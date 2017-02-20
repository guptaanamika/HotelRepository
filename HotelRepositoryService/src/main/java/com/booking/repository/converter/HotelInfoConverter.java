package com.booking.repository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.booking.repository.dao.entity.HotelEntity;
import com.booking.repository.dao.entity.HotelSearchEntity;
import com.booking.repository.entity.Address;
import com.booking.repository.entity.HotelInfo;

public class HotelInfoConverter extends BaseConverter {

	public static HotelEntity toHotelEntity(HotelInfo hotelInfo) {
		if (null == hotelInfo)
			return null;
		HotelEntity hotelEntity = new HotelEntity();
		BeanUtils.copyProperties(hotelInfo, hotelEntity);
		hotelEntity.setAddress(toString(hotelInfo.getAddress()));
		return hotelEntity;
	}

	public static HotelInfo toHotelInfo(HotelEntity hotelEntity) {
		if (null == hotelEntity)
			return null;
		HotelInfo hotelInfo = new HotelInfo();
		BeanUtils.copyProperties(hotelEntity, hotelInfo);
		hotelInfo.setAddress(fromString(hotelEntity.getAddress(), Address.class));
		return hotelInfo;
	}

	public static HotelSearchEntity toHotelSearchEntity(HotelInfo hotelInfo) {
		if (null == hotelInfo)
			return null;
		HotelSearchEntity hotelSearchEntity = new HotelSearchEntity();
		BeanUtils.copyProperties(hotelInfo, hotelSearchEntity);
		return hotelSearchEntity;
	}

	public static HotelInfo toHotelInfo(HotelSearchEntity hotelSearchEntity) {
		if (null == hotelSearchEntity)
			return null;
		HotelInfo hotelInfo = new HotelInfo();
		BeanUtils.copyProperties(hotelSearchEntity, hotelInfo);
		return hotelInfo;
	}

	public static List<HotelInfo> fromHotelSearchEntityList(List<HotelSearchEntity> result) {
		if (null == result)
			return null;
		List<HotelInfo> hotelInfoList = new ArrayList<HotelInfo>();
		for (HotelSearchEntity entity : result) {
			hotelInfoList.add(toHotelInfo(entity));
		}
		return hotelInfoList;
	}

}
