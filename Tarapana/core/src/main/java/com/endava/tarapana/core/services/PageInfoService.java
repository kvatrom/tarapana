package com.endava.tarapana.core.services;

import java.util.List;

import com.endava.tarapana.core.model.PageInfo;

public interface PageInfoService {

	public PageInfo getPageInfo(String pageInfoId);

	public PageInfo addPageInfo(PageInfo pageInfo);

	public void removePageInfo(String pageInfoId);

	List<PageInfo> getAllPageInfo(String propertyNameToSort);

}
