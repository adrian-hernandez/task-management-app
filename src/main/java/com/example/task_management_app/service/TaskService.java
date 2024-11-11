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

    private HTreeMap<String, Task> taskMap;

    @PostConstruct
    public void init() {
        DB db = DBMaker.memoryDB().make();
        taskMap = db.hashMap("tasks")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.JAVA)
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

    public void deleteTask(String id) {
        taskMap.remove(id);
    }

    public Task updateTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }
}