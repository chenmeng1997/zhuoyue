package com.cm.zhuoyue.user.service.impl;

import com.alibaba.nacos.client.config.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cm.zhuouyue.common.utils.baseUtils.StringUtil;
import com.cm.zhuoyue.common.web.enums.BizErrorCodeEnum;
import com.cm.zhuoyue.common.web.exception.BizException;
import com.cm.zhuoyue.user.api.dto.UsrSysUserAddRequest;
import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.mapper.SysUserInfoMapper;
import com.cm.zhuoyue.user.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理平台用户信息表 服务实现类
 *
 * @author 陈萌
 * @since 2021-06-09
 */
@Slf4j
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;


    @Override
    public Integer insertSysUser(UsrSysUserAddRequest request) {
        SysUserInfo userInfo = new SysUserInfo();
        BeanUtils.copyProperties(request, userInfo);
        QueryWrapper<SysUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper -> {
            wrapper.eq("ACCOUNT", request.getAccount()).or().eq("MOBILE", request.getMobile());
            if (StringUtils.isNotEmpty(request.getEmail())) {
                wrapper.or().eq("EMAIL", request.getEmail());
            }
            return wrapper;
        });
        Integer count = sysUserInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            log.error("账号已存在或手机号/邮箱已使用: {}", request.getAccount());
            throw new BizException("账号已存在");
        }
        String salt = StringUtil.genRandomForNumStr(6);
        userInfo.setSalt(salt);
        MD5 md5 = MD5.getInstance();
        String pwd = md5.getMD5String(request.getPassword() + salt);
        userInfo.setPassword(pwd);
        sysUserInfoMapper.insert(userInfo);
        if (userInfo.getId() == null) {
            throw new BizException(BizErrorCodeEnum.INSERT_ERROR);
        }
        return userInfo.getId();
    }

    @Override
    public Integer DelSysUser(List<Integer> ids) {
        return sysUserInfoMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer updateSysUser() {
        return null;
    }
}
