package com.springBoot.mongo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.mongo.dao.TodoRepository;
import com.springBoot.mongo.exception.TodoCoollectionException;
import com.springBoot.mongo.model.User;
import com.springBoot.mongo.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository repository;

	@Autowired
	private TodoService service;

	@GetMapping("/todos")
	public ResponseEntity getAllTodos() {
		List<User> users = service.getAllTodos();
		if (users.size() > 0) {
			return new ResponseEntity(users, HttpStatus.OK);
		} else {
			return new ResponseEntity("No Todos Available", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody User user) {
		try {
			service.createTodo(user);
			return new ResponseEntity(user, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCoollectionException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getTodoById(@PathVariable String id) {

		Optional<User> optionaltdu = repository.findById(id);
		if (optionaltdu.isPresent()) {
			return new ResponseEntity(optionaltdu.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity("ToDo Not Found id " + id, HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody User user) {
		Optional<User> optionaltdu = repository.findById(id);
		if (optionaltdu.isPresent()) {
			User todoToSave = optionaltdu.get();
			todoToSave.setCompleted(user.getCompleted() != null ? user.getCompleted() : todoToSave.getCompleted());
			todoToSave.setCreatedAt(user.getCreatedAt() != null ? user.getCreatedAt() : todoToSave.getCreatedAt());
			todoToSave.setDesc(user.getDesc() != null ? user.getDesc() : todoToSave.getDesc());
			todoToSave.setTodo(user.getTodo() != null ? user.getTodo() : todoToSave.getTodo());
			todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			repository.save(todoToSave);
			return new ResponseEntity(todoToSave, HttpStatus.OK);
		} else {
			return new ResponseEntity("ToDo Not Found id " + id, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity("SuccessFully Deleted with id : " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
