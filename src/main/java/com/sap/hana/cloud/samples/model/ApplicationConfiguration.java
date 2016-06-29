package com.sap.hana.cloud.samples.model;

public class ApplicationConfiguration {

	private final String name;
	private final String account;
	private final String landscapeFQDN;

	public ApplicationConfiguration(String name, String account, String landscapeFQDN) {
		this.name = name;
		this.account = account;
		this.landscapeFQDN = landscapeFQDN;
	}

	public String getName() {
		return this.name;
	}

	public String getAccount() {
		return this.account;
	}

	public String getLandscapeFQDN() {
		return this.landscapeFQDN;
	}
}
