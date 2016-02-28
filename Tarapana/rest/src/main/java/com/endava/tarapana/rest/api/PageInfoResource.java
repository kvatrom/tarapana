package com.endava.tarapana.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.endava.tarapana.core.model.PageInfo;
import com.endava.tarapana.core.services.PageInfoService;

@RestController
@RequestMapping(value = "/pageInfo")
public class PageInfoResource {

	Logger logger = LoggerFactory.getLogger(PageInfoResource.class);

	private PageInfoService pageInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Data> getAllEntities() {
		List<PageInfo> allPageInfo = pageInfoService.getAllPageInfo("numberOfLikes");

		Data data = new Data();
		data.setKey("PageInfo");
		data.setValues(allPageInfo);

		List<Data> datas = new ArrayList<Data>();
		datas.add(data);

		return datas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{pageInfoId}")
	public @ResponseBody PageInfo getEntityById(@PathVariable("pageInfoId") String pageInfoId) {
		return pageInfoService.getPageInfo(pageInfoId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody PageInfo addEntity(@RequestBody PageInfo pageInfo) {
		return pageInfoService.addPageInfo(pageInfo);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{pageInfoId}")
	public void deleteEntity(@PathVariable("pageInfoId") String pageInfoId) {
		pageInfoService.removePageInfo(pageInfoId);
	}

	// GETTERS AND SETTERS
	public PageInfoService getPageInfoService() {
		return pageInfoService;
	}

	public void setPageInfoService(PageInfoService pageInfoService) {
		this.pageInfoService = pageInfoService;
	}

}