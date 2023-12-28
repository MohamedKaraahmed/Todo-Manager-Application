package com.example.todoapplication.repository;

import com.example.todoapplication.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("from Todo as td where td.isCompleted = :isCompleted")
    List<Todo> findAllByCompleted(boolean isCompleted);

}