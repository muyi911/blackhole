package pers.miaku.blackhole.rest.controller;

import pers.miaku.blackhole.domain.SmsReceiver;
import pers.miaku.blackhole.exception.ApiException;
import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.form.ReceiverForm;
import pers.miaku.blackhole.rest.service.SmsReceiverService;
import pers.miaku.blackhole.rest.vo.ResultVO;
import pers.miaku.blackhole.rest.vo.SmsReceiverItemVO;
import pers.miaku.blackhole.rest.vo.SmsReceiverListVO;
import pers.miaku.blackhole.util.KeyUtil;
import pers.miaku.blackhole.util.ResultVOUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/sms")
public class SmsReceiverController {
    private static final Logger log = LoggerFactory.getLogger(SmsReceiverController.class);

    @Autowired
    private SmsReceiverService smsReceiverService;

    /**
     * 获取短信接收者列表
     *
     * @param searchKeyWord
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/receivers")
    public ResultVO<SmsReceiverListVO> getReceiverList(@RequestParam(value = "searchkeyword", defaultValue = "") String searchKeyWord,
                                                       @RequestParam(value = "pageindex", defaultValue = "1") Integer pageIndex,
                                                       @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "insertTime");
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, sort);
        Page<SmsReceiverItemVO> receiverItemVOPage = smsReceiverService.findList(searchKeyWord, pageRequest);
        return ResultVOUtil.success(new SmsReceiverListVO(receiverItemVOPage.getTotalPages(), receiverItemVOPage.getContent()));
    }


    /**
     * 获取某一个短信接收人的信息
     *
     * @param id
     * @return
     */
    @GetMapping("/receiver/{id}")
    public ResultVO<SmsReceiverItemVO> getReceiver(@PathVariable String id) {
        SmsReceiverItemVO smsReceiverItemVO = smsReceiverService.getReceiver(id);
        return ResultVOUtil.success(smsReceiverItemVO);
    }

    /**
     * 新增短信接收人信息
     *
     * @param receiverForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/receiver")
    public ResultVO<?> addReceiver(@Valid @RequestBody ReceiverForm receiverForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("【新增短信接收人】参数不正确，receiverForm={}", receiverForm);
            throw new ApiException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        SmsReceiver smsReceiver = new SmsReceiver();
        smsReceiver.setReceiverID(KeyUtil.genUniqueKey());
        smsReceiver.setReceiverName(receiverForm.getReceiverName());
        smsReceiver.setReceiverPhone(receiverForm.getReceiverPhone());
        smsReceiver.setInsertTime(new Date());
        smsReceiverService.saveReceiver(smsReceiver);
        return ResultVOUtil.success();
    }

    /**
     * 更新短信接收人信息
     *
     * @param id
     * @param receiverForm
     * @param bindingResult
     * @return
     */
    @PutMapping("/receiver/{id}")
    public ResultVO<?> updateReceiver(@PathVariable String id, @Valid @RequestBody ReceiverForm receiverForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.debug("【更新短信接收人】参数不正确，receiverForm={}", receiverForm);
            throw new ApiException(ResultEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }
        smsReceiverService.updateReceiver(id, receiverForm);
        return ResultVOUtil.success();
    }

    /**
     * 删除短信接收人信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/receiver/{id}")
    public ResultVO<?> deleteReceiver(@PathVariable String id) {
        smsReceiverService.deleteReceiver(id);
        return ResultVOUtil.success();
    }
}
