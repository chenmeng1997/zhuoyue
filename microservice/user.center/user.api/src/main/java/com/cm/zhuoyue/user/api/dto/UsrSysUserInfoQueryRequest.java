package com.cm.zhuoyue.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈萌
 * @date 2021/6/23 14:44
 */
@Data
@ApiModel(value = "SysUser 单查", description = "管理平台用户 单查")
public class UsrSysUserInfoQueryRequest {

    @NotNull(message = "Id 不能为空")
    @ApiModelProperty(value = "用户ID")
    private Integer id;

}
