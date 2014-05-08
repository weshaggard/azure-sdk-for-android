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

package com.microsoft.windowsazure.management.sql.models;

import java.util.Calendar;

/**
* SQL Server database.
*/
public class Database {
    private String collationName;
    
    /**
    * Optional. Gets or sets the database resource's collation name.
    * @return The CollationName value.
    */
    public String getCollationName() {
        return this.collationName;
    }
    
    /**
    * Optional. Gets or sets the database resource's collation name.
    * @param collationNameValue The CollationName value.
    */
    public void setCollationName(final String collationNameValue) {
        this.collationName = collationNameValue;
    }
    
    private Calendar creationDate;
    
    /**
    * Optional. Gets or sets the date this database was created.
    * @return The CreationDate value.
    */
    public Calendar getCreationDate() {
        return this.creationDate;
    }
    
    /**
    * Optional. Gets or sets the date this database was created.
    * @param creationDateValue The CreationDate value.
    */
    public void setCreationDate(final Calendar creationDateValue) {
        this.creationDate = creationDateValue;
    }
    
    private String edition;
    
    /**
    * Optional. Gets or sets the database resource's edition.
    * @return The Edition value.
    */
    public String getEdition() {
        return this.edition;
    }
    
    /**
    * Optional. Gets or sets the database resource's edition.
    * @param editionValue The Edition value.
    */
    public void setEdition(final String editionValue) {
        this.edition = editionValue;
    }
    
    private int id;
    
    /**
    * Optional. Gets or sets the id of the database.
    * @return The Id value.
    */
    public int getId() {
        return this.id;
    }
    
    /**
    * Optional. Gets or sets the id of the database.
    * @param idValue The Id value.
    */
    public void setId(final int idValue) {
        this.id = idValue;
    }
    
    private boolean isFederationRoot;
    
    /**
    * Optional. Gets or sets a value indicating whether the database is a
    * federation root.
    * @return The IsFederationRoot value.
    */
    public boolean isFederationRoot() {
        return this.isFederationRoot;
    }
    
    /**
    * Optional. Gets or sets a value indicating whether the database is a
    * federation root.
    * @param isFederationRootValue The IsFederationRoot value.
    */
    public void setIsFederationRoot(final boolean isFederationRootValue) {
        this.isFederationRoot = isFederationRootValue;
    }
    
    private boolean isSystemObject;
    
    /**
    * Optional. Gets or sets a value indicating whether the database is a
    * system object.
    * @return The IsSystemObject value.
    */
    public boolean isSystemObject() {
        return this.isSystemObject;
    }
    
    /**
    * Optional. Gets or sets a value indicating whether the database is a
    * system object.
    * @param isSystemObjectValue The IsSystemObject value.
    */
    public void setIsSystemObject(final boolean isSystemObjectValue) {
        this.isSystemObject = isSystemObjectValue;
    }
    
    private int maximumDatabaseSizeInGB;
    
    /**
    * Optional. Gets or sets the maximum size of this database, in Gigabytes.
    * @return The MaximumDatabaseSizeInGB value.
    */
    public int getMaximumDatabaseSizeInGB() {
        return this.maximumDatabaseSizeInGB;
    }
    
    /**
    * Optional. Gets or sets the maximum size of this database, in Gigabytes.
    * @param maximumDatabaseSizeInGBValue The MaximumDatabaseSizeInGB value.
    */
    public void setMaximumDatabaseSizeInGB(final int maximumDatabaseSizeInGBValue) {
        this.maximumDatabaseSizeInGB = maximumDatabaseSizeInGBValue;
    }
    
    private String name;
    
    /**
    * Optional. Gets or sets the name of the database.
    * @return The Name value.
    */
    public String getName() {
        return this.name;
    }
    
    /**
    * Optional. Gets or sets the name of the database.
    * @param nameValue The Name value.
    */
    public void setName(final String nameValue) {
        this.name = nameValue;
    }
    
    private String serviceObjectiveAssignmentErrorCode;
    
    /**
    * Optional. Gets or sets the error code for this service objective.
    * @return The ServiceObjectiveAssignmentErrorCode value.
    */
    public String getServiceObjectiveAssignmentErrorCode() {
        return this.serviceObjectiveAssignmentErrorCode;
    }
    
    /**
    * Optional. Gets or sets the error code for this service objective.
    * @param serviceObjectiveAssignmentErrorCodeValue The
    * ServiceObjectiveAssignmentErrorCode value.
    */
    public void setServiceObjectiveAssignmentErrorCode(final String serviceObjectiveAssignmentErrorCodeValue) {
        this.serviceObjectiveAssignmentErrorCode = serviceObjectiveAssignmentErrorCodeValue;
    }
    
    private String serviceObjectiveAssignmentErrorDescription;
    
    /**
    * Optional. Gets or sets the error description, if any.
    * @return The ServiceObjectiveAssignmentErrorDescription value.
    */
    public String getServiceObjectiveAssignmentErrorDescription() {
        return this.serviceObjectiveAssignmentErrorDescription;
    }
    
    /**
    * Optional. Gets or sets the error description, if any.
    * @param serviceObjectiveAssignmentErrorDescriptionValue The
    * ServiceObjectiveAssignmentErrorDescription value.
    */
    public void setServiceObjectiveAssignmentErrorDescription(final String serviceObjectiveAssignmentErrorDescriptionValue) {
        this.serviceObjectiveAssignmentErrorDescription = serviceObjectiveAssignmentErrorDescriptionValue;
    }
    
    private String serviceObjectiveAssignmentState;
    
    /**
    * Optional. Gets or sets the state of the current assignment.
    * @return The ServiceObjectiveAssignmentState value.
    */
    public String getServiceObjectiveAssignmentState() {
        return this.serviceObjectiveAssignmentState;
    }
    
    /**
    * Optional. Gets or sets the state of the current assignment.
    * @param serviceObjectiveAssignmentStateValue The
    * ServiceObjectiveAssignmentState value.
    */
    public void setServiceObjectiveAssignmentState(final String serviceObjectiveAssignmentStateValue) {
        this.serviceObjectiveAssignmentState = serviceObjectiveAssignmentStateValue;
    }
    
    private String serviceObjectiveAssignmentStateDescription;
    
    /**
    * Optional. Gets or sets the state description.
    * @return The ServiceObjectiveAssignmentStateDescription value.
    */
    public String getServiceObjectiveAssignmentStateDescription() {
        return this.serviceObjectiveAssignmentStateDescription;
    }
    
    /**
    * Optional. Gets or sets the state description.
    * @param serviceObjectiveAssignmentStateDescriptionValue The
    * ServiceObjectiveAssignmentStateDescription value.
    */
    public void setServiceObjectiveAssignmentStateDescription(final String serviceObjectiveAssignmentStateDescriptionValue) {
        this.serviceObjectiveAssignmentStateDescription = serviceObjectiveAssignmentStateDescriptionValue;
    }
    
    private String serviceObjectiveAssignmentSuccessDate;
    
    /**
    * Optional. Gets or sets the date the service's assignment succeeded.
    * @return The ServiceObjectiveAssignmentSuccessDate value.
    */
    public String getServiceObjectiveAssignmentSuccessDate() {
        return this.serviceObjectiveAssignmentSuccessDate;
    }
    
    /**
    * Optional. Gets or sets the date the service's assignment succeeded.
    * @param serviceObjectiveAssignmentSuccessDateValue The
    * ServiceObjectiveAssignmentSuccessDate value.
    */
    public void setServiceObjectiveAssignmentSuccessDate(final String serviceObjectiveAssignmentSuccessDateValue) {
        this.serviceObjectiveAssignmentSuccessDate = serviceObjectiveAssignmentSuccessDateValue;
    }
    
    private String serviceObjectiveId;
    
    /**
    * Optional. Gets or sets the id of this service objective.
    * @return The ServiceObjectiveId value.
    */
    public String getServiceObjectiveId() {
        return this.serviceObjectiveId;
    }
    
    /**
    * Optional. Gets or sets the id of this service objective.
    * @param serviceObjectiveIdValue The ServiceObjectiveId value.
    */
    public void setServiceObjectiveId(final String serviceObjectiveIdValue) {
        this.serviceObjectiveId = serviceObjectiveIdValue;
    }
    
    private String sizeMB;
    
    /**
    * Optional. Gets or sets the size of this database in megabytes (MB).
    * @return The SizeMB value.
    */
    public String getSizeMB() {
        return this.sizeMB;
    }
    
    /**
    * Optional. Gets or sets the size of this database in megabytes (MB).
    * @param sizeMBValue The SizeMB value.
    */
    public void setSizeMB(final String sizeMBValue) {
        this.sizeMB = sizeMBValue;
    }
    
    private String state;
    
    /**
    * Optional. Gets or sets the state of the database.
    * @return The State value.
    */
    public String getState() {
        return this.state;
    }
    
    /**
    * Optional. Gets or sets the state of the database.
    * @param stateValue The State value.
    */
    public void setState(final String stateValue) {
        this.state = stateValue;
    }
    
    private String type;
    
    /**
    * Optional. Gets or sets the type of resource.
    * @return The Type value.
    */
    public String getType() {
        return this.type;
    }
    
    /**
    * Optional. Gets or sets the type of resource.
    * @param typeValue The Type value.
    */
    public void setType(final String typeValue) {
        this.type = typeValue;
    }
}
