package pers.miaku.blackhole.rest.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 短信接收人请求数据
 */
public class ReceiverForm {
    @NotEmpty(message = "姓名不能为空")
    @JsonProperty("name")
    private String receiverName;

    @NotEmpty(message = "手机号不能为空")
    @JsonProperty("phone")
    private String receiverPhone;

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
