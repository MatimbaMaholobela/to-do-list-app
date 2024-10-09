package com.impact.do_do_app.repositories;

import com.impact.do_do_app.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
    long countByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    long countByCompletedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    long countByStatus(String status);
    long countByStatusAndCreatedAtBetween(String status, LocalDateTime startDate, LocalDateTime endDate);

    List<ToDoList> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
