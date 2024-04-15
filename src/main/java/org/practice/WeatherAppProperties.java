package org.practice;

public class WeatherAppProperties {

    private String appKey;

    private String hostName;

    private String pathValue;

    private String scheme;
    
    public WeatherAppProperties(String appKey, String hostName, String pathValue, String scheme) {
        this.appKey = appKey;
        this.hostName = hostName;
        this.pathValue = pathValue;
        this.scheme = scheme;
    }

      public String getAppKey() { return appKey; }

      public void setAppKey(String appKey) { this.appKey = appKey; }

      public String getHostName() { return hostName; }

      public void setHostName(String hostName) { this.hostName = hostName; }

      public String getPathValue() { return pathValue; }

      public void setPathValue(String pathValue) { this.pathValue = pathValue; }

      public String getScheme() { return scheme; }

      public void setScheme(String scheme) { this.scheme = scheme; }

}
