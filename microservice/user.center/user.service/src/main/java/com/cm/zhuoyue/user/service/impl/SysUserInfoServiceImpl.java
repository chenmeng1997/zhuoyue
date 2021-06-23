package com.cm.zhuoyue.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cm.zhuouyue.common.utils.baseUtils.MD5Utils;
import com.cm.zhuouyue.common.utils.baseUtils.StringUtil;
import com.cm.zhuoyue.common.web.enums.BizErrorCodeEnum;
import com.cm.zhuoyue.common.web.exception.BizException;
import com.cm.zhuoyue.user.api.dto.*;
import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.mapper.SysUserInfoMapper;
import com.cm.zhuoyue.user.service.ISysUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String pwd = MD5Utils.encode(request.getPassword() + salt);
        userInfo.setPassword(pwd);
        sysUserInfoMapper.insert(userInfo);
        if (userInfo.getId() == null) {
            throw new BizException(BizErrorCodeEnum.INSERT_ERROR);
        }
        return userInfo.getId();
    }

    @Override
    public Boolean delSysUser(UsrSysUserDelRequest request) {
        if (CollectionUtils.isNotEmpty(request.getIds())) {
            int result = sysUserInfoMapper.deleteBatchIds(request.getIds());
            if (result == 0) {
                throw new BizException(BizErrorCodeEnum.DELETE_EXCEPTION);
            }
        }
        return true;
    }

    @Override
    public Boolean updateSysUser(UsrSysUserUpdateRequest request) {
        SysUserInfo userInfo = new SysUserInfo();
        BeanUtils.copyProperties(request, userInfo);
        int result = sysUserInfoMapper.updateById(userInfo);
        if (result == 0) {
            throw new BizException(BizErrorCodeEnum.UPDATE_EXCEPTION);
        }
        return true;
    }

    @Override
    public UsrSysUserInfoResponse getSysUserById(UsrSysUserInfoQueryRequest request) {
        UsrSysUserInfoResponse response = new UsrSysUserInfoResponse();
        SysUserInfo userInfo = sysUserInfoMapper.selectById(request.getId());
        BeanUtils.copyProperties(userInfo, response);
        return response;
    }

    @Override
    public UsrSysUserInfoListResponse getSysUserList(UsrSysUserInfoQueryListRequest request) {
        SysUserInfo userInfo = new SysUserInfo();
        UsrSysUserInfoListResponse response = new UsrSysUserInfoListResponse();
        List<UsrSysUserInfoResponse> usrSysUserInfoList = new ArrayList<>();
        BeanUtils.copyProperties(request, userInfo);
        QueryWrapper<SysUserInfo> wrapper = setUserInfoListQueryWrapper(userInfo);
        List<SysUserInfo> infoList = sysUserInfoMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(infoList)) {
            infoList.stream().forEach(x -> {
                UsrSysUserInfoResponse infoResponse = new UsrSysUserInfoResponse();
                BeanUtils.copyProperties(x, infoResponse);
                usrSysUserInfoList.add(infoResponse);
            });
            response.setUsrSysUserInfoList(usrSysUserInfoList);
        }
        return response;
    }

    @Override
    public IPage<UsrSysUserInfoResponse> queryUserForPage(UsrSysUserInfoQueryListRequest request) {
        //参数一是当前页，参数二是每页个数
        IPage<SysUserInfo> userPage = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<UsrSysUserInfoResponse> response = new Page<>();
        SysUserInfo userInfo = new SysUserInfo();
        QueryWrapper<SysUserInfo> wrapper = setUserInfoListQueryWrapper(userInfo);
        userPage = sysUserInfoMapper.selectPage(userPage, wrapper);
        BeanUtils.copyProperties(userPage, response);
        List<UsrSysUserInfoResponse> list = new ArrayList<>(userPage.getRecords().size());
        if (CollectionUtils.isNotEmpty(userPage.getRecords())) {
            UsrSysUserInfoResponse usrSysUserInfoResponse = new UsrSysUserInfoResponse();
            userPage.getRecords().stream().forEach(x -> {
                        BeanUtils.copyProperties(x, usrSysUserInfoResponse);
                        list.add(usrSysUserInfoResponse);
                    }
            );
            response.setRecords(list);
        }
        return response;
    }

    private QueryWrapper<SysUserInfo> setUserInfoListQueryWrapper(SysUserInfo userInfo) {
        String fullName = null;
        String source = null;
        if (StringUtils.isNotEmpty(userInfo.getFullName())) {
            fullName = userInfo.getFullName();
            userInfo.setFullName(null);
        }
        if (StringUtils.isNotEmpty(userInfo.getSource())) {
            source = userInfo.getSource();
            userInfo.setSource(null);
        }
        if (StringUtils.isNotEmpty(userInfo.getFullName())) {
            fullName = userInfo.getFullName();
            userInfo.setFullName(null);
        }
        QueryWrapper<SysUserInfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(userInfo);
        if (StringUtils.isNotEmpty(fullName)) {
            wrapper.like("FULL_NAME", fullName);
        }
        if (StringUtils.isNotEmpty(source)) {
            wrapper.like("SOURCE", source);
        }
        return wrapper;
    }

}
