package com.impact.do_do_app;


import com.impact.do_do_app.models.ToDoList;
import com.impact.do_do_app.repositories.ToDoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DoDoAppApplication.class)
public class ToDoListRepositoryTest {

    @Autowired
    private ToDoListRepository toDoListRepository;


    @Test
    public void testEmptyEntity(){
        //ensure emptiness
        toDoListRepository.deleteAll();

        Iterable<ToDoList> appItems = toDoListRepository.findAll();
        assertThat(appItems).isEmpty();
    }

    @Test
    public void testCreateItem(){
        // Create a new to-do entity

        ToDoList impactApp = new ToDoList();
        impactApp.setName("Impact App");
        impactApp.setStatus("In Progress");

        // save the entity
        ToDoList task1 = toDoListRepository.save(impactApp);

        Optional<ToDoList> getApp = toDoListRepository.findById(task1.getId());
        assertThat(getApp.isPresent()).isTrue();

        // test if app name and status are being retrieved correctly.
        assertThat(getApp.get().getName()).isEqualTo("Impact App");
        assertThat(getApp.get().getStatus()).isEqualTo("In Progress");
    }

    @Test
    public void testDeleteItem(){
        ToDoList task2 = new ToDoList();
        task2.setName("Impact App");
        task2.setDescription("Impact App Description");
        task2.setStatus("In Progress");

        ToDoList saveTask = toDoListRepository.save(task2);

        //delete entity
        toDoListRepository.deleteById(saveTask.getId());

        //verify if deleted
        Optional<ToDoList> deletedTask = toDoListRepository.findById(saveTask.getId());
        // use isPresent to fail the test.
        assertThat(deletedTask.isEmpty()).isTrue();

    }

    //ANALYSIS TESTS

    @Test
    public void testCountTasksByDateRange() {

        //clear the repo first
        toDoListRepository.deleteAll();

        ToDoList task1 = new ToDoList();
        task1.setName("Task 1");
        task1.setStatus("Completed");
        task1.setCreatedAt(LocalDateTime.now().minusDays(10));
        toDoListRepository.save(task1);

        ToDoList task2 = new ToDoList();
        task2.setName("Task 2");
        task2.setStatus("In Progress");
        task2.setCreatedAt(LocalDateTime.now().minusDays(5));
        toDoListRepository.save(task2);

        ToDoList task3 = new ToDoList();
        task3.setName("Task 3");
        task3.setStatus("Completed");
        task3.setCreatedAt(LocalDateTime.now().minusDays(2));
        toDoListRepository.save(task3);

        // Test for last 7 days
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();

        List<ToDoList> tasksCreated = toDoListRepository.findByCreatedAtBetween(startDate, endDate);
        long countCreated = tasksCreated.size();
        long countCompleted = tasksCreated.stream()
                .filter(task -> "Completed".equals(task.getStatus()))
                .count();

        assertThat(countCreated).isEqualTo(2);
        assertThat(countCompleted).isEqualTo(1);
    }

    @Test
    public void testCountTasksByStatus() {

        //ensure that the repo is empty
        toDoListRepository.deleteAll();

        ToDoList task1 = new ToDoList();
        task1.setName("Task 1");
        task1.setStatus("Completed");
        toDoListRepository.save(task1);

        ToDoList task2 = new ToDoList();
        task2.setName("Task 2");
        task2.setStatus("In Progress");
        toDoListRepository.save(task2);

        ToDoList task3 = new ToDoList();
        task3.setName("Task 3");
        task3.setStatus("Completed");
        toDoListRepository.save(task3);


        long countCompleted = toDoListRepository.countByStatus("Completed");
        long countInProgress = toDoListRepository.countByStatus("In Progress");

        assertThat(countCompleted).isEqualTo(2);
        assertThat(countInProgress).isEqualTo(1);
    }

//
//    @Test
//    public void testFindByCreatedAtBetween() {
//        LocalDateTime start = LocalDateTime.now().minusDays(7);
//        LocalDateTime end = LocalDateTime.now();
//
//        ToDoList task1 = new ToDoList();
//        task1.setName("Task 1");
//        task1.setStatus("In Progress");
//        task1.setCreatedAt(LocalDateTime.now().minusDays(5));
//        toDoListRepository.save(task1);
//
//        List<ToDoList> tasks = toDoListRepository.findByCreatedAtBetween(start, end);
//
//        assertNotNull(tasks);
//        assertThat(tasks.size()).isEqualTo(10);
//    }


}
