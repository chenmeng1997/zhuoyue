package com.cm.zhuoyue.user.service.impl;

import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.mapper.SysUserInfoMapper;
import com.cm.zhuoyue.user.service.ISysUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理平台用户信息表 服务实现类
 * </p>
 *
 * @author 陈萌
 * @since 2021-06-09
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;


    @Override
    public Integer insertSysUser(SysUserInfo sysUserInfo) {
        return sysUserInfoMapper.insert(sysUserInfo);
    }

    @Override
    public Integer sysUserDel(List<Integer> ids) {
        return sysUserInfoMapper.deleteBatchIds(ids);
    }
}
