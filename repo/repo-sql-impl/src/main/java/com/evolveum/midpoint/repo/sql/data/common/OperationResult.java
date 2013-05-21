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

package com.evolveum.midpoint.repo.sql.data.common;

import com.evolveum.midpoint.repo.sql.data.common.enums.ROperationResultStatus;

import java.io.Serializable;

/**
 * This interface helps handling and translation from and to entity classes like {@link ROperationResult}
 * to {@link com.evolveum.midpoint.xml.ns._public.common.common_2a.OperationResultType}.
 *
 * @author lazyman
 */
public interface OperationResult extends Serializable {

    String getParams();

    String getPartialResults();

    ROperationResultStatus getStatus();

    Long getToken();

    String getDetails();

    String getLocalizedMessage();

    String getMessage();

    String getMessageCode();

    String getOperation();

    void setParams(String params);

    void setPartialResults(String partialResults);

    void setStatus(ROperationResultStatus status);

    void setToken(Long token);

    void setDetails(String details);

    void setLocalizedMessage(String message);

    void setMessage(String message);

    void setMessageCode(String messageCode);

    void setOperation(String operation);
}
