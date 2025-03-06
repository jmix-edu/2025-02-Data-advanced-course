package com.company.projectmanagementdata.view.projectsats;


import com.company.projectmanagementdata.app.ProjectStatsService;
import com.company.projectmanagementdata.entity.ProjectStats;
import com.company.projectmanagementdata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "project-stats-view", layout = MainView.class)
@ViewController(id = "ProjectStatsView")
@ViewDescriptor("project-stats-view.xml")
public class ProjectStatsView extends StandardView {
    private static final Logger log = LoggerFactory.getLogger(ProjectStatsView.class);

    @Autowired
    private ProjectStatsService projectStatsService;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(final LoadContext loadContext) {
        log.info("Fetched stats size: " + projectStatsService.fetchProjectStatistics().size());
        return projectStatsService.fetchProjectStatistics();
    }
}