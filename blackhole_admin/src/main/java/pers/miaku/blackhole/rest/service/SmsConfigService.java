package pers.miaku.blackhole.rest.service;

import pers.miaku.blackhole.dao.SmsConfigDao;
import pers.miaku.blackhole.domain.SmsConfig;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.convert.SmsConfig2SmsConfigVOConverter;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.ConfigForm;
import pers.miaku.blackhole.rest.vo.SmsConfigItemVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.List;

@Service
public class SmsConfigService {
    @Autowired
    SmsConfigDao smsConfigDao;

    /**
     * 获取短信配置信息
     *
     * @param id
     * @return
     */
    public SmsConfigItemVO getConfig(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(ResultEnum.PARAM_ERROR);
        }
        SmsConfig smsConfig = smsConfigDao.getOne(id);
        SmsConfigItemVO smsConfigItemVO = new SmsConfigItemVO();
        BeanUtils.copyProperties(smsConfig, smsConfigItemVO);
        return smsConfigItemVO;
    }

    /**
     * 获取短信配置列表
     *
     * @param searchKeyWord
     * @param pageable
     * @return
     */
    public Page<SmsConfigItemVO> findList(String searchKeyWord, Pageable pageable) {
        Page<SmsConfig> smsConfigPage = smsConfigDao.findAll(pageable);
        List<SmsConfigItemVO> smsConfigItemVOS = SmsConfig2SmsConfigVOConverter.convert(smsConfigPage.getContent());
        Page<SmsConfigItemVO> smsConfigItemVOSPage = new PageImpl<>(smsConfigItemVOS, pageable,
                smsConfigPage.getTotalElements());
        return smsConfigItemVOSPage;
    }

    /**
     * 新增短信配置
     *
     * @param smsConfig
     */
    public void addConfig(SmsConfig smsConfig) {
        smsConfigDao.save(smsConfig);
    }

    /**
     * 更新短信配置
     *
     * @param id
     * @param configForm
     */
    public void updateConfig(String id, @Valid ConfigForm configForm) {
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(ResultEnum.PARAM_ERROR);
        }

        SmsConfig smsConfig = smsConfigDao.getOne(id);
        if (smsConfig != null) {
            smsConfig.setAppID(configForm.getAppID());
            smsConfig.setAppKey(configForm.getAppKey());
            smsConfig.setPlatform(configForm.getPlatform());
        } else {
            throw new ApiException(ResultEnum.OPERATION_FAIL);
        }
    }

    /**
     * 删除短信配置
     *
     * @param id
     */
    public void deleteConfig(String id) {
        // TODO 存在待发送的短信不能删除
        smsConfigDao.deleteById(id);
    }
}
