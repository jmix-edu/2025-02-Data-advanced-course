package com.company.projectmanagementdata.view.user;

import com.company.projectmanagementdata.app.UsersService;
import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.User;
import com.company.projectmanagementdata.view.main.MainView;
import com.company.projectmanagementdata.view.userprojectsdialog.UserProjectsDialog;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.genericfilter.GenericFilter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "users", layout = MainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {

    private Project filterProject;

    @ViewComponent
    private GenericFilter genericFilter;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private UsersService usersService;
    @ViewComponent
    private DataGrid<User> usersDataGrid;
    @Autowired
    private DialogWindows dialogWindows;

    @Install(to = "usersDl", target = Target.DATA_LOADER)
    private List<User> usersDlLoadDelegate(final LoadContext loadContext) {
        LoadContext.Query query = loadContext.getQuery();
        if (filterProject != null && query != null) {
            return usersService.getUsersNotInProject(filterProject, query.getFirstResult(), query.getMaxResults());
        }
        return dataManager.loadList(loadContext);
    }

    public void setFilterProject(Project project) {
        this.filterProject = project;
        genericFilter.setVisible(false);
    }

    @Subscribe("usersDataGrid.showUserProjects")
    public void onUsersDataGridShowUserProjects(final ActionPerformedEvent event) {
        User selected = usersDataGrid.getSingleSelectedItem();
        if (selected == null) {
            return;
        }

        dialogWindows.view(this, UserProjectsDialog.class)
                .withViewConfigurer(view -> view.setUser(selected))
                .open();
    }
}