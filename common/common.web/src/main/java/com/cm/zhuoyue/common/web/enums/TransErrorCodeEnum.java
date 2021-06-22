package com.cm.zhuoyue.common.web.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 陈萌
 * @date 2021/6/21 23:01
 */
public enum  TransErrorCodeEnum {
    ERROR_1("usr_1027","90001007"),
    //申诉大于2次
    ERROR_2("usr_1038","90001008"),
    //患者信息大于1条
    ERROR_3("","90001002"),
    //sub_channel不为空
    ERROR_4("usr_1036","1"),
    //手机黑名单
    ERROR_5("usr_6043","1"),
    //手机号已存在
    ERROR_6("usr_1040","1"),
    //用户已锁定
    ERROR_7("usr_1035","1"),
    //用户已存在
    ERROR_8("usr_1017","1"),
    //服务商信息不存在
    ERROR_9("17006","1"),
    //服务商id不能为空
    ERROR_10("20019","1"),
    //用户id不存在
    ERROR_11("usr_6018","1"),
    //用户手机号超过5次
    ERROR_12("usr_1024","90001008"),
    ERROR_1021("usr_1021","1"),

    //修改用户信息时不能修改用户姓名
    //修改用户信息时不能修改身份证号

    //用户信息不存在
    ERROR_13("usr_1005","1"),
    ERROR_14("17001","1"),

    //用户状态黑名单，用户已注销
    ERROR_15("","90001020"),
    //患者信息大于1条
    ERROR_16("","90001012"),

    //用户已锁定不能登录
    ERROR__17("usr_1035","1"),
    //用户已注销不能登录
    ERROR__18("usr_1039","1"),

    //数据库异常
    ERROR_19("","99999999"),

    //用户一年只能注销一次
    ERROR_20("usr_1023","1"),
    //此证件号尚未注册
    ERROR_21("usr_2002","1"),

    //分布式锁，操作频繁
    ERROR_22("10011","1"),

    ;

    private String code;
    private String value;

    TransErrorCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static TransErrorCodeEnum getByCode(String code) {
        for (TransErrorCodeEnum value : TransErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code){
        for (TransErrorCodeEnum value : TransErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }
}
