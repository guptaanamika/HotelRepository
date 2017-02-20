package com.booking.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.avalon.framework.service.ServiceException;

import com.booking.repository.exception.InternalClientException;

@Getter
@Setter
@NoArgsConstructor
public class ClientDetails {

	private String clientKey = "testKey";
	private String url = "http://localhost:8080";
	private String clientName = "LabelStore";
	private int connectionRequestTimeout = 5000;
	private int connectTimeout = 5000;
	private int socketTimeout = 7000;
	/**
	 * Keep alive time requied in keep alive strategy. Default is -1 which mean
	 * indefinite time.
	 */
	private Long keepAlive = -1L;

	/**
	 * This property specify whether mutual authentication is required and
	 * certificates on both side needs to be exchanged. Default is false.
	 */
	private Boolean mutualAuthenticationRequired = false;
	/**
	 * Specify the keystore type if client authentication is required.Default is
	 * jks.
	 */
	private String keyStoreType = "jks";

	/**
	 * Configure the file location for client keyStore.Specify the full system
	 * path as it doesnot understand in trms of classpath.
	 */
	private String clientKeystoreFile = "/opt/configuration/clientkeystore.jks";

	/**
	 * Configure the password for client Keystore.
	 */
	private String clientKeystorePassword = "snapdeal";

	/**
	 * Configure the max connection per route.
	 */
	private Integer maxConnPerRoute = 50;

	/**
	 * Configure the max connection in total.
	 */
	private Integer maxConnTotal = 50;

	public String getClientKey() throws ServiceException {
		validate(clientKey);
		return clientKey;
	}

	private void validate(String param) throws ServiceException {
		if (param == null)
			throw new InternalClientException(
					"Client details are not initialized. Please initialize client before calling any API.");
	}
}