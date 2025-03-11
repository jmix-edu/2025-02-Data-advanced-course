package com.company.projectmanagementdata.view.project;

import com.company.projectmanagementdata.app.ProjectsSavingService;
import com.company.projectmanagementdata.datatype.ProjectLabels;
import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.Roadmap;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {

    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private TypedTextField<ProjectLabels> projectLabelsField;
    @Autowired
    private ProjectsSavingService projectsSavingService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Validator validator;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<Project> event) {

        Roadmap roadmap = dataContext.create(Roadmap.class);
        event.getEntity().setRoadmap(roadmap);

        projectLabelsField.setReadOnly(false);
        event.getEntity().setProjectLabels(new ProjectLabels(List.of("bug", "task", "enhancement")));
    }

    @Subscribe(id = "commitWithBeanValidationButton", subject = "clickListener")
    public void onCommitWithBeanValidationButtonClick(final ClickEvent<JmixButton> event) {
        try {
            projectsSavingService.save(getEditedEntity());
            close(StandardOutcome.CLOSE);
        } catch (ConstraintViolationException e) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
                sb.append(constraintViolation.getMessage()).append("\n");
            }

            notifications.create(sb.toString())
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }

    @Subscribe(id = "performBeanValidationButton", subject = "clickListener")
    public void onPerformBeanValidationButtonClick(final ClickEvent<JmixButton> event) {
        Set<ConstraintViolation<Project>> violations = validator.validate(getEditedEntity());

        showExceptions(violations);

    }

    private void showExceptions(Set<ConstraintViolation<Project>> violations) {
        StringBuilder sb = new StringBuilder();

        for (ConstraintViolation<?> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage()).append("\n");
        }

        notifications.create(sb.toString())
                .withPosition(Notification.Position.TOP_CENTER)
                .show();
    }
}