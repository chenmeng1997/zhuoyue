package com.cm.zhuoyue.user.api.client;

/**
 * @author 陈萌
 * @date 2021/6/21 15:34
 */
public interface UserApi {

    /**
     * 应用名称
     */
    String APPLICATION_NAME = "user-service";

    /**
     * 系统用户信息
     */
    String GET_SYS_USER_INFO = "/sysUser/getSysUserInfo";
}
