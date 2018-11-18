package pers.miaku.blackhole.rest.enums;

public enum SmsPlatformEnum {
    TENCENT(0), YUNXIN(1);

    private Integer no;

    SmsPlatformEnum(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return this.no;
    }

}
