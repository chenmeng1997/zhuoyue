package com.cm.zhuoyue.common.utils.excelDemo;

import cn.afterturn.easypoi.excel.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 陈萌
 * @date 2021/6/11 14:22
 */
@Data
@ExcelTarget("userEntity")
public class User implements Serializable {

    /**
     * id
     */
    @ExcelIgnore
    private String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", height = 20, width = 30, isImportField = "true_0", needMerge = true)
    private String name;
    /**
     * 性别
     */
    @Excel(name = "性别", replace = {"男_0", "女_1"}, suffix = "生", isImportField = "true_1")
    private int sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_2", width = 20)
    private Date birthday;

    /**
     * 一对一
     */
    @ExcelEntity(name = "妻子")
    private Wife wife;
    /**
     * 集合 一对多
     */
    @ExcelCollection(name = "爱好", orderNum = "3")
    private List<Hobby> hobbyList;

}
