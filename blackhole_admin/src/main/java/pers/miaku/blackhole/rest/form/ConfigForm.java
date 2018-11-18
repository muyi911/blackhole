package pers.miaku.blackhole.rest.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class ConfigForm {
    @NotEmpty(message = "appid不能为空")
    @JsonProperty("appid")
    private String appID;

    @NotEmpty(message = "appkey不能为空")
    @JsonProperty("appkey")
    private String appKey;

    @NotEmpty(message = "短信平台不能为空")
    @JsonProperty("platform")
    private Integer platform;

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
