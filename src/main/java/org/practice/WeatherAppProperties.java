package org.practice;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherAppProperties {

	@ConfigProperty(name = "weather.app-key")
	private String appKey;

	 @ConfigProperty(name = "weather.host-name")
	private String hostName;

	 @ConfigProperty(name = "weather.path-value")
	private String pathValue;

	 @ConfigProperty(name = "weather.scheme")
	private String scheme;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPathValue() {
		return pathValue;
	}

	public void setPathValue(String pathValue) {
		this.pathValue = pathValue;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
