package com.cm.zhuoyue.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 陈萌
 * @date 2021/6/22 14:58
 */
@Data
@ApiModel(value = "SysUser 修改", description = "管理平台用户 用户修改")
public class UsrSysUserUpdateRequest {

    @ApiModelProperty(value = "密码中必须包含大小写 字母、数字、特称字符，至少8个字符，最多20个字符")
    @JsonFormat(pattern = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,20}")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "组织机构ID")
    private Integer spId;

    @ApiModelProperty(value = "所属单位")
    private String source;

    @ApiModelProperty(value = "手机号")
    @JsonFormat(pattern = "/^0?(13[0-9]|15[012356789]|18[012346789]|14[57]|17[678]|170[059]|14[57]|166|19[89])[0-9]{8}$/")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建当前账户的账户id")
    private String oprateId;

    @ApiModelProperty(value = "账号类型 0、平台用户 1、商户用户")
    private String type;

}
