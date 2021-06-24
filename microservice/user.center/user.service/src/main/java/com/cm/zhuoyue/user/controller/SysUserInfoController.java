package com.cm.zhuoyue.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.zhuoyue.common.web.annotation.method.GenericResponse;
import com.cm.zhuoyue.user.api.client.UserApi;
import com.cm.zhuoyue.user.api.dto.*;
import com.cm.zhuoyue.user.service.ISysUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 管理平台用户信息表 前端控制器
 *
 * @author 陈萌
 * @since 2021-06-09
 */
@Slf4j
@RestController
@Api(tags = "SysUserInfo Controller", value = "SysUserInfo api", description = "管理平台用户信息")
public class SysUserInfoController {

    @Autowired
    private ISysUserInfoService sysUserInfoService;

    @ApiOperation(value = "用户增", notes = "用户增")
    @PostMapping(value = UserApi.ADD_SYS_USER_INFO, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<Integer> insertSysUser(@RequestBody @Valid UsrSysUserAddRequest request) {
        Integer userId = sysUserInfoService.insertSysUser(request);
        log.info("userInfo :{}", userId);
        return new GenericResponse<>(userId);
    }

    @ApiOperation(value = "根据Id删除用户", notes = "删除用户")
    @PostMapping(value = UserApi.DEL_SYS_USER_INFOS_BY_IDS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<Boolean> delSysUser(@RequestBody @Valid UsrSysUserDelRequest request) {
        Boolean result = sysUserInfoService.delSysUser(request);
        return new GenericResponse<>(result);
    }

    @ApiOperation(value = "根据Id修改用户", notes = "修改用户")
    @PostMapping(value = UserApi.EDIT_SYS_USER_INFO_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<Boolean> updateSysUser(@RequestBody @Valid UsrSysUserUpdateRequest request) {
        Boolean result = sysUserInfoService.updateSysUser(request);
        return new GenericResponse<>(result);
    }

    @ApiOperation(value = "根据Id获取用户", notes = "根据Id获取用户")
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFOSBY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<UsrSysUserInfoResponse> getSysUserById(@RequestBody @Valid UsrSysUserInfoQueryRequest request) {
        UsrSysUserInfoResponse userInfo = sysUserInfoService.getSysUserById(request);
        log.info("userInfo :{}", userInfo);
        return new GenericResponse<>(userInfo);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFO_LIST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<UsrSysUserInfoListResponse> getSysUserList(@RequestBody @Valid UsrSysUserInfoQueryListRequest request) {
        UsrSysUserInfoListResponse sysUserList = sysUserInfoService.getSysUserList(request);
        return new GenericResponse<>(sysUserList);
    }

    @ApiOperation(value = "获取用户列表 分页", notes = "用户列表 分页")
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFO_PAGE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IPage<UsrSysUserInfoResponse> queryUserForPage(@RequestBody @Valid UsrSysUserInfoQueryListRequest request) {
        return sysUserInfoService.queryUserForPage(request);
    }

}

