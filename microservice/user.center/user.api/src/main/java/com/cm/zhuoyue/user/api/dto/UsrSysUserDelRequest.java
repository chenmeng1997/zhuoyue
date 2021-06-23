package com.cm.zhuoyue.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 陈萌
 * @date 2021/6/23 9:17
 */
@Data
@ApiModel(value = "SysUserInfo 批量删除请求", description = "管理平台用户 批量删除")
public class UsrSysUserDelRequest {

    @NotNull
    @ApiModelProperty(value = "用户Id")
    private List<Integer> ids;

}
