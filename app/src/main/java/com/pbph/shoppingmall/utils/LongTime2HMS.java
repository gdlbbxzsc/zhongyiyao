package com.pbph.shoppingmall.utils;

import com.utils.StringUtils;

/**
 * Created by Administrator on 2018/4/9.
 */

public class LongTime2HMS {

    public long h, m, s;

    public LongTime2HMS(long h, long m, long s) {
        this.h = h;
        this.m = m;
        this.s = s;
    }

    public String getH() {
        return StringUtils.formatLong2String(h, 2);
    }

    public String getM() {
        return StringUtils.formatLong2String(m, 2);
    }

    public String getS() {
        return StringUtils.formatLong2String(s, 2);
    }

    public static LongTime2HMS longTime2HMS(long time) {
        long h = time / 3600000;

        time = time % 3600000;
        long m = time / 60000;

        time = time % 60000;
        long s = time / 1000;
        return new LongTime2HMS(h, m, s);
    }


}
