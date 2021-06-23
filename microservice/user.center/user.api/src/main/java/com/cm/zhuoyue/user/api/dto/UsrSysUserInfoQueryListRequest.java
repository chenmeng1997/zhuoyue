package com.cm.zhuoyue.user.api.dto;

import com.cm.zhuoyue.common.web.template.page.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 陈萌
 * @date 2021/6/21 22:11
 */
@Data
@ApiModel(value = "SysUserInfo 列表请求", description = "管理平台用户 列表")
public class UsrSysUserInfoQueryListRequest extends PageRequest {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "组织机构ID")
    private Integer spId;

    @ApiModelProperty(value = "所属单位")
    private String source;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "创建当前账户的账户id")
    private String oprateId;

    @ApiModelProperty(value = "状态 0.注销 1.激活")
    private String status;

    @ApiModelProperty(value = "是否删除 0：未删除，1：删除")
    private String isDelete;

    @ApiModelProperty(value = "账号类型 0、平台用户 1、商户用户")
    private String type;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "注销时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCancel;

    @ApiModelProperty(value = "激活时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtActivate;

}
