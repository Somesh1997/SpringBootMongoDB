package com.springBoot.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.mongo.dao.TodoRepository;
import com.springBoot.mongo.exception.TodoCoollectionException;
import com.springBoot.mongo.model.User;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository repository;

	@Override
	public void createTodo(User user) throws ConstraintViolationException, TodoCoollectionException {
		Optional<User> opTodoDTU = repository.findByTodo(user.getTodo());
		if (opTodoDTU.isPresent()) {
			throw new TodoCoollectionException(TodoCoollectionException.TodoAlreadyExists());
		} else {
			user.setCreatedAt(new Date(System.currentTimeMillis()));
			repository.save(user);
		}
	}

	@Override
	public List<User> getAllTodos() {
		// TODO Auto-generated method stub
		List<User> users = repository.findAll();
		if (users.size() > 0) {
			return users;
		} else {
			return new ArrayList<>();
		}
		// return null;
	}

	@Override
	public User getSingleTodo(String id) throws TodoCoollectionException {

		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new TodoCoollectionException(TodoCoollectionException.NotFoundException(id));
		}

		// return null;
	}

}
