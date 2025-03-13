package com.company.projectmanagementdata.repository;

import com.company.projectmanagementdata.entity.Task;
import io.jmix.core.repository.JmixDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JmixDataRepository<Task, UUID> {

    List<Task> findByNameContainingIgnoreCase(String part);
}