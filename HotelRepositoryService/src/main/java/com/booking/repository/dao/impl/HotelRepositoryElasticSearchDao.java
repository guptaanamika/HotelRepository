package com.booking.repository.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.booking.repository.converter.HotelInfoConverter;
import com.booking.repository.dao.HotelRepositoryDao;
import com.booking.repository.dao.entity.HotelSearchEntity;
import com.booking.repository.entity.HotelInfo;
import com.booking.repository.search.HotelSearchRepository;

/**
 * @author anamika.gupta created_on : 16-Feb-2017
 **/
@Component("HotelRepositoryElasticSearchDao")
public class HotelRepositoryElasticSearchDao implements HotelRepositoryDao {

	@Autowired
	private HotelSearchRepository searchRepository;

	public String addHotel(HotelInfo hotelInfo) {
		HotelSearchEntity hotelSearchEntity = HotelInfoConverter.toHotelSearchEntity(hotelInfo);
		searchRepository.save(hotelSearchEntity);
		return hotelInfo.getId();
	}

	public HotelInfo getHotelById(String id) {
		HotelSearchEntity hotelSearchEntity = searchRepository.getById(id);
		HotelInfo hotelInfo = HotelInfoConverter.toHotelInfo(hotelSearchEntity);
		return hotelInfo;
	}

	public void removeHotelById(String id) {
		searchRepository.delete(id);
	}

	public void updateHotelById(HotelInfo hotelInfo) {
		HotelSearchEntity hotelSearchEntity = HotelInfoConverter.toHotelSearchEntity(hotelInfo);
		hotelSearchEntity.setVersion(hotelInfo.getVersion() + 1);
		searchRepository.save(hotelSearchEntity);
	}

	@Override
	public List<HotelInfo> getPaginatedHotelSearchList(HotelInfo hotelInfo, Integer pageCount, Integer pageStartPoint) {
		Pageable pageable = new PageRequest(pageStartPoint, pageCount);
		String name = hotelInfo.getName();
		String city = hotelInfo.getAddress().getCity();
		Integer starRanking = hotelInfo.getStarRanking();
		List<HotelSearchEntity> result = null;

		if (name != null && city != null && starRanking != null)
			result = searchRepository.findByAddressCityAndStarRankingAndNameLike(city, starRanking, name, pageable);
		else if (name != null && city == null && starRanking == null)
			result = searchRepository.findByNameLike(name, pageable);
		else if (name == null && city != null && starRanking == null)
			result = searchRepository.findByAddressCity(city, pageable);
		else if (name == null && city == null && starRanking != null)
			result = searchRepository.findByStarRanking(starRanking, pageable);
		else if (name != null && city != null && starRanking == null)
			result = searchRepository.findByAddressCityAndNameLike(city, name, pageable);
		else if (name == null && city != null && starRanking != null)
			result = searchRepository.findByAddressCityAndStarRanking(city, starRanking, pageable);
		else if (name != null && city == null && starRanking != null)
			result = searchRepository.findByStarRankingAndNameLike(starRanking, name, pageable);

		return HotelInfoConverter.fromHotelSearchEntityList(result);
	}
}
