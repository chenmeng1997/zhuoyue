package com.cm.zhuoyue.common.web.constants;

/**
 * @author 陈萌
 * @date 2021/6/21 22:59
 */
public class BaseResultCode {
    /*系统异常码*/
    public static final String SUCCESS = "200";
    public static final String SUCCESS_MSG = "success";

    public static final String UNKOWN_ERROR = "404";
    public static final String UNKOWN_ERROR_MSG = "unkown error";

    public static final String SYSTEM_ERROR = "500";
    public static final String SYSTEM_ERROR_MSG = "system error";
    /**服务不可用*/
    public static final String SYSTEM_SERVICE_UNAVAILABLE_ERROR = "503";


    /*通用业务异常码*/
    public static final String PARAM_ERROR = "900";//参数错误
    public static final String HTTP_METHOD_NOT_SURPPORT = "901";//HTTP方法不支持
    public static final String GET_REDIS_LOCK_ERROR = "902";//获取redis分布式锁失败
    public static final String NO_PERMISSIONS = "903";//无权访问
    public static final String REPEATSUBMIT = "904";//重复提交
    public static final String THIRD_IP_WHITE_CHECK_ERROR = "905";//ip白名单校检失败
    //    public static final String FIRST_AUDIT_LAST_COMMENT = "907";//先审后评
    public static final String IS_ClOSE_COMMENT = "908";//评论关闭

    public static final String GO_TO_REGISTER= "90007";//用户注册

    public static final String NO_OLD_PASSWORD= "90008";//用户尚未设置密码



    /**
     * 缺少必要参数
     */
    public static final String REQUIRED_PARAMETER_MISSING = "909";

    /**
     * 资源不存在
     */
    public static final String RESOURCE_NOT_EXIST = "910";

    /**
     * 资源不存在
     */
    public static final String COMMON_ERROR = "912";

    public static final String SUCCESS_REMARK = "重发短信成功！";

    public static final String FAILED_REMARK = "重发短信失败！";

    public static final String FAILE = "400";

    /**
     * 信息上报错误
     */
    public static final String REPORT_ERROR = "912";
    //验证码成功
    public static final String TRANS_EXEC_SUCCESS = "0";

    public static final String TOKEN_IS_NULL = "913";

    /**
     * 登录超时
     */
    public static final String LOGIN_TIMEOUT = "000001";

    private static final String SAFE_PREFIX = "safe" + "_";
    public static final String IP_LOCKING = SAFE_PREFIX + "0";
    public static final String IP_IS_LOCKING =  SAFE_PREFIX + "2";

    /**
     * 账号不存在
     */
    public static final String ACCOUNT_ERROR = "930";

    public static final String ACCESS_RATE_LIMIT = "800";
}

