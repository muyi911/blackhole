package pers.miaku.blackhole.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsConfigItemVO {
    @JsonProperty("id")
    private String configID;

    @JsonProperty("appid")
    private String appID;

    @JsonProperty("appkey")
    private String appKey;

    @JsonProperty("platform")
    private Integer platform;

    public String getConfigID() {
        return configID;
    }

    public void setConfigID(String configID) {
        this.configID = configID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
