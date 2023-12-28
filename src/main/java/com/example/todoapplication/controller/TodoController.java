package com.example.todoapplication.controller;

import com.example.todoapplication.entities.Todo;
import com.example.todoapplication.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String listTodos(@RequestParam(required = false) boolean completed, Model model) {
        List<Todo> todos;
        if (completed) {
            todos = todoService.findByCompleted(completed);
        } else {
            todos = todoService.findAll();
        }
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/todo/add")
    public String addTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoEdit";
    }

    @PostMapping("/todo/add")
    @Validated
    public String addTodo(@Valid @ModelAttribute Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "todoEdit";
        }

        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("todo", todoService.findById(id));

        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "redirect:/";
        }
        return "todoEdit";
    }

    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable Long id, @Valid @ModelAttribute Todo todo, BindingResult result, Model model) {
        //must validate todo
        if (result.hasErrors()) {
            return "todoEdit";
        }
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }


}
