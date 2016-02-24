package com.endava.tarapana.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.services.TrackingEntityService;

@RestController
@RequestMapping(value = "/tracking-entities")
public class TrackingEntityResource {

	Logger logger = LoggerFactory.getLogger(TrackingEntityResource.class);

	private TrackingEntityService trackingEntityService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<TrackingEntity> getAllEntities() {
		return trackingEntityService.getAllTrackingEntities();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{trackingEntityId}")
	public @ResponseBody TrackingEntity getEntityById(@PathVariable("trackingEntityId") String trackingEntityId) {
		return trackingEntityService.getTrackingEntity(trackingEntityId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody TrackingEntity addEntity(@RequestBody TrackingEntity trackingEntity) {
		return trackingEntityService.addTrackingEntity(trackingEntity);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{trackingEntityId}")
	public void deleteEntity(@PathVariable("trackingEntityId") String trackingEntityId) {
		trackingEntityService.removeTrackingEntity(trackingEntityId);
	}

	public TrackingEntityService getTrackingEntityService() {
		return trackingEntityService;
	}

	public void setTrackingEntityService(TrackingEntityService trackingEntityService) {
		this.trackingEntityService = trackingEntityService;
	}
}
