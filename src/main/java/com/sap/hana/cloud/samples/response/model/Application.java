package com.sap.hana.cloud.samples.response.model;

import java.util.List;
import java.util.ArrayList;

public class Application extends ResponseCollection {

	private String account;
	private String application;

	private final List<ApplicationProcess> processes = new ArrayList<ApplicationProcess>(1);

	public List<ApplicationProcess> getProcesses() {
		return processes;
	}

	public String getAccount() {
		return this.account;
	}

	public String getApplication() {
		return this.application;
	}
}
