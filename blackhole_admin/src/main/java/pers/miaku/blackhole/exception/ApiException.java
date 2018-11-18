package pers.miaku.blackhole.exception;

import pers.miaku.blackhole.rest.enums.ResultEnum;

/**
 * 自定义接口异常
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 5076107878279639603L;

	private Integer code;

	public ApiException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

	public ApiException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public ApiException(ResultEnum resultEnum, String message) {
		super(message);
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}
}
