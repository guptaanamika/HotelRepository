package com.booking.utils;

import java.util.TimerTask;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

@Slf4j
public class IdleConnectionMonitor extends TimerTask {

	/**
	 * Rate at which metric will get logged.
	 */
	public static final long PUBLISH_RATE = 10 * 1000;

	public static final long MONITOR_RATE = 30 * 1000;

	/**
	 * Thread name to be set from the configuration per client else default name
	 * get published.
	 */
	@Getter
	@Setter
	private String monitorName = "ConnectionMonitor";

	@Setter
	private PoolingHttpClientConnectionManager connMgr;

	@Override
	public void run() {
		Thread.currentThread().setName(monitorName);
		log.debug("Cleaning expired connection by " + monitorName + " IdleConnectionManager");
		// connMgr.closeExpiredConnections();
		publishStatus();
	}

	/**
	 * Scheduled function to get the status of pooled connection in a metric.
	 */
	public void publishStatus() {
		log.debug("Connection Status :" + connMgr.getTotalStats());
		log.trace(monitorName + ".max_connection : " + connMgr.getTotalStats().getMax());
		log.trace(monitorName + ".available_connection : " + connMgr.getTotalStats().getAvailable());
		log.trace(monitorName + ".pending_connection : " + connMgr.getTotalStats().getPending());
		log.trace(monitorName + ".leased_connection : " + connMgr.getTotalStats().getLeased());
	}
}