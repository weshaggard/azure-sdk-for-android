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

package com.microsoft.azure.management.resources.models;

/**
* Deployment operation information.
*/
public class DeploymentOperation {
    private String operationId;
    
    /**
    * Optional. Operation Id
    * @return The OperationId value.
    */
    public String getOperationId() {
        return this.operationId;
    }
    
    /**
    * Optional. Operation Id
    * @param operationIdValue The OperationId value.
    */
    public void setOperationId(final String operationIdValue) {
        this.operationId = operationIdValue;
    }
    
    private DeploymentOperationProperties properties;
    
    /**
    * Optional. Gets or sets deployment properties.
    * @return The Properties value.
    */
    public DeploymentOperationProperties getProperties() {
        return this.properties;
    }
    
    /**
    * Optional. Gets or sets deployment properties.
    * @param propertiesValue The Properties value.
    */
    public void setProperties(final DeploymentOperationProperties propertiesValue) {
        this.properties = propertiesValue;
    }
}
