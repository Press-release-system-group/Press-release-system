package com.example.javaee.controller.admin;

import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminCategoryService;
import com.example.javaee.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminCategoryController {

    @Autowired
    IAdminCategoryService categoryService;

    //删除某个新闻类别
    @PostMapping("changeCagegory")
    public Result changeCategoryState(Integer category_id){
        if(category_id==null){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        categoryService.changeCategoryState(category_id);
        Result result=new Result();
        result.setCode(200);
        result.setMsg("删除新闻类别成功");
        return result;

    }

    @PostMapping("addCategory")//增加某个新闻类别
    public Result addCategory(String categoryName){
        if (categoryName==null||categoryName.equals("")||categoryName.length()==0){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        categoryService.addCategory(categoryName);
        Result result=new Result();
        result.setMsg("成功添加");
        result.setCode(200);
        return result;
    }
}
