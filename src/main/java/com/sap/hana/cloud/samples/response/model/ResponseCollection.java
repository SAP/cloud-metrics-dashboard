package com.sap.hana.cloud.samples.response.model;

import java.util.List;

public abstract class ResponseCollection {

	private List<Metric> metrics;
	private String state;

	public String getState() {
		return state;
	}

	public List<Metric> getMetrics() {
		return metrics;
	}
}
