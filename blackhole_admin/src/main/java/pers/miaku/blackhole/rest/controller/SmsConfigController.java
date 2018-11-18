package pers.miaku.blackhole.rest.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pers.miaku.blackhole.domain.SmsConfig;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.ConfigForm;
import pers.miaku.blackhole.rest.service.SmsConfigService;
import pers.miaku.blackhole.rest.vo.ResultVO;
import pers.miaku.blackhole.rest.vo.SmsConfigItemVO;
import pers.miaku.blackhole.rest.vo.SmsConfigListVO;
import pers.miaku.blackhole.util.KeyUtil;
import pers.miaku.blackhole.util.ResultVOUtil;

@RestController
@RequestMapping("/sms")
public class SmsConfigController {
    private static final Logger log = LoggerFactory.getLogger(SmsConfigController.class);

    @Autowired
    private SmsConfigService smsConfigService;

    /**
     * 获取短信配置信息
     *
     * @param id
     * @return
     */
    @GetMapping("/config/{id}")
    public ResultVO<SmsConfigItemVO> getConfig(@PathVariable String id) {
        SmsConfigItemVO smsConfigItemVO = smsConfigService.getConfig(id);
        return ResultVOUtil.success(smsConfigItemVO);
    }

    @GetMapping("/configs")
    public ResultVO<SmsConfigListVO> getConfigList(@RequestParam(value = "searchkeyword", defaultValue = "") String searchKeyWord,
                                                   @RequestParam(value = "pageindex", defaultValue = "1") Integer pageIndex,
                                                   @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "insertTime");
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, sort);
        Page<SmsConfigItemVO> smsConfigItemVOPage = smsConfigService.findList(searchKeyWord, pageRequest);
        return ResultVOUtil.success(new SmsConfigListVO(smsConfigItemVOPage.getTotalPages(), smsConfigItemVOPage.getContent()));
    }


    /**
     * 新增短信配置
     *
     * @param configForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/config")
    public ResultVO<?> addConfig(@Valid @RequestBody ConfigForm configForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("【新增短信配置】参数不正确，configForm={}", configForm);
            throw new ApiException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        SmsConfig smsConfig = new SmsConfig();
        smsConfig.setConfigID(KeyUtil.genUniqueKey());
        smsConfig.setAppID(configForm.getAppID());
        smsConfig.setAppKey(configForm.getAppKey());
        smsConfig.setPlatform(configForm.getPlatform());
        smsConfig.setInsertTime(new Date());
        smsConfigService.addConfig(smsConfig);
        return ResultVOUtil.success();
    }


    /**
     * 修改短信配置
     *
     * @param id
     * @param configForm
     * @param bindingResult
     * @return
     */
    @PutMapping("/config/{id}")
    public ResultVO<?> updateConfig(@PathVariable String id, @Valid @RequestBody ConfigForm configForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("【修改短信配置】参数不正确，configForm={}", configForm);
            throw new ApiException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        smsConfigService.updateConfig(id, configForm);
        return ResultVOUtil.success();
    }

    /**
     * 删除短信配置
     *
     * @param id
     * @return
     */
    @DeleteMapping("/config/{id}")
    public ResultVO<?> deleteConfig(@PathVariable String id) {
        smsConfigService.deleteConfig(id);
        return ResultVOUtil.success();
    }

}
