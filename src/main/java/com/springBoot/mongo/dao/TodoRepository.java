package com.springBoot.mongo.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.springBoot.mongo.model.User;

@Repository
public interface TodoRepository extends MongoRepository<User, String> {

	@Query("{'todo':?0}")
	Optional<User> findByTodo(String todo);

}
