package com.impact.do_do_app.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name="TO_DO_LIST")
public class ToDoList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String status;

    public ToDoList(){

    }
    public ToDoList(String name,String description,LocalDateTime createdAt, LocalDateTime completedAt, String status){
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
