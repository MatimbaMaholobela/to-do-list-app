package com.impact.do_do_app;
import com.impact.do_do_app.models.ToDoList;
import com.impact.do_do_app.repositories.ToDoListRepository;
import com.impact.do_do_app.services.ToDoListServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class ToDoListServiceImpTest {

    @Mock
    private ToDoListRepository toDoListRepository;

    @InjectMocks
    private ToDoListServiceImp toDoListServiceImp;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    // Test case: create a task
    @Test
    void testCreateTask() {
        // Arrange
        ToDoList createEntityTask = new ToDoList("Create Entity", "To-Do list entity", LocalDateTime.now(), null, "Completed");
        when(toDoListRepository.save(createEntityTask)).thenReturn(createEntityTask);

        // created a service
        ToDoList createdTask = toDoListServiceImp.createTask(createEntityTask);

        //
        assertNotNull(createdTask);
        assertEquals("Create Entity", createdTask.getName());
        verify(toDoListRepository, times(1)).save(createEntityTask);
    }

    // Test case: updating a task
    @Test
    void testUpdateTask() {


        ToDoList existingTask = new ToDoList("Create Task 1", "this is a test", LocalDateTime.now(), null, "Not Started");
        existingTask.setId(1L);
        when(toDoListRepository.existsById(1L)).thenReturn(true);
        when(toDoListRepository.save(existingTask)).thenReturn(existingTask);


        ToDoList updatedTask = toDoListServiceImp.updateTask(1L, existingTask);

        assertEquals("Create Task 1", updatedTask.getName());
        verify(toDoListRepository, times(1)).save(existingTask);
    }

    // Test case: deleting a task
    @Test
    void testDeleteTask() {

        when(toDoListRepository.existsById(1L)).thenReturn(true);
        toDoListServiceImp.deleteTask(1L);

        verify(toDoListRepository, times(1)).deleteById(1L);
    }

    // Test case: retrieving a task by id
    @Test
    void testGetTask() {

        ToDoList task = new ToDoList("Task 1", "Description 1", LocalDateTime.now(), null, "Not Started");
        when(toDoListRepository.findById(1L)).thenReturn(Optional.of(task));


        Optional<ToDoList> foundTask = toDoListServiceImp.getTask(1L);

        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getName());
    }

    // Test case:  getting all tasks
    @Test
    void testGetAllTasks() {

        ToDoList task1 = new ToDoList("Task 1", "Description 1", LocalDateTime.now(), null, "Not Started");
        ToDoList task2 = new ToDoList("Task 2", "Description 2", LocalDateTime.now(), null, "In Progress");
        when(toDoListRepository.findAll()).thenReturn(Arrays.asList(task1, task2));


        List<ToDoList> tasks = toDoListServiceImp.getAllTasks();
        assertEquals(2, tasks.size());
    }

    // Test case:  updating task status
    @Test
    void testUpdateTaskStatus() {

        ToDoList existingTask = new ToDoList("Task 1", "Description 1", LocalDateTime.now(), null, "Not Started");
        when(toDoListRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(toDoListRepository.save(existingTask)).thenReturn(existingTask);

        ToDoList updatedTask = toDoListServiceImp.updateTaskStatus(1L, "In Progress");

        assertEquals("In Progress", updatedTask.getStatus());
        verify(toDoListRepository, times(1)).save(existingTask);
    }
}
