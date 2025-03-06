package com.company.projectmanagementdata.app;

import com.company.projectmanagementdata.entity.Project;
import com.company.projectmanagementdata.entity.ProjectStats;
import com.company.projectmanagementdata.entity.Task;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectStatsService {

    private final DataManager dataManager;

    public ProjectStatsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<ProjectStats> fetchProjectStatistics() {
        List<Project> projects = dataManager.load(Project.class).all().list();
        List<ProjectStats> statsList = projects.stream()
                .map(project -> {
                    ProjectStats stats = dataManager.create(ProjectStats.class);
                    stats.setProjectName(project.getName());

                    List<Task> tasks = project.getTasks();
                    stats.setTasksCount(tasks.size());

                    Integer estimatedEfforts = tasks.stream()
                            .map(task -> task.getEstimatedEfforts() == null
                                    ? 0
                                    : task.getEstimatedEfforts())
                            .reduce(0, Integer::sum);
                    stats.setPlannedEfforts(estimatedEfforts);
                    stats.setActualEfforts(getActualEfforts(project.getId()));
                    return stats;

                })
                .toList();

        return statsList;
    }

    private Integer getActualEfforts(UUID id) {
        return dataManager.loadValue("select sum(te.timeSpent) from TimeEntry te " +
                        "where te.task.project.id = :projectId", Integer.class)
                .parameter("projectId", id)
                .one();
    }
}