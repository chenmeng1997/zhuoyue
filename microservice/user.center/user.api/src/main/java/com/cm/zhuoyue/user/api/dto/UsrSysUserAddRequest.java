package com.cm.zhuoyue.user.api.dto;

import com.cm.zhuoyue.common.web.RegexpConstants;
import com.cm.zhuoyue.common.web.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 陈萌
 * @date 2021/6/21 22:11
 */
@Data
@ApiModel(value = "SysUserInfo 添加请求", description = "管理平台用户 添加")
public class UsrSysUserAddRequest {

    @NotBlank(message = "账号 不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @Pattern(regexp = RegexpConstants.REGEXP_SYS_USER_PWD, message = "密码格式错误")
    @NotBlank(message = "密码 不能为空")
    @ApiModelProperty(value = "密码中必须包含大小写 字母、数字、特称字符，至少8个字符，最多20个字符")
    private String password;

    @NotBlank(message = "姓名 不能为空")
    @ApiModelProperty(value = "姓名")
    private String fullName;

    @NotNull(message = "组织机构ID 不能为空")
    @ApiModelProperty(value = "组织机构ID")
    private Integer spId;

    @ApiModelProperty(value = "所属单位")
    private String source;

    @Phone
    @NotBlank(message = "手机号 不能为空")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Email(message = "邮箱错误")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建当前账户的账户id")
    private String oprateId;

    @ApiModelProperty(value = "账号类型 0、平台用户 1、商户用户")
    private String type;

}
