package com.example.taskify.service;

import org.mapdb.HTreeMap;
import org.springframework.stereotype.Service;

import com.example.taskify.model.Task;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private HTreeMap<String, Task> taskMap;

    @PostConstruct
    public void init() {
        // Initialize taskMap with real in-memory HTreeMap and specify types
        taskMap = org.mapdb.DBMaker.memoryDB()
                .make()
                .hashMap("tasks")
                .keySerializer(org.mapdb.Serializer.STRING)
                .valueSerializer(org.mapdb.Serializer.JAVA)
                .create();
    }

    public Task createTask(Task task) {
        String taskId = UUID.randomUUID().toString();
        task.setId(taskId);
        taskMap.put(taskId, task);
        return task;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public boolean deleteTask(String id) {
        try {
            taskMap.remove(id);
            return true;
        } catch(Exception e){
            System.out.println("An error occurred while deleting task " + id + " " + e.getMessage());
            return false;
        }
    }

    public Task updateTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    // Getter method for taskMap for testing purposes
    public HTreeMap<String, Task> getTaskMap() {
        return taskMap;
    }
}