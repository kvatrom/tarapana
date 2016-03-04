package com.endava.tarapana.core.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.endava.tarapana.core.model.PageInfo;
import com.endava.tarapana.core.model.PostInfo;
import com.endava.tarapana.core.model.UserInfo;
import com.endava.tarapana.core.repository.PageInfoRepository;

public class PageInfoServiceImpl implements PageInfoService {

	PageInfoRepository pageInfoRepository;

	Logger logger = LoggerFactory.getLogger(PageInfoServiceImpl.class);

	DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public PageInfo getPageInfo(String pageInfoId) {
		PageInfo pageInfo = pageInfoRepository.findOne(pageInfoId);
		fillDerivedPageInformation(pageInfo);
		return pageInfo;
	}

	@Override
	public List<PageInfo> getAllPageInfo(String propertyNameToSort) {
		List<PageInfo> pageInfos = null;

		if (propertyNameToSort != null) {
			pageInfos = pageInfoRepository.findAll(new Sort(new Order(propertyNameToSort)));
		}
		else {
			pageInfos = pageInfoRepository.findAll();
		}
		for (PageInfo pageInfo : pageInfos) {
			fillDerivedPageInformation(pageInfo);
		}
		return pageInfos;
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

	// Utility methods
	@SuppressWarnings("deprecation")
	private void fillDerivedPageInformation(PageInfo pageInfo) {
		Set<UserInfo> activeLikes = new HashSet<UserInfo>();
		int maleLikes = 0;
		int femaleLikes = 0;
		int noGenderLikes = 0;

		int under20Likes = 0;
		int between20and30Likes = 0;
		int between30and40Likes = 0;
		int between40and50Likes = 0;
		int over50Likes = 0;
		int noAgeLikes = 0;

		for (PostInfo postInfo : pageInfo.getFeed()) {
			activeLikes.addAll(postInfo.getLikes());
			for (UserInfo like : postInfo.getLikes()) {
				if (like.getGender() == null) {
					noGenderLikes++;
				}
				else if (like.getGender().equalsIgnoreCase("male")) {
					maleLikes++;
				}
				else {
					femaleLikes++;
				}

				Integer likeAge = null;
				if (like.getBirthday() != null) {
					try {
						logger.debug(like.getBirthday());
						likeAge = calculateAge(format.parse(like.getBirthday()));
					}
					catch (Exception e) {
						logger.error("Date parse format error", e);
					}
				}

				if (likeAge == null) {
					noAgeLikes++;
				}
				else if (likeAge < 20) {
					under20Likes++;
				}
				else if (likeAge >= 20 && likeAge < 30) {
					between20and30Likes++;
				}
				else if (likeAge >= 30 && likeAge < 40) {
					between30and40Likes++;
				}
				else if (likeAge >= 40 && likeAge < 50) {
					between40and50Likes++;
				}
				else if (likeAge >= 50) {
					over50Likes++;
				}

			}
		}
		pageInfo.setActiveLikes(activeLikes);
		pageInfo.setNumberOfMaleActiveLikes(maleLikes);
		pageInfo.setNumberOfFemaleActiveLikes(femaleLikes);
		pageInfo.setNumberOfNoGenderActiveLikes(noGenderLikes);
		pageInfo.setActiveMembersUnder20(under20Likes);
		pageInfo.setActiveMembersBetween20and30(between20and30Likes);
		pageInfo.setActiveMembersBetween30and40(between30and40Likes);
		pageInfo.setActiveMembersBetween40and50(between40and50Likes);
		pageInfo.setActiveMembersOver50(over50Likes);

	}

	private int calculateAge(Date birthDay) {
		Calendar birthDayCal = new GregorianCalendar();
		birthDayCal.setTime(birthDay);
		logger.debug(String.valueOf(birthDayCal.get(Calendar.YEAR)));
		logger.debug(String.valueOf(birthDayCal.get(Calendar.MONTH)));
		logger.debug(String.valueOf(birthDayCal.get(Calendar.DATE)));
		LocalDate start = LocalDate.of(birthDayCal.get(Calendar.YEAR), birthDayCal.get(Calendar.MONTH) + 1,
				birthDayCal.get(Calendar.DATE));
		LocalDate end = LocalDate.now();
		long years = ChronoUnit.YEARS.between(start, end);
		logger.debug(String.valueOf(years));
		return (int) years;
	}

	// GETTERS AND SETTERS
	public PageInfoRepository getPageInfoRepository() {
		return pageInfoRepository;
	}

	public void setPageInfoRepository(PageInfoRepository pageInfoRepository) {
		this.pageInfoRepository = pageInfoRepository;
	}
}
