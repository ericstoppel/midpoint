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

package com.evolveum.midpoint.repo.sql.query;

import com.evolveum.midpoint.prism.PrismContext;
import com.evolveum.midpoint.prism.path.ItemPath;
import com.evolveum.midpoint.repo.sql.util.ClassMapper;
import com.evolveum.midpoint.schema.constants.ObjectTypes;
import com.evolveum.midpoint.xml.ns._public.common.common_2a.ObjectType;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lazyman
 */
public class QueryContext {

    private QueryInterpreter interpreter;
    private PrismContext prismContext;
    private Session session;

    private Class<? extends ObjectType> type;

    private final Map<ItemPath, Criteria> criterias = new HashMap<ItemPath, Criteria>();
    private final Map<ItemPath, String> aliases = new HashMap<ItemPath, String>();

    public QueryContext(QueryInterpreter interpreter, Class<? extends ObjectType> type,
                        PrismContext prismContext, Session session) {
        this.interpreter = interpreter;
        this.type = type;
        this.prismContext = prismContext;
        this.session = session;

        String alias = addAlias(null);
        addCriteria(null, session.createCriteria(ClassMapper.getHQLTypeClass(type), alias));
    }

    public PrismContext getPrismContext() {
        return prismContext;
    }

    public Session getSession() {
        return session;
    }

    public QueryInterpreter getInterpreter() {
        return interpreter;
    }

    public Class<? extends ObjectType> getType() {
        return type;
    }

    public Criteria getCriteria(ItemPath path) {
        return criterias.get(path);
    }

    public void addCriteria(ItemPath path, Criteria criteria) {
        criterias.put(path, criteria);
    }

    public String getAlias(ItemPath path) {
        return aliases.get(path);
    }

    public String addAlias(ItemPath path) {
        QName qname = null;
        if (path == null) {
            //get qname from class type
            qname = ObjectTypes.getObjectType(type).getQName();
        } else {
            if (!path.isEmpty()) {
                //get last qname from path
                qname = ItemPath.getName(path.last());
            } else {
                throw new IllegalArgumentException("Item path must not be empty.");
            }
        }

        String alias = createAlias(qname);
        aliases.put(path, alias);

        return alias;
    }

    private String createAlias(QName qname) {
        String prefix = Character.toString(qname.getLocalPart().charAt(0)).toLowerCase();
        int index = 1;

        String alias = prefix;
        while (hasAlias(alias)) {
            alias = prefix + Integer.toString(index);
            index++;

            if (index > 20) {
                throw new IllegalStateException("Alias index for segment '" + qname
                        + "' is more than 20? This probably should not happen.");
            }
        }

        return alias;
    }

    private boolean hasAlias(String alias) {
        return aliases.containsValue(alias);
    }

    public boolean hasAlias(ItemPath path) {
        return aliases.get(path) != null;
    }
}
