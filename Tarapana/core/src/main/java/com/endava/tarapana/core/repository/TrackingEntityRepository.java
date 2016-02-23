package com.endava.tarapana.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.util.Model.EntityType;

public interface TrackingEntityRepository extends MongoRepository<TrackingEntity, String> {

	public List<TrackingEntity> findAll();

	public List<TrackingEntity> findByType(EntityType entityType);

	public List<TrackingEntity> findByLikesGreaterThan(int likes);
}
