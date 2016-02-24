package com.endava.tarapana.core.services;

import java.util.List;

import com.endava.tarapana.core.model.TrackingEntity;

public interface TrackingEntityService {

	public TrackingEntity getTrackingEntity(String trackingEntityId);

	public TrackingEntity addTrackingEntity(TrackingEntity trackingEntity);

	public void removeTrackingEntity(String trackingEntityId);

	List<TrackingEntity> getAllTrackingEntities(String propertyNameToSort);

}
