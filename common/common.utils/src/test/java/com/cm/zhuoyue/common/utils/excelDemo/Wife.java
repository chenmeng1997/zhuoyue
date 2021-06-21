package com.cm.zhuoyue.common.utils.excelDemo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

/**
 * @author 陈萌
 * @date 2021/6/11 16:40
 */
@Data
@ExcelTarget("hobbyEntity")
public class Wife {

    @Excel(name = "年龄", orderNum = "0", type = 10)
    private Integer age;

    @Excel(name = "姓名", orderNum = "1")
    private String name;
}
