package com.impact.do_do_app.controllers;

import com.impact.do_do_app.models.ToDoList;
import com.impact.do_do_app.services.ToDoListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class ToDoListControllers {

    private final ToDoListServices toDoListService;

    @Autowired
    public ToDoListControllers(ToDoListServices toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        // used to display all tasks in the index.

        List<ToDoList> tasks = toDoListService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute ToDoList task) {

        //create new task
        toDoListService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update-status")
    public String updateTaskStatus(@RequestParam Long id, @RequestParam String status) {
        // Fetch the existing task
        ToDoList existingTask = toDoListService.getTask(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if the current status is "Completed"
        if ("Completed".equals(existingTask.getStatus())) {
            // If moving away from "Completed", clear the completed date
            toDoListService.clearCompletedDate(id);
        }

        // Update the task status
        toDoListService.updateTaskStatus(id, status);

        // Set completed date if the new status is "Completed"
        if ("Completed".equals(status)) {
            toDoListService.setCompletedDate(id);
        }

        return "redirect:/tasks";
    }


    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        // Fetch the task by ID and pass it to the view
        ToDoList task = toDoListService.getTask(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        return "edit-task";  // Ensure this template exists in the templates directory
    }


    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute ToDoList updatedTask) {
        // Fetch the existing task from the service
        ToDoList existingTask = toDoListService.getTask(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setName(updatedTask.getName());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        // Don't update createdAt

        toDoListService.updateTask(id, existingTask);

        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id) {

        //delete a task
        toDoListService.deleteTask(id);
        return "redirect:/tasks";
    }
}
