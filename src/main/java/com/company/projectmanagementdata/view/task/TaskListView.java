package com.company.projectmanagementdata.view.task;

import com.company.projectmanagementdata.entity.Task;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import io.jmix.pessimisticlock.LockManager;
import io.jmix.pessimisticlock.entity.LockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Route(value = "tasks", layout = MainView.class)
@ViewController(id = "Task_.list")
@ViewDescriptor(path = "task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
    private static final Logger log = LoggerFactory.getLogger(TaskListView.class);

    @Autowired
    private LockManager lockManager;
    @Autowired
    private MetadataTools metadataTools;
    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    @Supply(to = "tasksDataGrid.locked", subject = "renderer")
    private Renderer<Task> tasksDataGridLockedRenderer() {
        return new ComponentRenderer<>(Div::new, (div, task) -> {
            Icon icon;
            if (taskIsLocked(task)) {
                icon = VaadinIcon.LOCK.create();
                icon.setColor("var(--lumo-error-color)");
            } else {
                icon = VaadinIcon.OPEN_BOOK.create();
                icon.setColor("var(--lumo-success-color)");
            }

            div.add(icon);
        });
    }

    private boolean taskIsLocked(Task task) {
        Collection<LockInfo> lockInfos = lockManager.getCurrentLocks();

        return lockInfos.stream()
                .map(LockInfo::getObjectId)
                .map(UUID::fromString)
                .toList()
                .contains(task.getId());
    }

}