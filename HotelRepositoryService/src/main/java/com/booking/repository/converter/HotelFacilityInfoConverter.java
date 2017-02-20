package com.booking.repository.converter;

import java.util.ArrayList;
import java.util.List;

import com.booking.repository.dao.entity.HotelFacilityEntity;
import com.booking.repository.entity.FacilityInfo;
import com.booking.repository.entity.HotelFacilityInfo;

public class HotelFacilityInfoConverter extends BaseConverter {

	public static List<HotelFacilityEntity> toHotelFacilityEntity(HotelFacilityInfo hotelFaciltyInfo) {
		if (null == hotelFaciltyInfo)
			return null;

		List<HotelFacilityEntity> list = new ArrayList<HotelFacilityEntity>();
		for (FacilityInfo facilityInfo : hotelFaciltyInfo.getFacilityInfoList()) {
			HotelFacilityEntity e = new HotelFacilityEntity();
			e.setId(hotelFaciltyInfo.getId());
			e.setVersion(facilityInfo.getVersion());
			e.setFacilityType(facilityInfo.getFaciltyType());
			e.setFacilityDesc(toString(facilityInfo.getFacilityDetailList()));
			e.setCreatedOn(hotelFaciltyInfo.getCreatedOn());
			e.setCreatedBy(hotelFaciltyInfo.getCreatedBy());
			e.setUpdatedOn(hotelFaciltyInfo.getUpdatedOn());
			e.setUpdatedOn(hotelFaciltyInfo.getUpdatedOn());
			list.add(e);
		}
		return list;
	}

	public static HotelFacilityInfo toHotelFacilityInfo(List<HotelFacilityEntity> result) {
		if (result == null || result.isEmpty())
			return null;

		HotelFacilityInfo facilityInfo = new HotelFacilityInfo();
		facilityInfo.setId(result.get(0).getId());
		List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

		for (HotelFacilityEntity facilityEntity : result) {
			FacilityInfo e = new FacilityInfo();
			e.setFaciltyType(facilityEntity.getFacilityType());
			e.setVersion(facilityEntity.getVersion());
			e.setFacilityDetailList(fromString(facilityEntity.getFacilityDesc(), List.class));
			facilityInfoList.add(e);
		}
		facilityInfo.setFacilityInfoList(facilityInfoList);
		return facilityInfo;
	}

}
