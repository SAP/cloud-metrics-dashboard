package com.sap.hana.cloud.samples.model;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private static final String PROXY_HOST = "proxy";
	private static final int PROXY_PORT = 8080;

	private final String user = "my_username";
	private final String password = "my_password";

	private final List<ApplicationConfiguration> appsList = new ArrayList<ApplicationConfiguration>();

	public void configure() {

		String landscapeFQDN1 = "api.hana.ondemand.com";
		String account1 = "a1";
		String application1 = "app1";
		ApplicationConfiguration app1Config = new ApplicationConfiguration(application1, account1, landscapeFQDN1);
		this.appsList.add(app1Config);

		String landscapeFQDN2 = "api.us1.hana.ondemand.com";
		String account2 = "a2";
		String application2 = "app2";
		ApplicationConfiguration app2Config = new ApplicationConfiguration(application2, account2, landscapeFQDN2);
		this.appsList.add(app2Config);
	}

	public String getProxyHost() {
		return Configuration.PROXY_HOST;
	}

	public int getProxyPort() {
		return Configuration.PROXY_PORT;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public List<ApplicationConfiguration> getAppsList() {
		return this.appsList;
	}
}
