package pers.miaku.blackhole.util;

import pers.miaku.blackhole.rest.enums.ResultEnum;
import pers.miaku.blackhole.rest.vo.ResultVO;

/**
 * api通用返回数据
 *
 * @author caozn
 */
public class ResultVOUtil {
    public static <W> ResultVO<W> success(W object) {
        ResultVO<W> resultVO = new ResultVO<>();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
        resultVO.setData(object);
        return resultVO;
    }

    public static <W> ResultVO<W> success() {
        return success(null);
    }

    public static <W> ResultVO<W> error(Integer code, String msg) {
        ResultVO<W> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}
