package com.baizhi.zcn;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.zcn.entity.Emp;
import com.baizhi.zcn.entity.Photo;
import com.baizhi.zcn.entity.Teacher;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class EasyPoiTests {

    @Test
    public void testExport(){

        //1.数据查询   List<Object>
        ArrayList<Emp> emps = new ArrayList<>();
        emps.add(new Emp("1","小黑黑",18,new Date()));
        emps.add(new Emp("2","小拜拜",56,new Date()));
        emps.add(new Emp("3","小蛋黄",54,new Date()));
        emps.add(new Emp("4","小狗蛋",21,new Date()));
        emps.add(new Emp("5","小黑s",18,new Date()));

        //导出的参数   参数：标题,工作表名
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");
        //配置工作表参数   参数：导出参数对象,要导出的对象,导出的数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Emp.class, emps);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D://186-easyPoi.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExports(){

        //1.数据查询   List<Object>
        ArrayList<Emp> emps = new ArrayList<>();
        emps.add(new Emp("1","小黑黑",18,new Date()));
        emps.add(new Emp("2","小拜拜",56,new Date()));
        emps.add(new Emp("3","小蛋黄",54,new Date()));
        emps.add(new Emp("4","小狗蛋",21,new Date()));
        emps.add(new Emp("5","小黑s",18,new Date()));

        Teacher teacher1 = new Teacher("1","suns",39,emps);
        Teacher teacher2 = new Teacher("2","xiaozhang",25,emps);

        ArrayList<Teacher> teachersList = new ArrayList<>();
        teachersList.add(teacher1);
        teachersList.add(teacher2);

        //导出的参数   参数：标题,工作表名
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");
        //配置工作表参数   参数：导出参数对象,要导出的对象,导出的数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Teacher.class, teachersList);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D://186-easyPoi.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImport(){

        //配置导入相关参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //表格标题行数,默认0
        params.setHeadRows(2);  //表头所占行  表头行数,默认1

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("D://186-easyPoi.xls"));
            List<Teacher> list = ExcelImportUtil.importExcel(fileInputStream,Teacher.class, params);

            for (Teacher teacher : list) {
                System.out.println("=======教师数据"+teacher);

                List<Emp> empList = teacher.getEmpList();
                for (Emp emp : empList) {
                    System.out.println("--学生数据"+emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExportPhoto(){

        //1.数据查询   List<Object>
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo("1","src/main/webapp/upload/photo/1585533158314-10.jpg",18,new Date()));
        photos.add(new Photo("2","D:\\186_code\\yingx186_zhangcn\\src\\main\\webapp\\upload\\photo\\1585298568675-10.jpg",56,new Date()));
        photos.add(new Photo("3","src/main/webapp/upload/photo/1585533360804-8.jpg",54,new Date()));
        photos.add(new Photo("4","src/main/webapp/upload/photo/1585533360804-8.jpg",21,new Date()));
        photos.add(new Photo("5","src/main/webapp/upload/photo/1585533360804-8.jpg",18,new Date()));

        //导出的参数   参数：标题,工作表名
        ExportParams exportParams = new ExportParams("应学App用户数据", "数据1");
        //配置工作表参数   参数：导出参数对象,要导出的对象,导出的数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Photo.class, photos);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("D://186-user.xls")));

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImportUser(){

        //配置导入相关参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //表格标题行数,默认0
        params.setHeadRows(1);  //表头所占行  表头行数,默认1
        params.setSaveUrl("D://abc");
        params.setNeedSave(false);

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("D://186-user.xls"));
            List<Photo> list = ExcelImportUtil.importExcel(fileInputStream,Photo.class, params);
            for (Photo photo : list) {
                System.out.println(photo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testImportUsers(){
        try {
            ImportParams params = new ImportParams();
            params.setNeedSave(true);
            params.setTitleRows(1);  //表格标题行数,默认0
            params.setHeadRows(1);  //表头所占行  表头行数,默认1

            FileInputStream fileInputStream = new FileInputStream(new File("D://186-user.xls"));
            List<Photo> result = ExcelImportUtil.importExcel(fileInputStream,Photo.class, params);

            for (int i = 0; i < result.size(); i++) {
                System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
            }

            //Assert.assertTrue(result.size() == 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
