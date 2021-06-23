package com.cm.zhuoyue.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 管理平台用户信息表
 * </p>
 *
 * @author 陈萌
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUserInfo对象", description="管理平台用户信息表")
public class SysUserInfo extends Model<SysUserInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    @TableField("ACCOUNT")
    private String account;

    @ApiModelProperty(value = "密码 MD5加密字母数字特殊符号组合")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "盐")
    @TableField("SALT")
    private String salt;

    @ApiModelProperty(value = "姓名")
    @TableField("FULL_NAME")
    private String fullName;

    @ApiModelProperty(value = "组织机构ID")
    @TableField("SP_ID")
    private Integer spId;

    @ApiModelProperty(value = "所属单位")
    @TableField("SOURCE")
    private String source;

    @ApiModelProperty(value = "手机号")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "创建当前账户的账户id")
    @TableField("OPRATE_ID")
    private String oprateId;

    @ApiModelProperty(value = "状态 0.注销 1.激活")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "是否删除 0：未删除，1：删除")
    @TableField(value = "IS_DELETE", fill = FieldFill.INSERT)
    @TableLogic
    private String isDelete;

    @ApiModelProperty(value = "账号类型 0、平台用户 1、商户用户")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "GMT_CREATE", fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "GMT_MODIFIED", fill = FieldFill.UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "注销时间")
    @TableField("GMT_CANCEL")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCancel;

    @ApiModelProperty(value = "激活时间")
    @TableField("GMT_ACTIVATE")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtActivate;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
