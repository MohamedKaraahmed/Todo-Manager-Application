package com.example.todoapplication.service;

import com.example.todoapplication.entities.Todo;

import java.util.List;

public interface ServiceInterface {
    Todo saveTodo(Todo todo);

    void deleteTodoById(long id);

    Todo findById(long id);

    List<Todo> findAll();

    List<Todo>findByCompleted(boolean completed);
}
