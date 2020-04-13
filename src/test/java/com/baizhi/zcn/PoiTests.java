package com.baizhi.zcn;

import com.baizhi.zcn.dao.VideoMapper;
import com.baizhi.zcn.entity.Emp;
import com.baizhi.zcn.po.VideoPo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class PoiTests {



    @Test
    public void testqueryss(){

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表  参数：工作表的名字  默认按照：sheet0,sheet1,...命名
        Sheet sheet = workbook.createSheet("学生信息1");

        Sheet sheet2 = workbook.createSheet("学生名单");

        Row row1 = sheet2.createRow(2);
        row1.createCell(0).setCellValue("lalala");

        //创建一行  参数：行下标(下表从0开始)
        Row row = sheet.createRow(4);

        //创建一个单元格  参数：单元格下标(下表从0开始)
        Cell cell = row.createCell(2);

        //给单元格设置内容
        cell.setCellValue("这是第5行第3个单元格");

        //导出单元格
        try {
            workbook.write(new FileOutputStream(new File("D://186-poi.xls")));

            //释放资源
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExport(){

        //1.数据查询   List<Object>

        ArrayList<Emp> emps = new ArrayList<>();
        emps.add(new Emp("1","小黑黑",18,new Date()));
        emps.add(new Emp("2","小拜拜",56,new Date()));
        emps.add(new Emp("3","小蛋黄",54,new Date()));
        emps.add(new Emp("4","小狗蛋",21,new Date()));
        emps.add(new Emp("5","小黑s",18,new Date()));

        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();

        //创建一个工作表  参数：工作表的名字  默认按照：sheet0,sheet1,...命名
        Sheet sheet = workbook.createSheet("学生信息1");

        //设置列宽   参数：列索引，列宽值  单位  1/256
        sheet.setColumnWidth(3,20*256);

        //创建字体样式
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 20); //设置字号
        font.setFontName("微软雅黑");  //设置字体
        font.setBold(true);  //加粗
        font.setColor(Font.COLOR_RED);  //颜色
        font.setItalic(true);    //斜体
        font.setUnderline(FontFormatting.U_SINGLE);  //下划线

        //创建标题样式
        CellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);  //居中
        titleCellStyle.setFont(font);  //将设置的字体样式交给样式对象

        //创建一个标题行
        Row titleRow = sheet.createRow(0);
        //行高  参数：行高度  1/20
        titleRow.setHeight((short)(25*20));
        //创建标题单元格
        Cell titleCell1 = titleRow.createCell(0);

        //给单元格设置指定样式
        titleCell1.setCellStyle(titleCellStyle);

        //给标题赋值
        titleCell1.setCellValue("186班学生信息");

        //合并单元格  参数：(firstRow(行开始),lastRow(行结束),firstCol(列开始),lastCol(列结束))
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 3);
        CellRangeAddress cellAddresses1 = new CellRangeAddress(2, 6, 4, 4);

        //将设置好的的合并单元格策略给sheet
        sheet.addMergedRegion(cellAddresses);
        sheet.addMergedRegion(cellAddresses1);

        //目录行
        String[] titles={"ID","名字","年龄","生日"};

        //创建目录行
        Row row = sheet.createRow(1);

        //行高  参数：行高度  1/20
        row.setHeight((short)(15*20));

        //遍历数组  处理目录数据
        for (int i = 0; i < titles.length; i++) {

            //创建一个单元格
            Cell cell = row.createCell(i);

            //给单元格赋值
            cell.setCellValue(titles[i]);
        }

        //创建一个日期格式对象
        DataFormat format = workbook.createDataFormat();
        //设置日期格式
        short formats = format.getFormat("yyyy年MM月dd日");

        //创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();

        //将设置好的日期格式给样式对象
        cellStyle.setDataFormat(formats);

        //处理数据行
        //遍历数据
        for (int i = 0; i < emps.size(); i++) {

            //遍历一条数据  创建一行
            Row rows = sheet.createRow(i+2);

            //创建单元格  给单元格赋值
            rows.createCell(0).setCellValue(emps.get(i).getId());
            rows.createCell(1).setCellValue(emps.get(i).getName());
            rows.createCell(2).setCellValue(emps.get(i).getAge());

            //创建日期单元格
            Cell cell = rows.createCell(3);
            //给单元格设置指定样式
            cell.setCellStyle(cellStyle);
            //给单元格赋值
            cell.setCellValue(emps.get(i).getBir());
        }

        //导出单元格
        try {
            workbook.write(new FileOutputStream(new File("D://186-poi.xls")));

            //释放资源
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inport(){

        //获取表格中数据  读入程序中    List<Emp>
        //插入数据库

        try {
            //获取要导入的文件
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File("D://186-poi.xls")));

            //根据文档获取工作表
            Sheet sheet = workbook.getSheet("学生信息1");

            //获取最后一行下表
            //int lastRowNum = sheet.getLastRowNum();

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {

                //获取行
                Row row = sheet.getRow(i);

                //获取行数据
                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                double ages = row.getCell(2).getNumericCellValue();
                int age=(int)ages;
                Date bir = row.getCell(3).getDateCellValue();

                Emp emp = new Emp(id, name, age, bir);
                System.out.println("向数据库插入："+emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
