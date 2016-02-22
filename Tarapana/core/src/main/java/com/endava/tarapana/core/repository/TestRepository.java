package com.endava.tarapana.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.endava.tarapana.core.model.Test;

public interface TestRepository extends MongoRepository<Test, String> {

	public Test findByName(String name);

}