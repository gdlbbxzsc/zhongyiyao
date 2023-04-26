package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    public static final String PATTERN_NUM = "[0-9]*";
    public static final String PATTERN_LETTER = "[a-zA-Z]*";


    public static final String PATTERN_NUM_LETTER_LINE_HZ = "[a-zA-Z0-9_\u4e00-\u9fa5]*";

    public static final String PATTERN_MD5 = "[0-9a-zA-Z]*";


    public static boolean isEmpty(String str) {
        return null == str || str.length() <= 0;
    }

    public static boolean isEqual(Object foo, Object bar) {
//        return foo == bar || (null != foo && foo.equals(bar));
        return foo == bar || (null != foo && null != bar && foo.equals(bar));
//        return null == foo || null == bar ? foo == bar : foo.equals(bar);
    }


    //    字符串拼接,线程安全
    public static String bufferJoin(String join, String... array) {
        StringBuffer s = new StringBuffer();
        for (String str : array) {
            s.append(join);
            s.append(str);
        }
        return s.substring(join.length());
    }

    public static String buffer(String... array) {
        StringBuffer s = new StringBuffer();
        for (String str : array) {
            s.append(str);
        }
        return s.toString();
    }

    //    字符串拼接,线程不安全,效率高
    public static String builderJoin(String join, String... array) {
        StringBuilder s = new StringBuilder();
        for (String str : array) {
            s.append(join);
            s.append(str);
        }
        return s.substring(join.length());
    }

    public static String builder(String... array) {
        StringBuilder s = new StringBuilder();
        for (String str : array) {
            s.append(str);
        }
        return s.toString();
    }


    public static String exception2String(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            return "";
        }
    }

    public static boolean hasHZ(String str) {
        return str.getBytes().length != str.length();
    }

    public static boolean isNumber(String str) {
        try {
            Pattern pattern = Pattern.compile(PATTERN_NUM);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isNLL(String str) {
        try {
            Pattern pattern = Pattern.compile(PATTERN_NUM_LETTER_LINE_HZ);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isLetter(String str) {
        try {
            if (isEmpty(str)) {
                return false;
            }
            Pattern pattern = Pattern.compile(PATTERN_LETTER);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean isMD5(String str) {
        try {
            Pattern pattern = Pattern.compile(PATTERN_MD5);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        } catch (Exception ignored) {
        }
        return false;
    }

    public static String nullReturn(String str) {
        return str == null || str.length() == 0
                || "null".equals(str.toLowerCase()) ? "" : str;
    }

    //    转半角的函数(DBC case)
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++)
            c[i] = c[i] == 12288 ? (char) 32
                    : c[i] > 65280 && c[i] < 65375 ? (char) (c[i] - 65248)
                    : c[i];
        return new String(c);
    }

    //    转全角的函数(SBC case)
    public static String toSBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++)
            c[i] = c[i] == 32 ? (char) 12288
                    : c[i] < 127 ? (char) (c[i] + 65248) : c[i];
        return new String(c);
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    public static String convertFileSize1(long size) {
        double kiloByte = size / 1024D;
        if (kiloByte < 1) return size + "Byte";

        double megaByte = kiloByte / 1024;
        if (megaByte < 1)
            return new BigDecimal(Double.toString(kiloByte))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString()
                    + "KB";

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1)
            return new BigDecimal(Double.toString(megaByte))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString()
                    + "MB";

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1)
            return new BigDecimal(Double.toString(gigaByte))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString()
                    + "GB";

        return new BigDecimal(teraBytes)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString()
                + "TB";
    }

    public static byte[] stream2byteArray(InputStream ips) {
        byte[] buff = null;
        ByteArrayOutputStream bops = null;
        int tmp;
        try {
            bops = new ByteArrayOutputStream();

            while ((tmp = ips.read()) != -1) {
                bops.write(tmp);
            }
            buff = bops.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bops != null)
                    bops.close();
            } catch (IOException ignored) {
            }
            try {
                if (ips != null)
                    ips.close();
            } catch (IOException ignored) {
            }
        }
        return buff;
    }

    /**
     * 　　* 将原数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     * <p>
     * 　　* @param sourceDate
     * <p>
     * 　　* @param formatLength
     * <p>
     * 　　* @return 重组后的数据
     * <p>
     */
    public static String formatInt2String(int sourceDate, int formatLength) {
        // formatLength 字符总长度为 formatLength
        return String.format("%0" + formatLength + "d", sourceDate);
    }

    public static String formatLong2String(long sourceDate, int formatLength) {
        // formatLength 字符总长度为 formatLength
        return String.format("%0" + formatLength + "d", sourceDate);
    }


    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String moneyFen2Yuan(long amount) {
        return new DecimalFormat("0.00").format(BigDecimal.valueOf(amount).divide(new BigDecimal(100)));
//        return String.format("%.2f", BigDecimal.valueOf(amount).divide(new BigDecimal(100)).doubleValue());
    }


}
