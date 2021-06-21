package com.cm.zhuoyue.user.controller;


import com.cm.zhuoyue.user.api.client.UserApi;
import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.service.ISysUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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

    @PostMapping(value = UserApi.GET_SYS_USER_INFO, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据Id获取用户", notes = "根据Id获取用户")
    public SysUserInfo getSysUserInfoById(@RequestBody @NotNull Long id) {
        SysUserInfo userInfo = sysUserInfoService.getById(id);
        log.info("userInfo :{}", userInfo);
        return userInfo;
    }

}

