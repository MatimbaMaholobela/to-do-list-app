package com.impact.do_do_app.services;

import com.impact.do_do_app.models.ToDoList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ToDoListServices {

    // create a new task
    ToDoList createTask(ToDoList task);

    // update the task with new information
    ToDoList updateTask(Long id, ToDoList updatedTask);

    //delete task using id
    void deleteTask(Long id);

    // find task using id
    Optional<ToDoList> getTask(Long id);

    //get all tasks
    List<ToDoList> getAllTasks();

    //update task status
    ToDoList updateTaskStatus(Long id, String status);

    //update task date
    ToDoList setCompletedDate(Long id);

    ToDoList clearCompletedDate(Long id);

    // Get count of tasks created between two dates
    long countTasksCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Get count of tasks completed between two dates
    long countTasksCompletedBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Get count of tasks per status between two dates
    long countTasksByStatusBetween(String status, LocalDateTime startDate, LocalDateTime endDate);

    long countTasksByStatus(String status);
}
