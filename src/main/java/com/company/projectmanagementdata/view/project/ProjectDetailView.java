package com.company.projectmanagementdata.view.project;

import com.company.projectmanagementdata.datatype.ProjectLabels;
import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.Roadmap;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;

import java.util.List;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private TypedTextField<ProjectLabels> projectLabelsField;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {

        Roadmap roadmap = dataContext.create(Roadmap.class);
        event.getEntity().setRoadmap(roadmap);

        projectLabelsField.setReadOnly(false);
        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "task", "enhancement")));
    }
}