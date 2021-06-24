package com.cm.zhuoyue.publicinfo.controller;

import com.cm.zhuoyue.common.web.annotation.method.GenericResponse;
import com.cm.zhuoyue.user.api.client.UserApi;
import com.cm.zhuoyue.user.api.client.feign.SysUserInfoClient;
import com.cm.zhuoyue.user.api.dto.UsrSysUserAddRequest;
import com.cm.zhuoyue.user.api.dto.UsrSysUserInfoQueryRequest;
import com.cm.zhuoyue.user.api.dto.UsrSysUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * @author 陈萌
 * @date 2021/6/24 23:40
 */
@Slf4j
@RestController
public class TestController {

    //    @Autowired
//    private SysUserInfoClient sysUserInfoClient;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = UserApi.QUERY_SYS_USER_INFOSBY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<Object> getSysUserById(@RequestBody @Valid UsrSysUserInfoQueryRequest request) {
        Object result = restTemplate.postForObject("http://user-service/sysUser/querySysUserInfoById", request, Object.class);
        log.info("userInfo :{}", result);
        return new GenericResponse<>(result);
    }
}
