package com.cm.zhuoyue.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 陈萌
 * @date 2021/6/23 14:36
 */
@Data
@ApiModel(value = "SysUser 列表响应", description = "管理平台用户 列表")
public class UsrSysUserInfoListResponse {

    @ApiModelProperty(value = "SysUser 列表")
    private List<UsrSysUserInfoResponse> usrSysUserInfoList;

}
