package com.example.taskify.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapdb.HTreeMap;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.taskify.model.Task;
import com.example.taskify.service.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private HTreeMap<String, Task> taskMap;  // Mocking taskMap

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTasks() {
        // Arrange: Set up a list of tasks
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(new Task("1", "Task 1", "Description 1"));
        mockTasks.add(new Task("2", "Task 2", "Description 2"));

        // Mock taskMap behavior
        when(taskMap.values()).thenReturn(mockTasks);

        // Act: Call the service method
        List<Task> tasks = taskService.getTasks();

        // Assert: Check the result
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Description 1", tasks.get(0).getDescription());
        verify(taskMap, times(1)).values();
    }

    @Test
    public void testAddTask() {
        // Arrange: Create a new task to add
        Task newTask = new Task(null, "New Task", "New Description");
        Task savedTask = new Task("1", "New Task", "New Description");

        // Mock taskMap behavior
        when(taskMap.put(anyString(), any(Task.class))).thenReturn(savedTask);

        // Act: Add the task
        Task result = taskService.createTask(newTask);

        // Assert: Verify that the task was saved
        assertNotNull(result.getId());
        assertEquals("New Task", result.getTitle());
        verify(taskMap, times(1)).put(anyString(), any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        // Arrange: Set up a task to delete
        String taskId = "1";
        Task taskToDelete = new Task(taskId, "Task to delete", "Description");
        when(taskMap.remove(taskId)).thenReturn(taskToDelete);

        // Act: Delete the task
        boolean result = taskService.deleteTask(taskId);

        // Assert: Verify that the task was deleted
        assertTrue(result);
        verify(taskMap, times(1)).remove(taskId);
    }

    @Test
    public void testUpdateTask() {
        // Arrange: Set up an existing task and an updated task
        String taskId = "1";
        Task existingTask = new Task(taskId, "Existing Task", "Old Description");
        Task updatedTask = new Task(taskId, "Updated Task", "New Description");

        // Mock taskMap behavior using the getter method
        when(taskMap.put(eq(taskId), any(Task.class))).thenReturn(updatedTask);

        // Act: Update the task
        Task result = taskService.updateTask(updatedTask);

        // Assert: Check if the task was updated
        assertEquals("Updated Task", result.getTitle());
        assertEquals("New Description", result.getDescription());
        verify(taskMap, times(1)).put(eq(taskId), any(Task.class));  // Use eq for taskId and any() for Task
    }
}
