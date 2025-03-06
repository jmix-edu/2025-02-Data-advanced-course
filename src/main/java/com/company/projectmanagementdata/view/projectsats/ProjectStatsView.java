package com.company.projectmanagementdata.view.projectsats;


import com.company.projectmanagementdata.entity.ProjectStats;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;

import java.util.List;

@Route(value = "project-stats-view", layout = MainView.class)
@ViewController(id = "ProjectStatsView")
@ViewDescriptor("project-stats-view.xml")
public class ProjectStatsView extends StandardView {

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(final LoadContext loadContext) {
        return null;
    }
}