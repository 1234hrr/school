package cn.zwz.talk.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.MyStudent;
import cn.zwz.talk.service.IMyStudentService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "学生管理接口")
@RequestMapping("/zwz/myStudent")
@Transactional
public class MyStudentController {

    @Autowired
    private IMyStudentService iMyStudentService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条学生")
    public Result<MyStudent> get(@RequestParam String id){
        return new ResultUtil<MyStudent>().setData(iMyStudentService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部学生个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iMyStudentService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部学生")
    public Result<List<MyStudent>> getAll(){
        return new ResultUtil<List<MyStudent>>().setData(iMyStudentService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询学生")
    public Result<IPage<MyStudent>> getByPage(@ModelAttribute MyStudent myStudent ,@ModelAttribute PageVo page){
        QueryWrapper<MyStudent> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(myStudent.getName())) {
            qw.like("name",myStudent.getName());
        }
        if(!ZwzNullUtils.isNull(myStudent.getLevel())) {
            qw.like("level",myStudent.getLevel());
        }
        IPage<MyStudent> data = iMyStudentService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<MyStudent>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改学生")
    public Result<MyStudent> saveOrUpdate(MyStudent myStudent){
        if(iMyStudentService.saveOrUpdate(myStudent)){
            return new ResultUtil<MyStudent>().setData(myStudent);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增学生")
    public Result<MyStudent> insert(MyStudent myStudent){
        iMyStudentService.saveOrUpdate(myStudent);
        return new ResultUtil<MyStudent>().setData(myStudent);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑学生")
    public Result<MyStudent> update(MyStudent myStudent){
        iMyStudentService.saveOrUpdate(myStudent);
        return new ResultUtil<MyStudent>().setData(myStudent);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除学生")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iMyStudentService.removeById(id);
        }
        return ResultUtil.success();
    }
}
