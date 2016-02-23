package com.endava.tarapana.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.endava.tarapana.core.model.TrackingEntity;

public interface TrackingEntityRepository extends MongoRepository<TrackingEntity, String> {

	public List<TrackingEntity> findAll();

	public List<TrackingEntity> findByLikesGreaterThan(int likes);
}
