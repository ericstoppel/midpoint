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

package com.evolveum.midpoint.web.security;

import com.evolveum.midpoint.audit.api.AuditEventRecord;
import com.evolveum.midpoint.audit.api.AuditEventStage;
import com.evolveum.midpoint.audit.api.AuditEventType;
import com.evolveum.midpoint.audit.api.AuditService;
import com.evolveum.midpoint.common.security.MidPointPrincipal;
import com.evolveum.midpoint.prism.PrismObject;
import com.evolveum.midpoint.schema.constants.SchemaConstants;
import com.evolveum.midpoint.schema.result.OperationResultStatus;
import com.evolveum.midpoint.task.api.Task;
import com.evolveum.midpoint.task.api.TaskManager;
import com.evolveum.midpoint.util.logging.Trace;
import com.evolveum.midpoint.util.logging.TraceManager;
import com.evolveum.midpoint.xml.ns._public.common.common_2a.UserType;
import org.apache.wicket.Session;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletContext;

/**
 * @author lazyman
 */
public class SecurityUtils {

    private static final Trace LOGGER = TraceManager.getTrace(SecurityUtils.class);

    public static MidPointPrincipal getPrincipalUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return getPrincipalUser(authentication);
    }

    public static MidPointPrincipal getPrincipalUser(Authentication authentication) {
        if (authentication == null) {
            LOGGER.debug("Authentication not available in security current context holder.");
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof MidPointPrincipal)) {
            LOGGER.warn("Principal user in security context holder is {} but not type of {}",
                    new Object[]{principal, MidPointPrincipal.class.getName()});
            return null;
        }

        return (MidPointPrincipal) principal;
    }
}
