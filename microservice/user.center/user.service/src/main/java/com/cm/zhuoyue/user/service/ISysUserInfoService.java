package com.cm.zhuoyue.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.zhuoyue.user.api.dto.*;
import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 管理平台用户信息表 服务类
 *
 * @author 陈萌
 * @since 2021-06-09
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    /**
     * 增
     */
    Integer insertSysUser (UsrSysUserAddRequest request);

    /**
     * 删
     */
    Boolean delSysUser(UsrSysUserDelRequest request);

    /**
     * 改
     */
    Boolean updateSysUser (UsrSysUserUpdateRequest request);

    /**
     * 据id查
     */
    UsrSysUserInfoResponse getSysUserById(UsrSysUserInfoQueryRequest request);

    /**
     * 列表
     */
    UsrSysUserInfoListResponse getSysUserList(UsrSysUserInfoQueryListRequest request);

    /**
     * 分页
     */
    IPage<UsrSysUserInfoResponse> queryUserForPage (UsrSysUserInfoQueryListRequest request);

}
