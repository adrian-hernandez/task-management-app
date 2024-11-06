package com.example.task_management_app.service;

import com.example.task_management_app.model.Task;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@Service
public class TaskService {

    private HTreeMap taskMap;

    @PostConstruct
    public void init() {
        // Create an in-memory database using MapDB
        DB db = DBMaker.memoryDB().make();  // .make() creates the DB

        // Correctly create a HTreeMap (ConcurrentMap) using MapDB
        taskMap = db
                .hashMap("tasks")  // "tasks" is the name of the map
                .keySerializer(Serializer.STRING) // Serializer for the key (String)
                .valueSerializer(Serializer.JAVA) // Serializer for the value (Task)
                .create();  // Use .create() instead of .make()
    }

    public Task createTask(Task task) {
        // Automatically generate a UUID for the task ID
        String taskId = UUID.randomUUID().toString();
        task.setId(taskId);  // Set the generated ID for the task

        taskMap.put(taskId, task); // Store the task in the map using the generated ID
        return task;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }
}