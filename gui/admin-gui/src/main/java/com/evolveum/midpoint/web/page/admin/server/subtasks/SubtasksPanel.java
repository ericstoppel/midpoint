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

package com.evolveum.midpoint.web.page.admin.server.subtasks;

import com.evolveum.midpoint.model.api.context.ModelState;
import com.evolveum.midpoint.web.component.data.TablePanel;
import com.evolveum.midpoint.web.component.model.delta.DeltaDto;
import com.evolveum.midpoint.web.component.model.delta.DeltaPanel;
import com.evolveum.midpoint.web.component.util.ListDataProvider;
import com.evolveum.midpoint.web.component.util.SimplePanel;
import com.evolveum.midpoint.web.page.admin.home.dto.MyWorkItemDto;
import com.evolveum.midpoint.web.page.admin.server.PageTasks;
import com.evolveum.midpoint.web.page.admin.server.dto.TaskDto;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mederly
 */
public class SubtasksPanel extends SimplePanel<List<TaskDto>> {

    private static final String ID_SUBTASKS_TABLE = "subtasksTable";

    public SubtasksPanel(String id, IModel<List<TaskDto>> model, boolean workflowsEnabled) {
        super(id, model);
        initLayoutLocal(workflowsEnabled);
    }

    private void initLayoutLocal(boolean workflowsEnabled) {
        List<IColumn<TaskDto, String>> columns = new ArrayList<IColumn<TaskDto, String>>();
        columns.add(PageTasks.createTaskNameColumn(this, "SubtasksPanel.label.name"));
        columns.add(PageTasks.createTaskCategoryColumn(this, "SubtasksPanel.label.category"));
        columns.add(PageTasks.createTaskExecutionStatusColumn(this, "SubtasksPanel.label.executionState"));
        columns.add(PageTasks.createTaskResultStatusColumn(this, "SubtasksPanel.label.result"));
        columns.add(PageTasks.createTaskDetailColumn(this, "SubtasksPanel.label.detail", workflowsEnabled));

        ISortableDataProvider provider = new ListDataProvider(this, getModel());
        add(new TablePanel<TaskDto>(ID_SUBTASKS_TABLE, provider, columns));

    }
}
