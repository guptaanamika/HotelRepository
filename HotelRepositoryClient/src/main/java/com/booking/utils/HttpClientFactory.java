package com.booking.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

public class HttpClientFactory {

	private static HttpClientFactory instance = new HttpClientFactory();

	private HttpClientFactory() {
	}

	public static HttpClientFactory getInstance() {
		return instance;
	}

	public HttpClientEntity createHttpClient(ClientDetails clientDetails)
			throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, FileNotFoundException, IOException {

		HttpClientBuilder builder;

		RequestConfig config = RequestConfig.custom().setConnectTimeout(clientDetails.getConnectTimeout())
				.setConnectionRequestTimeout(clientDetails.getConnectionRequestTimeout())
				.setSocketTimeout(clientDetails.getSocketTimeout()).build();

		PoolingHttpClientConnectionManager connManager = getConnectionManager(
				getSSLConnectionSocketFactory(clientDetails), clientDetails);

		builder = HttpClientBuilder.create().useSystemProperties()
				.setKeepAliveStrategy(getKeepAliveStartegy(clientDetails.getKeepAlive()))
				.setConnectionManager(connManager);

		HttpClientEntity httpClientEntity = new HttpClientEntity();
		httpClientEntity.setHttpClient(builder.setDefaultRequestConfig(config).build());
		httpClientEntity.setConnManager(connManager);
		return httpClientEntity;
	}

	/**
	 * Creates a Pooled connection Manager to be used by HTTP Client during
	 * connection establishment.You can also set the number of max connection
	 * per route here though connPoolControl.
	 */
	public PoolingHttpClientConnectionManager getConnectionManager(SSLConnectionSocketFactory sslsf,
			ClientDetails clientDetails) {

		final PoolingHttpClientConnectionManager poolingmgr = new PoolingHttpClientConnectionManager(RegistryBuilder
				.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslsf).build());

		poolingmgr.setMaxTotal(clientDetails.getMaxConnTotal());
		poolingmgr.setDefaultMaxPerRoute(clientDetails.getMaxConnPerRoute());

		return poolingmgr;
	}

	/**
	 * This will create a keep alive strategy used to persist a established
	 * connection. Default : 5 second
	 */
	public ConnectionKeepAliveStrategy getKeepAliveStartegy(final long keepAliveTime) {

		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				return keepAliveTime;
			}
		};
		return myStrategy;
	}

	/**
	 * Set the SSLContext on the basis of mutualAuthentication.If mutual
	 * authentication is required,it will set the respective client keystore and
	 * trust store to be sent to server during SSL Handshaking.
	 */
	public SSLConnectionSocketFactory getSSLConnectionSocketFactory(ClientDetails clientDetails)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException, KeyManagementException {

		final SSLContextBuilder builder = new SSLContextBuilder();
		if (clientDetails.getMutualAuthenticationRequired()) {
			KeyStore clientKeyStore = KeyStore.getInstance(clientDetails.getKeyStoreType());
			clientKeyStore.load(new FileInputStream(clientDetails.getClientKeystoreFile()),
					clientDetails.getClientKeystorePassword().toCharArray());
			builder.loadKeyMaterial(clientKeyStore, clientDetails.getClientKeystorePassword().toCharArray());
		}
		SSLContext sslContext = builder.build();
		// Install the all-trusting trust manager
		sslContext.init(null, getTrustAllCertsManager(), new java.security.SecureRandom());
		final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		sslsf.createSocket(null);
		return sslsf;
	}

	private TrustManager[] getTrustAllCertsManager() {
		// creating trust all Certificate TM
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		return trustAllCerts;
	}

}
