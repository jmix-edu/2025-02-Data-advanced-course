package com.company.projectmanagementdata.view.task;

import com.company.projectmanagementdata.entity.Task;
import com.company.projectmanagementdata.repository.TaskRepository;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.jmix.core.repository.JmixDataRepositoryUtils.buildPageRequest;
import static io.jmix.core.repository.JmixDataRepositoryUtils.buildRepositoryContext;


@Route(value = "tasks-repo", layout = MainView.class)
@ViewController(id = "Task_repo.list")
@ViewDescriptor(path = "task-repo-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskRepoListView extends StandardListView<Task> {


    @Autowired
    private TaskRepository taskRepository;

    @Install(to = "tasksDl", target = Target.DATA_LOADER)
    private List<Task> tasksDlLoadDelegate(final LoadContext loadContext) {
        return taskRepository.findAll(buildPageRequest(loadContext), buildRepositoryContext(loadContext)).getContent();
    }
}