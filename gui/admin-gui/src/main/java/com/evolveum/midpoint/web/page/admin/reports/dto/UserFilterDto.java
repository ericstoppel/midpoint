/*
 * Copyright (c) 2010-2013 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.midpoint.web.page.admin.reports.dto;

import java.io.Serializable;

import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.xml.ns._public.common.common_2a.ResourceType;

/**
 * @author mserbak
 */
public class UserFilterDto implements Serializable {
	private String searchText;
    private Boolean activated = null;
    private PrismObject<ResourceType> resource = null;
    
    
	public String getSearchText() {
		return searchText;
	}
	
	public Boolean isActivated() {
		return activated;
	}
	
	public PrismObject<ResourceType> getResource() {
		return resource;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	
	public void setResource(PrismObject<ResourceType> resource) {
		this.resource = resource;
	} 
}
