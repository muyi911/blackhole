package pers.miaku.blackhole.util;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一主键
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
