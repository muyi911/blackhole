package pers.miaku.blackhole.exception;

import pers.miaku.blackhole.rest.vo.ResultVO;
import pers.miaku.blackhole.util.ResultVOUtil;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultVOUtil.error(500, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResultVO<?> adminApiExceptionHandler(ApiException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
