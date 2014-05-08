/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.sql;

import com.microsoft.windowsazure.core.ServiceOperations;
import com.microsoft.windowsazure.core.utils.BOMInputStream;
import com.microsoft.windowsazure.core.utils.XmlUtility;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.sql.models.ServiceObjective;
import com.microsoft.windowsazure.management.sql.models.ServiceObjectiveGetResponse;
import com.microsoft.windowsazure.management.sql.models.ServiceObjectiveListResponse;
import com.microsoft.windowsazure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
* The SQL Database Management API includes operations for getting Service
* Objective for a subscription.
*/
public class ServiceObjectiveOperationsImpl implements ServiceOperations<SqlManagementClientImpl>, ServiceObjectiveOperations {
    /**
    * Initializes a new instance of the ServiceObjectiveOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    ServiceObjectiveOperationsImpl(SqlManagementClientImpl client) {
        this.client = client;
    }
    
    private SqlManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.sql.SqlManagementClientImpl.
    * @return The Client value.
    */
    public SqlManagementClientImpl getClient() {
        return this.client;
    }
    
    /**
    * Returns information about a certain Service Objective on a given Id.
    *
    * @param serverName Required. The name of the SQL Server to be queried.
    * @param serviceObjectiveId Required. The Id of the Service Objective to be
    * obtained.
    * @return Response containing the service objective for a given server and
    * service objective Id.
    */
    @Override
    public Future<ServiceObjectiveGetResponse> getAsync(final String serverName, final String serviceObjectiveId) {
        return this.getClient().getExecutorService().submit(new Callable<ServiceObjectiveGetResponse>() { 
            @Override
            public ServiceObjectiveGetResponse call() throws Exception {
                return get(serverName, serviceObjectiveId);
            }
         });
    }
    
    /**
    * Returns information about a certain Service Objective on a given Id.
    *
    * @param serverName Required. The name of the SQL Server to be queried.
    * @param serviceObjectiveId Required. The Id of the Service Objective to be
    * obtained.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred. This class is the general class of exceptions produced by
    * failed or interrupted I/O operations.
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws ParserConfigurationException Thrown if there was a serious
    * configuration error with the document parser.
    * @throws SAXException Thrown if there was an error parsing the XML
    * response.
    * @return Response containing the service objective for a given server and
    * service objective Id.
    */
    @Override
    public ServiceObjectiveGetResponse get(String serverName, String serviceObjectiveId) throws IOException, ServiceException, ParserConfigurationException, SAXException {
        // Validate
        if (serverName == null) {
            throw new NullPointerException("serverName");
        }
        if (serviceObjectiveId == null) {
            throw new NullPointerException("serviceObjectiveId");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("serverName", serverName);
            tracingParameters.put("serviceObjectiveId", serviceObjectiveId);
            CloudTracing.enter(invocationId, this, "getAsync", tracingParameters);
        }
        
        // Construct URL
        String baseUrl = this.getClient().getBaseUri().toString();
        String url = (this.getClient().getCredentials().getSubscriptionId() != null ? this.getClient().getCredentials().getSubscriptionId().trim() : "") + "/services/sqlservers/servers/" + serverName.trim() + "/serviceobjectives/" + serviceObjectiveId.trim();
        // Trim '/' character from the end of baseUrl and beginning of url.
        if (baseUrl.charAt(baseUrl.length() - 1) == '/') {
            baseUrl = baseUrl.substring(0, (baseUrl.length() - 1) + 0);
        }
        if (url.charAt(0) == '/') {
            url = url.substring(1);
        }
        url = baseUrl + "/" + url;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        try {
            if (shouldTrace) {
                CloudTracing.sendRequest(invocationId, httpRequest);
            }
            httpResponse = this.getClient().getHttpClient().execute(httpRequest);
            if (shouldTrace) {
                CloudTracing.receiveResponse(invocationId, httpResponse);
            }
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
                if (shouldTrace) {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            ServiceObjectiveGetResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpResponse.getEntity().getContent();
            result = new ServiceObjectiveGetResponse();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document responseDoc = documentBuilder.parse(new BOMInputStream(responseContent));
            
            Element serviceResourceElement = XmlUtility.getElementByTagNameNS(responseDoc, "http://schemas.microsoft.com/windowsazure", "ServiceResource");
            if (serviceResourceElement != null) {
                ServiceObjective serviceResourceInstance = new ServiceObjective();
                result.setServiceObjective(serviceResourceInstance);
                
                Element nameElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "Name");
                if (nameElement != null) {
                    String nameInstance;
                    nameInstance = nameElement.getTextContent();
                    serviceResourceInstance.setName(nameInstance);
                }
                
                Element typeElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "Type");
                if (typeElement != null) {
                    String typeInstance;
                    typeInstance = typeElement.getTextContent();
                    serviceResourceInstance.setType(typeInstance);
                }
                
                Element stateElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "State");
                if (stateElement != null) {
                    String stateInstance;
                    stateInstance = stateElement.getTextContent();
                    serviceResourceInstance.setState(stateInstance);
                }
                
                Element selfLinkElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "SelfLink");
                if (selfLinkElement != null) {
                    String selfLinkInstance;
                    selfLinkInstance = selfLinkElement.getTextContent();
                    serviceResourceInstance.setSelfLink(selfLinkInstance);
                }
                
                Element parentLinkElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "ParentLink");
                if (parentLinkElement != null) {
                    String parentLinkInstance;
                    parentLinkInstance = parentLinkElement.getTextContent();
                    serviceResourceInstance.setParentLink(parentLinkInstance);
                }
                
                Element idElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "Id");
                if (idElement != null) {
                    String idInstance;
                    idInstance = idElement.getTextContent();
                    serviceResourceInstance.setId(idInstance);
                }
                
                Element isDefaultElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "IsDefault");
                if (isDefaultElement != null) {
                    boolean isDefaultInstance;
                    isDefaultInstance = DatatypeConverter.parseBoolean(isDefaultElement.getTextContent().toLowerCase());
                    serviceResourceInstance.setIsDefault(isDefaultInstance);
                }
                
                Element isSystemElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "IsSystem");
                if (isSystemElement != null) {
                    boolean isSystemInstance;
                    isSystemInstance = DatatypeConverter.parseBoolean(isSystemElement.getTextContent().toLowerCase());
                    serviceResourceInstance.setIsSystem(isSystemInstance);
                }
                
                Element descriptionElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "Description");
                if (descriptionElement != null) {
                    String descriptionInstance;
                    descriptionInstance = descriptionElement.getTextContent();
                    serviceResourceInstance.setDescription(descriptionInstance);
                }
                
                Element enabledElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "Enabled");
                if (enabledElement != null) {
                    boolean enabledInstance;
                    enabledInstance = DatatypeConverter.parseBoolean(enabledElement.getTextContent().toLowerCase());
                    serviceResourceInstance.setEnabled(enabledInstance);
                }
                
                Element dimensionSettingsSequenceElement = XmlUtility.getElementByTagNameNS(serviceResourceElement, "http://schemas.microsoft.com/windowsazure", "DimensionSettings");
                if (dimensionSettingsSequenceElement != null) {
                    for (int i1 = 0; i1 < com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(dimensionSettingsSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").size(); i1 = i1 + 1) {
                        org.w3c.dom.Element dimensionSettingsElement = ((org.w3c.dom.Element) com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(dimensionSettingsSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").get(i1));
                        ServiceObjective.DimensionSettingResponse serviceResourceInstance2 = new ServiceObjective.DimensionSettingResponse();
                        serviceResourceInstance.getDimensionSettings().add(serviceResourceInstance2);
                        
                        Element nameElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Name");
                        if (nameElement2 != null) {
                            String nameInstance2;
                            nameInstance2 = nameElement2.getTextContent();
                            serviceResourceInstance2.setName(nameInstance2);
                        }
                        
                        Element typeElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Type");
                        if (typeElement2 != null) {
                            String typeInstance2;
                            typeInstance2 = typeElement2.getTextContent();
                            serviceResourceInstance2.setType(typeInstance2);
                        }
                        
                        Element stateElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "State");
                        if (stateElement2 != null) {
                            String stateInstance2;
                            stateInstance2 = stateElement2.getTextContent();
                            serviceResourceInstance2.setState(stateInstance2);
                        }
                        
                        Element selfLinkElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "SelfLink");
                        if (selfLinkElement2 != null) {
                            String selfLinkInstance2;
                            selfLinkInstance2 = selfLinkElement2.getTextContent();
                            serviceResourceInstance2.setSelfLink(selfLinkInstance2);
                        }
                        
                        Element parentLinkElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "ParentLink");
                        if (parentLinkElement2 != null) {
                            String parentLinkInstance2;
                            parentLinkInstance2 = parentLinkElement2.getTextContent();
                            serviceResourceInstance2.setParentLink(parentLinkInstance2);
                        }
                        
                        Element idElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Id");
                        if (idElement2 != null) {
                            String idInstance2;
                            idInstance2 = idElement2.getTextContent();
                            serviceResourceInstance2.setId(idInstance2);
                        }
                        
                        Element descriptionElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Description");
                        if (descriptionElement2 != null) {
                            String descriptionInstance2;
                            descriptionInstance2 = descriptionElement2.getTextContent();
                            serviceResourceInstance2.setDescription(descriptionInstance2);
                        }
                        
                        Element ordinalElement = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Ordinal");
                        if (ordinalElement != null) {
                            byte ordinalInstance;
                            ordinalInstance = DatatypeConverter.parseByte(ordinalElement.getTextContent());
                            serviceResourceInstance2.setOrdinal(ordinalInstance);
                        }
                        
                        Element isDefaultElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "IsDefault");
                        if (isDefaultElement2 != null) {
                            boolean isDefaultInstance2;
                            isDefaultInstance2 = DatatypeConverter.parseBoolean(isDefaultElement2.getTextContent().toLowerCase());
                            serviceResourceInstance2.setIsDefault(isDefaultInstance2);
                        }
                    }
                }
            }
            
            result.setStatusCode(statusCode);
            if (httpResponse.getHeaders("x-ms-request-id").length > 0) {
                result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
            }
            
            if (shouldTrace) {
                CloudTracing.exit(invocationId, result);
            }
            return result;
        } finally {
            if (httpResponse != null && httpResponse.getEntity() != null) {
                httpResponse.getEntity().getContent().close();
            }
        }
    }
    
    /**
    * Returns information about all Service Objectives on a database server.
    *
    * @param serverName Required. The name of the database server to be queried.
    * @return Response containing the list of service objective for a given
    * server.
    */
    @Override
    public Future<ServiceObjectiveListResponse> listAsync(final String serverName) {
        return this.getClient().getExecutorService().submit(new Callable<ServiceObjectiveListResponse>() { 
            @Override
            public ServiceObjectiveListResponse call() throws Exception {
                return list(serverName);
            }
         });
    }
    
    /**
    * Returns information about all Service Objectives on a database server.
    *
    * @param serverName Required. The name of the database server to be queried.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred. This class is the general class of exceptions produced by
    * failed or interrupted I/O operations.
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws ParserConfigurationException Thrown if there was a serious
    * configuration error with the document parser.
    * @throws SAXException Thrown if there was an error parsing the XML
    * response.
    * @return Response containing the list of service objective for a given
    * server.
    */
    @Override
    public ServiceObjectiveListResponse list(String serverName) throws IOException, ServiceException, ParserConfigurationException, SAXException {
        // Validate
        if (serverName == null) {
            throw new NullPointerException("serverName");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("serverName", serverName);
            CloudTracing.enter(invocationId, this, "listAsync", tracingParameters);
        }
        
        // Construct URL
        String baseUrl = this.getClient().getBaseUri().toString();
        String url = (this.getClient().getCredentials().getSubscriptionId() != null ? this.getClient().getCredentials().getSubscriptionId().trim() : "") + "/services/sqlservers/servers/" + serverName.trim() + "/serviceobjectives";
        // Trim '/' character from the end of baseUrl and beginning of url.
        if (baseUrl.charAt(baseUrl.length() - 1) == '/') {
            baseUrl = baseUrl.substring(0, (baseUrl.length() - 1) + 0);
        }
        if (url.charAt(0) == '/') {
            url = url.substring(1);
        }
        url = baseUrl + "/" + url;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2012-03-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        try {
            if (shouldTrace) {
                CloudTracing.sendRequest(invocationId, httpRequest);
            }
            httpResponse = this.getClient().getHttpClient().execute(httpRequest);
            if (shouldTrace) {
                CloudTracing.receiveResponse(invocationId, httpResponse);
            }
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
                if (shouldTrace) {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            ServiceObjectiveListResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpResponse.getEntity().getContent();
            result = new ServiceObjectiveListResponse();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document responseDoc = documentBuilder.parse(new BOMInputStream(responseContent));
            
            Element serviceResourcesSequenceElement = XmlUtility.getElementByTagNameNS(responseDoc, "http://schemas.microsoft.com/windowsazure", "ServiceResources");
            if (serviceResourcesSequenceElement != null) {
                for (int i1 = 0; i1 < com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(serviceResourcesSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").size(); i1 = i1 + 1) {
                    org.w3c.dom.Element serviceResourcesElement = ((org.w3c.dom.Element) com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(serviceResourcesSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").get(i1));
                    ServiceObjective serviceResourceInstance = new ServiceObjective();
                    result.getServiceObjectives().add(serviceResourceInstance);
                    
                    Element nameElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "Name");
                    if (nameElement != null) {
                        String nameInstance;
                        nameInstance = nameElement.getTextContent();
                        serviceResourceInstance.setName(nameInstance);
                    }
                    
                    Element typeElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "Type");
                    if (typeElement != null) {
                        String typeInstance;
                        typeInstance = typeElement.getTextContent();
                        serviceResourceInstance.setType(typeInstance);
                    }
                    
                    Element stateElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "State");
                    if (stateElement != null) {
                        String stateInstance;
                        stateInstance = stateElement.getTextContent();
                        serviceResourceInstance.setState(stateInstance);
                    }
                    
                    Element selfLinkElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "SelfLink");
                    if (selfLinkElement != null) {
                        String selfLinkInstance;
                        selfLinkInstance = selfLinkElement.getTextContent();
                        serviceResourceInstance.setSelfLink(selfLinkInstance);
                    }
                    
                    Element parentLinkElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "ParentLink");
                    if (parentLinkElement != null) {
                        String parentLinkInstance;
                        parentLinkInstance = parentLinkElement.getTextContent();
                        serviceResourceInstance.setParentLink(parentLinkInstance);
                    }
                    
                    Element idElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "Id");
                    if (idElement != null) {
                        String idInstance;
                        idInstance = idElement.getTextContent();
                        serviceResourceInstance.setId(idInstance);
                    }
                    
                    Element isDefaultElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "IsDefault");
                    if (isDefaultElement != null) {
                        boolean isDefaultInstance;
                        isDefaultInstance = DatatypeConverter.parseBoolean(isDefaultElement.getTextContent().toLowerCase());
                        serviceResourceInstance.setIsDefault(isDefaultInstance);
                    }
                    
                    Element isSystemElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "IsSystem");
                    if (isSystemElement != null) {
                        boolean isSystemInstance;
                        isSystemInstance = DatatypeConverter.parseBoolean(isSystemElement.getTextContent().toLowerCase());
                        serviceResourceInstance.setIsSystem(isSystemInstance);
                    }
                    
                    Element descriptionElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "Description");
                    if (descriptionElement != null) {
                        String descriptionInstance;
                        descriptionInstance = descriptionElement.getTextContent();
                        serviceResourceInstance.setDescription(descriptionInstance);
                    }
                    
                    Element enabledElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "Enabled");
                    if (enabledElement != null) {
                        boolean enabledInstance;
                        enabledInstance = DatatypeConverter.parseBoolean(enabledElement.getTextContent().toLowerCase());
                        serviceResourceInstance.setEnabled(enabledInstance);
                    }
                    
                    Element dimensionSettingsSequenceElement = XmlUtility.getElementByTagNameNS(serviceResourcesElement, "http://schemas.microsoft.com/windowsazure", "DimensionSettings");
                    if (dimensionSettingsSequenceElement != null) {
                        for (int i2 = 0; i2 < com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(dimensionSettingsSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").size(); i2 = i2 + 1) {
                            org.w3c.dom.Element dimensionSettingsElement = ((org.w3c.dom.Element) com.microsoft.windowsazure.core.utils.XmlUtility.getElementsByTagNameNS(dimensionSettingsSequenceElement, "http://schemas.microsoft.com/windowsazure", "ServiceResource").get(i2));
                            ServiceObjective.DimensionSettingResponse serviceResourceInstance2 = new ServiceObjective.DimensionSettingResponse();
                            serviceResourceInstance.getDimensionSettings().add(serviceResourceInstance2);
                            
                            Element nameElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Name");
                            if (nameElement2 != null) {
                                String nameInstance2;
                                nameInstance2 = nameElement2.getTextContent();
                                serviceResourceInstance2.setName(nameInstance2);
                            }
                            
                            Element typeElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Type");
                            if (typeElement2 != null) {
                                String typeInstance2;
                                typeInstance2 = typeElement2.getTextContent();
                                serviceResourceInstance2.setType(typeInstance2);
                            }
                            
                            Element stateElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "State");
                            if (stateElement2 != null) {
                                String stateInstance2;
                                stateInstance2 = stateElement2.getTextContent();
                                serviceResourceInstance2.setState(stateInstance2);
                            }
                            
                            Element selfLinkElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "SelfLink");
                            if (selfLinkElement2 != null) {
                                String selfLinkInstance2;
                                selfLinkInstance2 = selfLinkElement2.getTextContent();
                                serviceResourceInstance2.setSelfLink(selfLinkInstance2);
                            }
                            
                            Element parentLinkElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "ParentLink");
                            if (parentLinkElement2 != null) {
                                String parentLinkInstance2;
                                parentLinkInstance2 = parentLinkElement2.getTextContent();
                                serviceResourceInstance2.setParentLink(parentLinkInstance2);
                            }
                            
                            Element idElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Id");
                            if (idElement2 != null) {
                                String idInstance2;
                                idInstance2 = idElement2.getTextContent();
                                serviceResourceInstance2.setId(idInstance2);
                            }
                            
                            Element descriptionElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Description");
                            if (descriptionElement2 != null) {
                                String descriptionInstance2;
                                descriptionInstance2 = descriptionElement2.getTextContent();
                                serviceResourceInstance2.setDescription(descriptionInstance2);
                            }
                            
                            Element ordinalElement = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "Ordinal");
                            if (ordinalElement != null) {
                                byte ordinalInstance;
                                ordinalInstance = DatatypeConverter.parseByte(ordinalElement.getTextContent());
                                serviceResourceInstance2.setOrdinal(ordinalInstance);
                            }
                            
                            Element isDefaultElement2 = XmlUtility.getElementByTagNameNS(dimensionSettingsElement, "http://schemas.microsoft.com/windowsazure", "IsDefault");
                            if (isDefaultElement2 != null) {
                                boolean isDefaultInstance2;
                                isDefaultInstance2 = DatatypeConverter.parseBoolean(isDefaultElement2.getTextContent().toLowerCase());
                                serviceResourceInstance2.setIsDefault(isDefaultInstance2);
                            }
                        }
                    }
                }
            }
            
            result.setStatusCode(statusCode);
            if (httpResponse.getHeaders("x-ms-request-id").length > 0) {
                result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
            }
            
            if (shouldTrace) {
                CloudTracing.exit(invocationId, result);
            }
            return result;
        } finally {
            if (httpResponse != null && httpResponse.getEntity() != null) {
                httpResponse.getEntity().getContent().close();
            }
        }
    }
}
