package com.example.taskify.service;

import com.example.taskify.model.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getTasks();
    boolean deleteTask(String id);
    Task updateTask(Task task);
}