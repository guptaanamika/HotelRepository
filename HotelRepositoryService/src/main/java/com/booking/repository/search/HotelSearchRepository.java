package com.booking.repository.search;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.booking.repository.dao.entity.HotelSearchEntity;

public interface HotelSearchRepository extends ElasticsearchRepository<HotelSearchEntity, String> {

	public HotelSearchEntity getById(String id);

	public void delete(String id);

	public List<HotelSearchEntity> findByNameLike(String name, Pageable pageable);

	public List<HotelSearchEntity> findByStarRanking(Integer starRanking, Pageable pageable);

	public List<HotelSearchEntity> findByAddressCity(String city, Pageable pageable);

	public List<HotelSearchEntity> findByStarRankingAndNameLike(Integer starRanking, String name, Pageable pageable);

	public List<HotelSearchEntity> findByAddressCityAndNameLike(String city, String name, Pageable pageable);

	public List<HotelSearchEntity> findByAddressCityAndStarRanking(String city, Integer starRanking, Pageable pageable);

	public List<HotelSearchEntity> findByAddressCityAndStarRankingAndNameLike(String city, Integer starRanking,
			String name, Pageable pageable);

}
