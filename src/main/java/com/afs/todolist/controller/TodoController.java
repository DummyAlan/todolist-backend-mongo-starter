package com.afs.todolist.controller;

import com.afs.todolist.controller.mapper.TodoMapper;
import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    List<Todo> getAll() {
        return todoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Todo addTodo(@RequestBody Todo todo) {
        return todoService.add(todo);
    }

    //delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        if (!id.isEmpty()) {
            todoService.delete(id);
        }
        else {
            throw new InvalidIdException(id);
        }
    }

    //update
    @PutMapping("/{id}")
    Todo updateTodo(@PathVariable String id, @RequestBody Todo todo) {
        Todo expectedTodo = todoService.findById(id);
        return todoService.update(id, todo);
    }

}

//@CrossOrigin method
//@CrossOrigin controller
//
