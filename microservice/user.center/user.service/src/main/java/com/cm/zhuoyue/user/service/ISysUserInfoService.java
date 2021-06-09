package com.cm.zhuoyue.user.service;

import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 管理平台用户信息表 服务类
 * </p>
 *
 * @author 陈萌
 * @since 2021-06-09
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    /**
     * 增
     */
    Integer insertSysUser (SysUserInfo sysUserInfo);

    /**
     * 删
     */
    Integer sysUserDel(List<Integer> ids);

}
