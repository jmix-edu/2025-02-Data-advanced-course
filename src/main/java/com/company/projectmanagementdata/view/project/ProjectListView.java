package com.company.projectmanagementdata.view.project;

import com.company.projectmanagementdata.datatype.ProjectLabels;
import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.Roadmap;
import com.company.projectmanagementdata.entity.User;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


@Route(value = "projects", layout = MainView.class)
@ViewController(id = "Project.list")
@ViewDescriptor(path = "project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {


    @Autowired
    private DataManager dataManager;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;

    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "dmCreateButton", subject = "clickListener")
    public void onDmCreateButtonClick(final ClickEvent<JmixButton> event) {
        Project project = dataManager.create(Project.class);
        project.setName("Project " + RandomStringUtils.randomAlphabetic(5));

        final User user = (User) currentAuthentication.getUser();
        project.setManager(user);

        Roadmap roadmap = dataManager.create(Roadmap.class);
        roadmap.setStartDate(LocalDate.now());
        project.setRoadmap(roadmap);

        project.setProjectLabels(new ProjectLabels(List.of("bug", "task", "enhancement")));

        Project saved = unconstrainedDataManager.save(project, roadmap).get(project);
        projectsDc.getMutableItems().add(saved);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        int newProjectsCount = dataManager.loadValue("select count(e) from Project e " +
                "where :session_isManager = TRUE and e.status = @enum(com.company.projectmanagementdata.entity.ProjectStatus.NEW) " +
                        "and e.manager.id = :current_user_id", Integer.class)
                .one();

        if (newProjectsCount != 0) {
            notifications.create("New projects", "You have as many projects with a new status: " + newProjectsCount)
                    .withType(Notifications.Type.SUCCESS)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }
    
    
}