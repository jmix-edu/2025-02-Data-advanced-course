package com.company.projectmanagementdata.view.userprojectsdialog;


import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.User;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

@Route(value = "user-projects-dialog", layout = MainView.class)
@ViewController(id = "UserProjectsDialog")
@ViewDescriptor(path = "user-projects-dialog.xml")
@DialogMode(width = "50em", height = "37.5em")
public class UserProjectsDialog extends StandardView {

    private User user;
    @ViewComponent
    private CollectionLoader<Project> projectsDl;

    public User getUser() {
        return user;
    }

    public UserProjectsDialog setUser(User user) {
        this.user = user;

        projectsDl.setParameter("user", user);
        projectsDl.load();

        return this;
    }
}