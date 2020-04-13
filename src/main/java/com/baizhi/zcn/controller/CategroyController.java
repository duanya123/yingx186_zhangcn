package com.baizhi.zcn.controller;

import com.baizhi.zcn.entity.Category;
import com.baizhi.zcn.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("category")
public class CategroyController {

    @Resource
    CategoryService categoryService;

    @RequestMapping("queryByOnePage")
    public HashMap<String,Object> queryByOnePage(Integer page,Integer rows){
        return categoryService.queryByOnePage(page, rows);
    }

    @RequestMapping("queryByTwoPage")
    public HashMap<String,Object> queryByTwoPage(Integer page,Integer rows,String parentId){
        return categoryService.queryByTwoPage(page, rows, parentId);
    }

    @RequestMapping("edit")
    public Object edit(Category category,String oper){

        if (oper.equals("add")){
            categoryService.add(category);
        }
        if (oper.equals("edit")){
            categoryService.update(category);
        }
        if (oper.equals("del")){
            HashMap<String, Object> map = categoryService.delete(category);
            System.out.println(map.get("message"));
            return map;
        }
        return null;
    }

}
