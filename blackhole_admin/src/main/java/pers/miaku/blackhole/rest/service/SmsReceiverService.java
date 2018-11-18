package pers.miaku.blackhole.rest.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import pers.miaku.blackhole.dao.SmsReceiverDao;
import pers.miaku.blackhole.domain.SmsReceiver;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.convert.SmsReceiver2SmsReceiverVOConverter;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.ReceiverForm;
import pers.miaku.blackhole.rest.vo.SmsReceiverItemVO;

@Service
public class SmsReceiverService {
    @Autowired
    private SmsReceiverDao smsReceiverDao;

    /**
     * 获取短信接收者列表
     *
     * @param searchKeyWord
     * @param pageable
     * @return
     */
    public Page<SmsReceiverItemVO> findList(String searchKeyWord, Pageable pageable) {
        Page<SmsReceiver> smsReceiverPage = smsReceiverDao.findAll(pageable);
        List<SmsReceiverItemVO> smsReceiverItemVOs = SmsReceiver2SmsReceiverVOConverter.convert(smsReceiverPage.getContent());
        Page<SmsReceiverItemVO> smsReceiverItemVOPage = new PageImpl<>(smsReceiverItemVOs, pageable,
                smsReceiverPage.getTotalElements());
        return smsReceiverItemVOPage;
    }

    /**
     * 获取某一个短信接收人的信息
     *
     * @param id
     * @return
     */
    public SmsReceiverItemVO getReceiver(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(ResultEnum.PARAM_ERROR);
        }

        Optional<SmsReceiver> smsReceiverOptional = smsReceiverDao.findById(id);
        SmsReceiverItemVO smsReceiverItemVO = new SmsReceiverItemVO();
        BeanUtils.copyProperties(smsReceiverOptional.get(), smsReceiverItemVO);
        return smsReceiverItemVO;
    }

    /**
     * 新增短信接收人
     *
     * @param smsReceiver
     */
    public void saveReceiver(SmsReceiver smsReceiver) {
        smsReceiverDao.save(smsReceiver);
    }

    /**
     * 更新短信接收人信息
     *
     * @param id
     * @param receiverForm
     */
    public void updateReceiver(String id, @Valid ReceiverForm receiverForm) {
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(ResultEnum.PARAM_ERROR);
        }

        SmsReceiver smsReceiver = smsReceiverDao.getOne(id);
        if (smsReceiver != null) {
            smsReceiver.setReceiverName(receiverForm.getReceiverName());
            smsReceiver.setReceiverPhone(receiverForm.getReceiverPhone());
            smsReceiverDao.save(smsReceiver);
        } else {
            throw new ApiException(ResultEnum.OPERATION_FAIL);
        }
    }

    /**
     * 删除短信接收人信息
     *
     * @param id
     */
    public void deleteReceiver(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(ResultEnum.PARAM_ERROR);
        }

        SmsReceiver smsReceiver = smsReceiverDao.getOne(id);
        if (smsReceiver != null) {
            // TODO 要是存在短信计划，不能删除短信接收人信息

            smsReceiverDao.delete(smsReceiver);
        } else {
            throw new ApiException(ResultEnum.OPERATION_FAIL);
        }
    }


}
