package com.sap.hana.cloud.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sap.hana.cloud.samples.model.ApplicationConfiguration;
import com.sap.hana.cloud.samples.model.Configuration;
import com.sap.hana.cloud.samples.response.model.Application;

public class ApplicationsProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ApplicationsProxyServlet.class);

	private static final String API_URL_FORMAT = "https://%s/monitoring/v1/accounts/%s/apps/%s/metrics";
	private static final String CONTENT_TYPE = "application/json;charset=utf-8";
	private static final Gson gson = new Gson();
	private final Configuration config = new Configuration();

	static {
		BasicConfigurator.configure();
	}

	public void init() throws ServletException {
		LOGGER.info("About to initialize the servlet");
		super.init();
		config.configure();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);

		List<Application> appsList = collectAllApplications();

		String appsJson = gson.toJson(appsList);

		response.getWriter().println(appsJson);
	}

	private List<Application> collectAllApplications() throws ClientProtocolException, IOException {
		List<Application> appsList = new ArrayList<Application>();

		LOGGER.info("Collecting applications details: ");

		for (ApplicationConfiguration appConfig : config.getAppsList()) {
			LOGGER.info(String.format("Collecting details for application %s", appConfig.getName()));
			Application application = getApplication(appConfig);
			addApplication(application, appsList);

			if (application == null) {
				LOGGER.info(String.format("Couldn't find details for application %s", appConfig.getName()));
			}
		}

		return appsList;
	}

	private Application getApplication(ApplicationConfiguration appConfig) throws IOException, ClientProtocolException {
		String responseString = null;
		try {
			DefaultHttpClient apiClient = createHttpClient();

			HttpGet apiRequest = new HttpGet(getApplicationURL(appConfig));
			HttpResponse apiResponse = apiClient.execute(apiRequest);

			HttpEntity entity = apiResponse.getEntity();
			responseString = EntityUtils.toString(entity);
			EntityUtils.consume(entity);

			List<Application> apps = gson.fromJson(responseString, new TypeToken<List<Application>>() {
			}.getType());

			if (apps != null && apps.size() > 0) {
				return apps.get(0);
			}
		} catch (JsonSyntaxException e) {
			LOGGER.error(String.format("Not a valid json response for application %s. Original response: %s",
					appConfig.getName(), responseString), e);
		}

		return null;
	}

	private DefaultHttpClient createHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient();

		if (config.getProxyHost() != null) {
			HttpHost proxyHost = new HttpHost(config.getProxyHost(), config.getProxyPort());
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHost);
		}

		client.getCredentialsProvider().setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials(config.getUser(), config.getPassword()));

		return client;
	}

	private void addApplication(Application monitoringApp, List<Application> appsList) {
		if (monitoringApp != null) {
			LOGGER.info(String.format("Details for application %s collected", monitoringApp.getApplication()));
			appsList.add(monitoringApp);
		}
	}

	private String getApplicationURL(ApplicationConfiguration appConfig) {
		return String.format(API_URL_FORMAT, appConfig.getLandscapeFQDN(), appConfig.getAccount(), appConfig.getName());
	}
}
