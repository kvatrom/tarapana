package com.endava.tarapana.core.services;

import java.util.List;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.repository.TrackingEntityRepository;

public class TrackingEntityServiceImpl implements TrackingEntityService {

	TrackingEntityRepository trackingEntityRepository;

	@Override
	public TrackingEntity getTrackingEntity(String fbPageStatsId) {
		return trackingEntityRepository.findOne(fbPageStatsId);
	}

	@Override
	public List<TrackingEntity> getAllTrackingEntities() {
		return trackingEntityRepository.findAll();
	}

	@Override
	public TrackingEntity addTrackingEntity(TrackingEntity fbPageStats) {
		return trackingEntityRepository.save(fbPageStats);
	}

	@Override
	public void removeTrackingEntity(String fbPageStatsId) {
		TrackingEntity fbPageStats = trackingEntityRepository.findOne(fbPageStatsId);
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
