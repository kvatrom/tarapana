package com.endava.tarapana.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.endava.tarapana.core.model.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application-context.xml" })
public class TrackingRepositoryTest {

	@Autowired
	PageInfoRepository trackingEntityRepository;

	@Test
	public void addTrackingEntitySimple() {
		PageInfo trackingEntity = new PageInfo();
		trackingEntity.setName("Tarapana Page Official");

		trackingEntityRepository.save(trackingEntity);
		assertNotNull(trackingEntity.getId());

		trackingEntity = trackingEntityRepository.findOne(trackingEntity.getId());

		assertEquals("Tarapana Page Official", trackingEntity.getName());
	}

	public PageInfoRepository getTrackingEntityRepository() {
		return trackingEntityRepository;
	}

	public void setTrackingEntityRepository(PageInfoRepository trackingEntityRepository) {
		this.trackingEntityRepository = trackingEntityRepository;
	}

}
