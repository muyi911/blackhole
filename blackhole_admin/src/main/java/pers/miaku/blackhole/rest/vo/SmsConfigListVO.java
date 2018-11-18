package pers.miaku.blackhole.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SmsConfigListVO {

    @JsonProperty("total")
    private long total;

    @JsonProperty("list")
    private List<SmsConfigItemVO> configList;

    public SmsConfigListVO(long total, List<SmsConfigItemVO> configList) {
        this.total = total;
        this.configList = configList;
    }
}
