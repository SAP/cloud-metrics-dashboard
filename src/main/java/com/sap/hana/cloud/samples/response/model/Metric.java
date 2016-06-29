package com.sap.hana.cloud.samples.response.model;

public class Metric {

	private String name;
	private String state;
	private double value;
	private String unit;
	private double warningThreshold;
	private double errorThreshold;
	private long timestamp;
	private String output;
	private String metricType;
	private double min;
	private double max;

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

	public double getValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

	public double getWarningThreshold() {
		return warningThreshold;
	}

	public double getErrorThreshold() {
		return errorThreshold;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getOutput() {
		return output;
	}

	public String getMetricType() {
		return metricType;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
}
