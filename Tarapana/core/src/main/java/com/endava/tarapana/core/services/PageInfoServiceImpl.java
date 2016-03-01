package com.endava.tarapana.core.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.endava.tarapana.core.model.PageInfo;
import com.endava.tarapana.core.repository.PageInfoRepository;

public class PageInfoServiceImpl implements PageInfoService {

	PageInfoRepository pageInfoRepository;

	@Override
	public PageInfo getPageInfo(String pageInfoId) {
		return pageInfoRepository.findOne(pageInfoId);
	}

	@Override
	public List<PageInfo> getAllPageInfo(String propertyNameToSort) {
		if (propertyNameToSort != null) {
			return pageInfoRepository.findAll(new Sort(new Order(propertyNameToSort)));
		}
		else {
			return pageInfoRepository.findAll();
		}
	}

	@Override
	public PageInfo addPageInfo(PageInfo pageInfoId) {
		return pageInfoRepository.save(pageInfoId);
	}

	@Override
	public void removePageInfo(String pageInfoIdId) {
		PageInfo fbPageStats = pageInfoRepository.findOne(pageInfoIdId);
		if (fbPageStats != null) {
			pageInfoRepository.delete(fbPageStats);
		}
	}

	// GETTERS AND SETTERS
	public PageInfoRepository getPageInfoRepository() {
		return pageInfoRepository;
	}

	public void setPageInfoRepository(PageInfoRepository pageInfoRepository) {
		this.pageInfoRepository = pageInfoRepository;
	}
}
