package com.example.javaee.controller.admin;

import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.service.admin.IAdminCategoryService;
import com.example.javaee.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@Api(tags = "管理员 新闻类别相关接口")
public class AdminCategoryController {

    @Autowired
    IAdminCategoryService categoryService;

    //删除某个新闻类别
    @ApiOperation(value = "管理员删除某个新闻类别", notes = "需要提供新闻类别id   category_id int类型")
    @PostMapping("changeCagegory")
    public Result<Void> changeCategoryState( Integer category_id){
        if(category_id==null){
            throw new BusinessException(ExceptionEnum.PARAMS_接收参数错误);
        }
        categoryService.changeCategoryState(category_id);
        Result result=new Result();
        result.setCode(200);
        result.setMsg("删除新闻类别成功");
        return result;

    }
    @ApiOperation(value = "管理员增加某个新闻类别", notes = "需要提供新闻类别名字   categoryName string类型")
    @PostMapping("addCategory")//增加某个新闻类别
    public Result<Void> addCategory(String categoryName){
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
