package com.baizhi.zcn.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @ExcelIgnore
    private String id;

    @Excel(name = "名字",needMerge = true)
    private String name;

    @Excel(name = "年龄",needMerge = true)
    private Integer age;

    //关系属性
    @ExcelCollection(name = "学生信息")
    private List<Emp> empList;
}
