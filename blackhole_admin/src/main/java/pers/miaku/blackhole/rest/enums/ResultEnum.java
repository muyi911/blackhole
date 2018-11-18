package pers.miaku.blackhole.rest.enums;

public enum ResultEnum {
    SUCCESS(200, "成功"),
    LOGINID_OR_PASSWORD_ERROR(4011, "用户名或密码错误"),
    USER_NOT_LOGIN(4012, "请先登录"),
    PARAM_ERROR(501, "参数不正确"),
    FILE_UPLOAD_ERROR(500002, "文件上传失败"),
    LOGOUT_ERROR(001001, "遭遇未知错误，登出失败"),
    OPERATION_FAIL(000000, "操作失败");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
