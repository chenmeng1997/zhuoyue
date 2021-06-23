package com.cm.zhuoyue.common.web.template.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页请求参数
 *
 * @author 陈萌
 * @date 2021/6/23 15:37
 */
@Data
@ApiModel(value = "分页请求参数", description = "通用")
public class PageRequest {

    @Min(value = 1, message = "页码大于0")
    @ApiModelProperty("页码")
    private Integer pageNum;

    @Min(value = 1, message = "页容量大于0")
    @ApiModelProperty("页容量")
    private Integer pageSize;

}
