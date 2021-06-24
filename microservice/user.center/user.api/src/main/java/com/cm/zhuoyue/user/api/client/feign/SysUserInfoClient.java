package com.cm.zhuoyue.user.api.client.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.zhuoyue.common.web.annotation.method.GenericResponse;
import com.cm.zhuoyue.user.api.client.UserApi;
import com.cm.zhuoyue.user.api.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author 陈萌
 * @date 2021/6/23 21:56
 */
@Component
@FeignClient(value = UserApi.APPLICATION_NAME)
public interface SysUserInfoClient {

    /**
     * 管理平台 用户 增
     */
    @PostMapping(value = UserApi.ADD_SYS_USER_INFO, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<Integer> insertSysUser(@RequestBody @Valid UsrSysUserAddRequest request);

    /**
     * 管理平台 用户 删
     */
    @PostMapping(value = UserApi.DEL_SYS_USER_INFOS_BY_IDS, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<Boolean> delSysUser(@RequestBody @Valid UsrSysUserDelRequest request);

    /**
     * 管理平台 用户 改
     */
    @PostMapping(value = UserApi.EDIT_SYS_USER_INFO_BY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<Boolean> updateSysUser(@RequestBody @Valid UsrSysUserUpdateRequest request);

    /**
     * 管理平台 用户 单查据id
     */
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFOSBY_ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<UsrSysUserInfoResponse> getSysUserById(@RequestBody @Valid UsrSysUserInfoQueryRequest request);

    /**
     * 管理平台 用户 列表
     */
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFO_LIST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    GenericResponse<UsrSysUserInfoListResponse> getSysUserList(@RequestBody @Valid UsrSysUserInfoQueryListRequest request);

    /**
     * 管理平台 用户 列表分页
     */
    @PostMapping(value = UserApi.QUERY_SYS_USER_INFO_PAGE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    IPage<UsrSysUserInfoResponse> queryUserForPage(@RequestBody @Valid UsrSysUserInfoQueryListRequest request);

}
