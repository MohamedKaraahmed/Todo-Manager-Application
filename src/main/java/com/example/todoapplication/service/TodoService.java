package com.example.todoapplication.service;

import com.example.todoapplication.entities.Todo;
import com.example.todoapplication.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ServiceInterface {
    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public Todo saveTodo(Todo todo) {
        return this.todoRepository.save(todo);
    }

    @Override
    public void deleteTodoById(long id) {
        boolean isIdExistInDb = todoRepository.existsById(id);
        if (!isIdExistInDb) {
            throw new IllegalArgumentException("Invalid id provided for deletion!");
        }
        this.todoRepository.deleteById(id);
    }

    @Override
    public Todo findById(long id) {
        return this.todoRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id!"));
    }

    @Override
    public List<Todo> findAll() {
        return this.todoRepository.findAll();
    }

    @Override
    public List<Todo> findByCompleted(boolean completed) {
        return todoRepository.findAllByCompleted(completed);
    }
}
