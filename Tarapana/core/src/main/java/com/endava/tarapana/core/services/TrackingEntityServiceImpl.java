package com.endava.tarapana.core.services;

import java.util.List;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.repository.TrackingEntityRepository;

public class TrackingEntityServiceImpl implements TrackingEntityService {

	TrackingEntityRepository trackingEntityRepository;

	@Override
	public TrackingEntity getTrackingEntity(String trackingEntityId) {
		return trackingEntityRepository.findOne(trackingEntityId);
	}

	@Override
	public List<TrackingEntity> getAllTrackingEntities() {
		return trackingEntityRepository.findAll();
	}

	@Override
	public TrackingEntity addTrackingEntity(TrackingEntity trackingEntity) {
		return trackingEntityRepository.save(trackingEntity);
	}

	@Override
	public void removeTrackingEntity(String trackingEntityId) {
		TrackingEntity fbPageStats = trackingEntityRepository.findOne(trackingEntityId);
		if (fbPageStats != null) {
			trackingEntityRepository.delete(fbPageStats);
		}
	}

	// GETTERS AND SETTERS
	public TrackingEntityRepository getTrackingEntityRepository() {
		return trackingEntityRepository;
	}

	public void setTrackingEntityRepository(TrackingEntityRepository trackingEntityRepository) {
		this.trackingEntityRepository = trackingEntityRepository;
	}
}
