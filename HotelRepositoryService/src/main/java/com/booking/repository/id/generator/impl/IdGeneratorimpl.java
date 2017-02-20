package com.booking.repository.id.generator.impl;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.booking.repository.entity.HotelInfo;
import com.booking.repository.id.generator.IdGenerator;

@Component
public class IdGeneratorimpl implements IdGenerator {

	@Override
	public String generatedId(HotelInfo hotelInfo) {
		return encodeBase64(hotelInfo.getAddress().getZipcode + ":" + hotelInfo.getName());
	}

	public static String encodeBase64(String src) {
		String encode_src = Base64.encodeBase64URLSafeString(src.getBytes());
		return StringUtils.strip(encode_src, "=");
	}
}
