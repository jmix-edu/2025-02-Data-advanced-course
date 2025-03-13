package com.company.projectmanagementdata.app;

import com.company.projectmanagementdata.entity.Task;
import com.company.projectmanagementdata.repository.TaskRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RepositoryDemoService {

    private final TaskRepository taskRepository;

    public RepositoryDemoService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task loadTask(UUID taskId) {
        return taskRepository.getById(taskId);
    }

    public List<Task> loadTask(String namePart) {
        return taskRepository.findByNameContainingIgnoreCase(namePart);
    }


}