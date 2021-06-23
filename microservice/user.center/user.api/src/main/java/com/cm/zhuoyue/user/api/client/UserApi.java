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

    /**
     * 系统用户信息 增
     */
    String ADD_SYS_USER_INFO = "/sysUser/addSysUserInfo";

    /**
     * 系统用户信息 删
     */
    String DEL_SYS_USER_INFOS_BY_IDS = "/sysUser/delSysUserInfoByIds";

    /**
     * 系统用户信息 改
     */
    String EDIT_SYS_USER_INFO_BY_ID = "/sysUser/editSysUserInfoById";

    /**
     * 系统用户信息 据id查
     */
    String QUERY_SYS_USER_INFOSBY_ID = "/sysUser/querySysUserInfoById";

    /**
     * 系统用户信息 列表
     */
    String QUERY_SYS_USER_INFO_LIST = "/sysUser/querySysUserInfoList";

    /**
     * 系统用户信息 列表
     */
    String QUERY_SYS_USER_INFO_PAGE = "/sysUser/querySysUserInfoPage";
}
