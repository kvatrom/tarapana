package com.endava.tarapana.rest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.services.TrackingEntityService;
import com.endava.tarapana.core.util.Model.Channel;
import com.endava.tarapana.core.util.Model.EntityType;

@RestController
@RequestMapping(value = "/tracking-entities")
public class TrackingEntityResource {
	private TrackingEntityService trackingEntityService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Data> getAllEntities() {
		// test only remove
		List<TrackingEntity> allTrackingEntities = trackingEntityService.getAllTrackingEntities();
		for (TrackingEntity trackingEntity : allTrackingEntities) {
			trackingEntityService.removeTrackingEntity(trackingEntity.getId());
		}
		int[] array = new Random().ints(10, 1, 1000).toArray();
		for (int i = 0; i < array.length; i++) {
			TrackingEntity fbPageStats = new TrackingEntity();
			fbPageStats.setChannel(Channel.FACEBOOK);
			fbPageStats.setLikes(array[i]);
			fbPageStats.setName("Test " + array[i]);
			fbPageStats.setRemoteId("whocares" + array[i]);
			fbPageStats.setType(EntityType.PAGE);

			trackingEntityService.addTrackingEntity(fbPageStats);
		}
		Data data = new Data();
		data.setKey("Nesto ja napravio onako");
		List<LabelValue> values = new ArrayList<LabelValue>();
		for (TrackingEntity trackingEntity : allTrackingEntities) {
			LabelValue value = new LabelValue();
			value.setLabel(trackingEntity.getName());
			value.setValue(trackingEntity.getLikes());
			values.add(value);
		}
		data.setValues(values);
		List<Data> datas = new ArrayList<Data>();
		datas.add(data);
		return datas;

		// test only remove

	}

	public TrackingEntityService getTrackingEntityService() {
		return trackingEntityService;
	}

	public void setTrackingEntityService(TrackingEntityService trackingEntityService) {
		this.trackingEntityService = trackingEntityService;
	}
}
