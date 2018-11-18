package pers.miaku.blackhole.rest.convert;

import pers.miaku.blackhole.domain.SmsConfig;
import pers.miaku.blackhole.rest.vo.SmsConfigItemVO;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SmsConfig2SmsConfigVOConverter {

    public static SmsConfigItemVO convert(SmsConfig smsConfig) {
        SmsConfigItemVO smsConfigItemVO = new SmsConfigItemVO();
        BeanUtils.copyProperties(smsConfig, smsConfigItemVO);
        return smsConfigItemVO;
    }

    public static List<SmsConfigItemVO> convert(List<SmsConfig> smsConfigs) {
        return smsConfigs.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
