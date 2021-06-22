package com.cm.zhuoyue.user.service;

import com.cm.zhuoyue.user.api.dto.UsrSysUserAddRequest;
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
    Integer DelSysUser(List<Integer> ids);

    /**
     * 改
     */
    Integer updateSysUser ();

}
