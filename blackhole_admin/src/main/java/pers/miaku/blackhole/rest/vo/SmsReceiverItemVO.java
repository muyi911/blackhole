package pers.miaku.blackhole.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 短信接收人详情返回视图
 */
public class SmsReceiverItemVO {
    @JsonProperty("id")
    private String receiverID;

    @JsonProperty("name")
    private String receiverName;

    @JsonProperty("phone")
    private String receiverPhone;

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
