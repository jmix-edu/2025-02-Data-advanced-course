package com.company.projectmanagementdata.entity;

import com.company.projectmanagementdata.listener.TaskJpaListener;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import io.jmix.pessimisticlock.annotation.PessimisticLock;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_", indexes = {
        @Index(name = "IDX_TASK__ASSIGNEE", columnList = "ASSIGNEE_ID"),
        @Index(name = "IDX_TASK__PROJECT", columnList = "PROJECT_ID")
})
@Entity(name = "Task_")
@EntityListeners(TaskJpaListener.class)
@PessimisticLock(timeoutSec = 30000)
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @JoinColumn(name = "ASSIGNEE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User assignee;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "ESTIMATED_EFFORTS")
    private Integer estimatedEfforts;

    @JoinColumn(name = "PROJECT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "CLOSED", nullable = false)
    @NotNull
    private Boolean closed = false;
    @JmixProperty
    @Transient
    private LocalDateTime supposedEndDate;

    public void setSupposedEndDate(LocalDateTime supposedEndDate) {
        this.supposedEndDate = supposedEndDate;
    }

    public LocalDateTime getSupposedEndDate() {
        return supposedEndDate;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getEstimatedEfforts() {
        return estimatedEfforts;
    }

    public void setEstimatedEfforts(Integer estimatedEfforts) {
        this.estimatedEfforts = estimatedEfforts;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @PostLoad
    public void postLoad() {
        if (estimatedEfforts != null) {
            supposedEndDate = startDate != null
                    ? startDate
                    : LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
            supposedEndDate = supposedEndDate.plusHours(estimatedEfforts);
        }
    }
}