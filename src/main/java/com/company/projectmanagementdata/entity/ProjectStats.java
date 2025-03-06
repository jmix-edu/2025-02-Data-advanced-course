package com.company.projectmanagementdata.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class ProjectStats {
    private String projectNane;

    private Integer tasksCount;

    private Integer plannedEfforts;

    private Integer actualEfforts;

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlannedEfforts() {
        return plannedEfforts;
    }

    public void setPlannedEfforts(Integer plannedEfforts) {
        this.plannedEfforts = plannedEfforts;
    }

    public Integer getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

    public String getProjectNane() {
        return projectNane;
    }

    public void setProjectNane(String projectNane) {
        this.projectNane = projectNane;
    }
}