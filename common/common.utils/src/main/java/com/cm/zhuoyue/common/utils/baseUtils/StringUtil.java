package com.cm.zhuoyue.common.utils.baseUtils;

import java.util.Random;

/**
 * @author 陈萌
 * @date 2021/6/21 22:37
 */
public class StringUtil {
    /**
     * 无参构造方法
     */
    private StringUtil() {
    }

    /**
     * 生成随即密码带字符
     *
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomForNumStr(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        // 生成的随机数
        int i;
        // 生成的密码的长度
        int count = 0;
        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，
            // 生成的数最大为36-1
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 判断多个字符串是否都不为空
     *
     * @param strs
     * @return
     */
    public static boolean isNotAllEmpty(String... strs) {
        for (String str : strs) {

            if (!isNotEmpty(str)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 针对敏感信息（如手机号等）屏蔽字符串
     *
     * @param s
     * @return
     */
    public static String maskString(String s) {
        if (isEmpty(s)) {
            return "";
        }
        if (s.length() == 1) {
            return "*";
        } else if (s.length() == 2) {
            return s.substring(0, 1) + "*";
        } else {
            return s.substring(0, 1) + "***" + s.substring(s.length() - 1, s.length());
        }
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
