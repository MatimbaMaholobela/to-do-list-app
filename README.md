## Objective
Develop a To-Do List Application using Java and Spring to help users organize daily objectives. The application will also track user progress over time, offering insights into productivity trends.

## Features
Core Functionality
CRUD Operations:
Create, Read, Update, and Delete tasks.
Task Model:
Each task includes:
Unique ID: Identifier for the task.
Name: The title or description of the task.
Created_at: Timestamp for when the task was created.
Completed_at: Timestamp for when the task was marked as completed.
Status: Tracks if the task is pending or completed.
## Frontend Pages
To-Do List Page:
Task List: Displays all tasks.
Checkbox: Allows users to mark tasks as complete.
Delete Button: Removes tasks.
Add Task Form: Enables users to add new tasks.
Analytics Page:
Displays:
Number of tasks created in a specific time frame.
Number of tasks completed in a specific time frame.
Date Range Filtering:
Default: Last 7 days.
Customizable: Query parameters to filter tasks by specific date ranges.
Technical Overview
## Implementation
Programming Language: Java
Framework: Spring Framework
Database: SQLite (preferred but configurable)
Frontend: Template-based rendering (e.g., Thymeleaf, FreeMarker, or JSP).
## API Design
No RESTful API required; HTML is server-rendered.
No authentication required; assume all requests are from the same user.
