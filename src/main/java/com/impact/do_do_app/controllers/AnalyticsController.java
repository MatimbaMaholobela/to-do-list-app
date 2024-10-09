package com.impact.do_do_app.controllers;

import com.impact.do_do_app.services.ToDoListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class AnalyticsController {

    @Autowired
    private ToDoListServices toDoListServices;

    @GetMapping("/analytics")
    public String getAnalytics(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {

        // Default to the last 7 days if dates aren't provided
        if (startDate == null || endDate == null) {
            endDate = LocalDateTime.now();
            startDate = endDate.minusDays(7);
        }

        // Prepare the analytics data
        model.addAttribute("formattedStartDate", startDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        model.addAttribute("formattedEndDate", endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        model.addAttribute("createdCount", toDoListServices.countTasksCreatedBetween(startDate, endDate));
        model.addAttribute("completedCount", toDoListServices.countTasksCompletedBetween(startDate, endDate));
        model.addAttribute("notStartedCount", toDoListServices.countTasksByStatus("Not Started"));
        model.addAttribute("inProgressCount", toDoListServices.countTasksByStatus("In Progress"));
        model.addAttribute("completedStatusCount", toDoListServices.countTasksByStatus("Completed"));
        model.addAttribute("onHoldCount", toDoListServices.countTasksByStatus("On Hold"));

        return "analytics";  // The Thymeleaf template to render
    }
}
