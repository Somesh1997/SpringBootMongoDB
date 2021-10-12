package com.springBoot.mongo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.springBoot.mongo.exception.TodoCoollectionException;
import com.springBoot.mongo.model.User;

public interface TodoService {
	public void createTodo(User user) throws ConstraintViolationException, TodoCoollectionException;

	public List<User> getAllTodos();
}