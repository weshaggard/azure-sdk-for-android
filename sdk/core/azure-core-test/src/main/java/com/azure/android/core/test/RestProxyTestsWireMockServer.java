// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.core.test;

import com.azure.android.core.serde.jackson.SerdeEncoding;
import com.azure.android.core.util.DateTimeRfc1123;
import com.azure.android.core.serde.jackson.JacksonSerder;
import com.azure.android.core.test.implementation.entities.HttpBinFormDataJSON;
import com.azure.android.core.test.implementation.entities.HttpBinJSON;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.http.MultiValue;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.head;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

/**
 * The WireMockServer for REST proxy tests.
 */
public final class RestProxyTestsWireMockServer {
    /**
     * Gets a WireMockServer instance to validate the REST proxy tests.
     *
     * @return The WireMockServer.
     */
    public static WireMockServer getRestProxyTestsServer() {
        WireMockServer server = new WireMockServer(WireMockConfiguration.options()
            .extensions(new RestProxyResponseTransformer())
            .dynamicPort()
            .disableRequestJournal()
            .gzipDisabled(true));

        // Stub that will return a response with a body containing the passed number of bytes.
        server.stubFor(get(urlPathMatching("/bytes/\\d+")));

        // Stub that will return a response with a body that contains the URL string as-is.
        server.stubFor(get(urlPathMatching("/anything.*")));

        // Stub that will return a response with the passed status code.
        server.stubFor(get(urlPathMatching("/status/\\d+")));

        // Simple stubs that will return what is passed.
        server.stubFor(post("post"));
        server.stubFor(put("put"));
        server.stubFor(head(urlPathMatching("/anything")));
        server.stubFor(delete("delete"));
        server.stubFor(patch(urlPathMatching("/patch")));
        server.stubFor(get("/get"));

        return server;
    }

    private static final class RestProxyResponseTransformer extends ResponseDefinitionTransformer {
        @Override
        public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition,
                                            FileSource fileSource, Parameters parameters) {
            try {
                URL url = new URL(request.getAbsoluteUrl());

                String urlPath = url.getPath();
                if (urlPath.startsWith("/bytes")) {
                    return createBytesResponse(urlPath);
                } else if (urlPath.startsWith("/status")) {
                    return createStatusResponse(urlPath);
                } else if (urlPath.startsWith("/post")) {
                    if ("application/x-www-form-urlencoded".equalsIgnoreCase(request.getHeader("Content-Type"))) {
                        return createFormResponse(request);
                    } else {
                        return createSimpleHttpBinResponse(request, url);
                    }
                } else if (urlPath.startsWith("/anything") || urlPath.startsWith("/put")
                    || urlPath.startsWith("/delete") || urlPath.startsWith("/patch") || urlPath.startsWith("/get")) {
                    return createSimpleHttpBinResponse(request, url);
                }  else {
                    return responseDefinition;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private static ResponseDefinition createBytesResponse(String urlPath) {
            int bodySize = Integer.parseInt(urlPath.split("/", 3)[2]);
            Map<String, String> rawHeaders = getBaseHttpHeaders();
            rawHeaders.put("Content-Type", "application/octet-stream");
            rawHeaders.put("Content-Length", String.valueOf(bodySize));

            byte[] body = new byte[bodySize];
            new SecureRandom().nextBytes(body);

            return new ResponseDefinitionBuilder().withStatus(200)
                .withHeaders(toWireMockHeaders(rawHeaders))
                .withBody(body)
                .build();
        }

        private static ResponseDefinition createSimpleHttpBinResponse(Request request, URL url) throws IOException {
            HttpBinJSON responseBody = new HttpBinJSON();
            responseBody.url(cleanseUrl(url));
            responseBody.data(request.getBodyAsString());

            if (request.getHeaders() != null) {
                responseBody.headers(request.getHeaders().all().stream()
                    .collect(Collectors.toMap(MultiValue::key, MultiValue::firstValue)));
            }

            return new ResponseDefinitionBuilder().withStatus(200)
                .withHeaders(toWireMockHeaders(getBaseHttpHeaders()))
                .withBody(new JacksonSerder().serialize(responseBody, SerdeEncoding.JSON))
                .build();
        }

        private static ResponseDefinition createStatusResponse(String urlPath) {
            return new ResponseDefinitionBuilder().withStatus(Integer.parseInt(urlPath.split("/", 3)[2])).build();
        }

        private static ResponseDefinition createFormResponse(Request request) throws IOException {
            HttpBinFormDataJSON formBody = new HttpBinFormDataJSON();
            HttpBinFormDataJSON.Form form = new HttpBinFormDataJSON.Form();
            List<String> toppings = new ArrayList<>();

            for (String formKvp : request.getBodyAsString().split("&")) {
                String[] kvpPieces = formKvp.split("=");

                switch (kvpPieces[0]) {
                    case "custname":
                        form.customerName(kvpPieces[1]);
                        break;
                    case "custtel":
                        form.customerTelephone(kvpPieces[1]);
                        break;
                    case "custemail":
                        form.customerEmail(kvpPieces[1]);
                        break;
                    case "size":
                        form.pizzaSize(HttpBinFormDataJSON.PizzaSize.valueOf(kvpPieces[1]));
                        break;
                    case "toppings":
                        toppings.add(kvpPieces[1]);
                        break;
                    default:
                        break;
                }
            }

            form.toppings(toppings);
            formBody.form(form);

            return new ResponseDefinitionBuilder()
                .withStatus(200)
                .withBody(new JacksonSerder().serialize(formBody, SerdeEncoding.JSON))
                .build();
        }

        private static String cleanseUrl(URL url) {
            StringBuilder builder = new StringBuilder();
            builder.append(url.getProtocol())
                .append("://")
                .append(url.getHost())
                .append(url.getPath().replace("%20", " "));

            if (url.getQuery() != null) {
                builder.append("?").append(url.getQuery().replace("%20", " "));
            }

            return builder.toString();
        }

        private static Map<String, String> getBaseHttpHeaders() {
            Map<String, String> baseHeaders = new HashMap<>();
            baseHeaders.put("Date", new DateTimeRfc1123(OffsetDateTime.now(ZoneOffset.UTC)).toString());
            baseHeaders.put("Connection", "keep-alive");
            baseHeaders.put("X-Processed-Time", String.valueOf(Math.random() * 10));
            baseHeaders.put("Access-Control-Allow-Credentials", "true");
            baseHeaders.put("Content-Type", "application/json");

            return baseHeaders;
        }

        private static HttpHeaders toWireMockHeaders(Map<String, String> rawHeaders) {
            return new HttpHeaders(rawHeaders.entrySet().stream()
                .map(kvp -> new HttpHeader(kvp.getKey(), kvp.getValue()))
                .collect(Collectors.toList()));
        }

        @Override
        public String getName() {
            return "rest-proxy-transformer";
        }
    }
}
