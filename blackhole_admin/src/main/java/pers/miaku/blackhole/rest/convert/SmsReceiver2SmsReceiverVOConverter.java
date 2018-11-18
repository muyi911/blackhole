package pers.miaku.blackhole.rest.convert;

import pers.miaku.blackhole.domain.SmsReceiver;
import pers.miaku.blackhole.rest.vo.SmsReceiverItemVO;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SmsReceiver2SmsReceiverVOConverter {
    public static SmsReceiverItemVO convert(SmsReceiver smsReceiver) {
        SmsReceiverItemVO smsReceiverItemVO = new SmsReceiverItemVO();
        BeanUtils.copyProperties(smsReceiver, smsReceiverItemVO);
        return smsReceiverItemVO;
    }

    public static List<SmsReceiverItemVO> convert(List<SmsReceiver> smsReceivers) {
        return smsReceivers.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
