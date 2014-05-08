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

import com.microsoft.windowsazure.core.ServiceClient;
import com.microsoft.windowsazure.credentials.SubscriptionCloudCredentials;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.http.impl.client.HttpClientBuilder;

/**
* The SQL Database Management API is a REST API for managing SQL Database
* servers and the firewall rules associated with SQL Database servers.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/gg715283.aspx for more
* information)
*/
public class SqlManagementClientImpl extends ServiceClient<SqlManagementClient> implements SqlManagementClient {
    private URI baseUri;
    
    /**
    * The URI used as the base for all SQL requests.
    * @return The BaseUri value.
    */
    public URI getBaseUri() {
        return this.baseUri;
    }
    
    private SubscriptionCloudCredentials credentials;
    
    /**
    * When you create a Windows Azure subscription, it is uniquely identified
    * by a subscription ID. The subscription ID forms part of the URI for
    * every call that you make to the Service Management API.  The Windows
    * Azure Service ManagementAPI use mutual authentication of management
    * certificates over SSL to ensure that a request made to the service is
    * secure.  No anonymous requests are allowed.
    * @return The Credentials value.
    */
    public SubscriptionCloudCredentials getCredentials() {
        return this.credentials;
    }
    
    private DacOperations dac;
    
    /**
    * Includes operations for importing and exporting SQL Databases into and
    * out of Windows Azure blob storage.
    * @return The DacOperations value.
    */
    public DacOperations getDacOperations() {
        return this.dac;
    }
    
    private DatabaseOperationOperations databaseOperations;
    
    /**
    * The SQL Database Management API includes operations for get/stop SQL
    * Databases' operations for a subscription.
    * @return The DatabaseOperationsOperations value.
    */
    public DatabaseOperationOperations getDatabaseOperationsOperations() {
        return this.databaseOperations;
    }
    
    private DatabaseOperations databases;
    
    /**
    * The SQL Database Management API includes operations for managing SQL
    * Databases for a subscription.
    * @return The DatabasesOperations value.
    */
    public DatabaseOperations getDatabasesOperations() {
        return this.databases;
    }
    
    private FirewallRuleOperations firewallRules;
    
    /**
    * The Windows Azure SQL Database Management API includes operations for
    * managing the server-level firewall rules for SQL Database servers.You
    * cannot manage the database-level firewall rules using the Windows Azure
    * SQL Database Management API; they can only be managed by running the
    * Transact-SQL statements against the master or individual user
    * databases.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715276.aspx for
    * more information)
    * @return The FirewallRulesOperations value.
    */
    public FirewallRuleOperations getFirewallRulesOperations() {
        return this.firewallRules;
    }
    
    private ServerOperations servers;
    
    /**
    * The SQL Database Management API includes operations for managing SQL
    * Database servers for a subscription.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/gg715271.aspx for
    * more information)
    * @return The ServersOperations value.
    */
    public ServerOperations getServersOperations() {
        return this.servers;
    }
    
    private ServiceObjectiveOperations serviceObjectives;
    
    /**
    * The SQL Database Management API includes operations for getting Service
    * Objective for a subscription.
    * @return The ServiceObjectivesOperations value.
    */
    public ServiceObjectiveOperations getServiceObjectivesOperations() {
        return this.serviceObjectives;
    }
    
    /**
    * Initializes a new instance of the SqlManagementClientImpl class.
    *
    * @param httpBuilder The HTTP client builder.
    * @param executorService The executor service.
    */
    private SqlManagementClientImpl(HttpClientBuilder httpBuilder, ExecutorService executorService) {
        super(httpBuilder, executorService);
        this.dac = new DacOperationsImpl(this);
        this.databaseOperations = new DatabaseOperationOperationsImpl(this);
        this.databases = new DatabaseOperationsImpl(this);
        this.firewallRules = new FirewallRuleOperationsImpl(this);
        this.servers = new ServerOperationsImpl(this);
        this.serviceObjectives = new ServiceObjectiveOperationsImpl(this);
    }
    
    /**
    * Initializes a new instance of the SqlManagementClientImpl class.
    *
    * @param httpBuilder The HTTP client builder.
    * @param executorService The executor service.
    * @param credentials Required. When you create a Windows Azure
    * subscription, it is uniquely identified by a subscription ID. The
    * subscription ID forms part of the URI for every call that you make to
    * the Service Management API.  The Windows Azure Service ManagementAPI use
    * mutual authentication of management certificates over SSL to ensure that
    * a request made to the service is secure.  No anonymous requests are
    * allowed.
    * @param baseUri Required. The URI used as the base for all SQL requests.
    */
    @Inject
    public SqlManagementClientImpl(HttpClientBuilder httpBuilder, ExecutorService executorService, @Named(ManagementConfiguration.SUBSCRIPTION_CLOUD_CREDENTIALS) SubscriptionCloudCredentials credentials, @Named(ManagementConfiguration.URI) URI baseUri) {
        this(httpBuilder, executorService);
        if (credentials == null) {
            throw new NullPointerException("credentials");
        } else {
            this.credentials = credentials;
        }
        if (baseUri == null) {
            try {
                this.baseUri = new URI("https://management.core.windows.net");
            }
            catch (URISyntaxException ex) {
            }
        } else {
            this.baseUri = baseUri;
        }
        this.credentials = credentials;
        this.baseUri = baseUri;
    }
    
    /**
    * Initializes a new instance of the SqlManagementClientImpl class.
    *
    * @param httpBuilder The HTTP client builder.
    * @param executorService The executor service.
    * @param credentials Required. When you create a Windows Azure
    * subscription, it is uniquely identified by a subscription ID. The
    * subscription ID forms part of the URI for every call that you make to
    * the Service Management API.  The Windows Azure Service ManagementAPI use
    * mutual authentication of management certificates over SSL to ensure that
    * a request made to the service is secure.  No anonymous requests are
    * allowed.
    * @throws URISyntaxException Thrown if there was an error parsing a URI in
    * the response.
    */
    public SqlManagementClientImpl(HttpClientBuilder httpBuilder, ExecutorService executorService, SubscriptionCloudCredentials credentials) throws URISyntaxException {
        this(httpBuilder, executorService);
        if (credentials == null) {
            throw new NullPointerException("credentials");
        }
        this.credentials = credentials;
        this.baseUri = new URI("https://management.core.windows.net");
    }
    
    /**
    * Initializes a new instance of the SqlManagementClientImpl class.
    *
    * @param httpBuilder The HTTP client builder.
    * @param executorService The executor service.
    */
    protected SqlManagementClientImpl newInstance(HttpClientBuilder httpBuilder, ExecutorService executorService) {
        return new SqlManagementClientImpl(httpBuilder, executorService, this.getCredentials(), this.getBaseUri());
    }
}
