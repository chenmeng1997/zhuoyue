package com.cm.zhuoyue.common.web;

/**
 * @author 陈萌
 * @date 2021/6/22 15:53
 * 常用公量
 */
public interface RegexpConstants {
    /**
     * 数据一次请求最大添加量，50条
     */
    int ACTION_DATA_MAX_50 = 50;

    /**
     * 每5分钟cron表达式
     */
    String CRON_EVERY_FIVE_MINIUTES = "0 */5 * * * ?";

    /**
     * 每30分钟cron表达式
     */
    String CRON_EVERY_HALF_HOUR = "0 */30 * * * ?";


    /**
     * 每天的8点~20点，每10分钟执行一次
     */
    String CRON_EVERY_TEN_MINUTE = "24 0/10 8-20 * * ? ";

    /**
     * 验证电话和手机的正则表达式，允许为空值
     */
    String REGEXP_TELEPHONE_MOBILE_EMPTY = "^(\\d{3,4}-)?\\d{3,6}$|^[1]\\d{10}$|^$";

    /**
     * 验证电话和手机的正则表达式
     */
    String REGEXP_TELEPHONE_MOBILE = "^(\\d{3,4}-)?\\d{3,6}$|^[1]\\d{10}$";

    /**
     * 管理平台用户密码格式
     * 密码中必须包含大小写 字母、数字、特称字符，至少8个字符，最多20个字符
     */
    String REGEXP_SYS_USER_PWD = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,20}";
}
