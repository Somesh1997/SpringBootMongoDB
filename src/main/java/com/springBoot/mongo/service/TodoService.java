package com.springBoot.mongo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.springBoot.mongo.exception.TodoCoollectionException;
import com.springBoot.mongo.model.User;

public interface TodoService {
	public void createTodo(User user) throws ConstraintViolationException, TodoCoollectionException;

	public List<User> getAllTodos();

	public User getSingleTodo(String id) throws TodoCoollectionException;

	public void updateTodo(String id, User user) throws TodoCoollectionException;
	public void deleteTodoById(String id) throws TodoCoollectionException;
}
