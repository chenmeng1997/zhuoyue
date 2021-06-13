package com.cm.zhuoyue.common.utils.excelDemo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈萌
 * @date 2021/6/11 16:14
 */
@Data
@ExcelTarget("hobbyEntity")
public class Hobby implements Serializable {

    @Excel(name = "爱好", orderNum = "3")
    private String hobbyName;

    @Excel(name = "描述", orderNum = "4")
    private String hobbyDepict;

}
