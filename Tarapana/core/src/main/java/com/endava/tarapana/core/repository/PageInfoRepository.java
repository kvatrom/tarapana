package com.endava.tarapana.core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.endava.tarapana.core.model.PageInfo;

public interface PageInfoRepository extends MongoRepository<PageInfo, String> {

	public List<PageInfo> findByNumberOfLikesGreaterThan(int likes);
}
