package pers.miaku.blackhole.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 短信接收人列表返回视图
 */
public class SmsReceiverListVO {
    @JsonProperty("total")
    private long total;

    @JsonProperty("list")
    private List<SmsReceiverItemVO> receiverList;

    public SmsReceiverListVO(long total, List<SmsReceiverItemVO> receiverList) {
        this.total = total;
        this.receiverList = receiverList;
    }

}
