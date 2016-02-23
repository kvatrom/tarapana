package com.endava.tarapana.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.endava.tarapana.core.model.TrackingEntity;
import com.endava.tarapana.core.util.Model.EntityType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application-context.xml" })
public class TrackingRepositoryTest {

	@Autowired
	TrackingEntityRepository trackingEntityRepository;

	@Test
	public void addTrackingEntitySimple() {
		TrackingEntity trackingEntity = new TrackingEntity();
		trackingEntity.setLikes(100);
		trackingEntity.setName("Tarapana Page Official");
		trackingEntity.setRemoteId("123123123123123");
		trackingEntity.setType(EntityType.PAGE);

		trackingEntityRepository.save(trackingEntity);
		assertNotNull(trackingEntity.getId());

		trackingEntity = trackingEntityRepository.findOne(trackingEntity.getId());

		assertEquals("Tarapana Page Official", trackingEntity.getName());
		assertEquals("123123123123123", trackingEntity.getRemoteId());
		assertEquals(EntityType.PAGE, trackingEntity.getType());
		assertEquals(100, trackingEntity.getLikes());
	}

	public TrackingEntityRepository getTrackingEntityRepository() {
		return trackingEntityRepository;
	}

	public void setTrackingEntityRepository(TrackingEntityRepository trackingEntityRepository) {
		this.trackingEntityRepository = trackingEntityRepository;
	}

}
