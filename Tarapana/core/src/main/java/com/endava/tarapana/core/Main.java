package com.endava.tarapana.core;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.endava.tarapana.core.model.Test;
import com.endava.tarapana.core.repository.TestRepository;
import com.mongodb.Mongo;

public class Main {

	@Autowired
	static TestRepository repository;

	public static void main(String[] args) throws UnknownHostException {
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "database"));

		Test p = new Test("Joe", "34");

		// Insert is used to initially store the object into the database.
		mongoOps.insert(p);
		System.out.println("Insert: " + p);

		// Find
		p = mongoOps.findById(p.getId(), Test.class);
		System.out.println("Found: " + p);

		// Update
		mongoOps.updateFirst(query(where("name").is("Joe")), update("age", "35"), Test.class);
		p = mongoOps.findOne(query(where("name").is("Joe")), Test.class);
		System.out.println("Updated: " + p);

		// Delete
		mongoOps.remove(p);

		// Check that deletion worked
		List<Test> people = mongoOps.findAll(Test.class);
		System.out.println("Number of people = : " + people.size());

		Test findByName = repository.findByName("Joe");
		System.out.println(findByName);

	}
}
