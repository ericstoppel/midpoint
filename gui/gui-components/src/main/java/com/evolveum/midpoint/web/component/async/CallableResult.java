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

package com.evolveum.midpoint.web.component.async;

import com.evolveum.midpoint.schema.result.OperationResult;

import java.io.Serializable;

/**
 * @author lazyman
 */
public class CallableResult<T> implements Serializable {

    public static final String F_VALUE = "value";
    public static final String F_RESULT = "result";

    private T value;
    private OperationResult result;

    public CallableResult() {
    }

    public CallableResult(T value, OperationResult result) {
        this.result = result;
        this.value = value;
    }

    public OperationResult getResult() {
        return result;
    }

    public T getValue() {
        return value;
    }

    public void setResult(OperationResult result) {
        this.result = result;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
