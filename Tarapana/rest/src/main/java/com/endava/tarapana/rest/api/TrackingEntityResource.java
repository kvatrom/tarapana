package com.endava.tarapana.rest.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.services.TrackingEntityService;

@RestController
@RequestMapping(value = "/tracking-entities")
public class TrackingEntityResource {
	private TrackingEntityService trackingEntityService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<TrackingEntity> getAllEntities() {
		return trackingEntityService.getAllTrackingEntities();
	}

	public TrackingEntityService getTrackingEntityService() {
		return trackingEntityService;
	}

	public void setTrackingEntityService(TrackingEntityService trackingEntityService) {
		this.trackingEntityService = trackingEntityService;
	}
}
