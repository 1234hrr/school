package cn.zwz.talk.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.MyStudent;
import cn.zwz.talk.entity.Performance;
import cn.zwz.talk.service.IMyStudentService;
import cn.zwz.talk.service.IPerformanceService;
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
@Api(tags = "课堂表现管理接口")
@RequestMapping("/zwz/performance")
@Transactional
public class PerformanceController {

    @Autowired
    private IPerformanceService iPerformanceService;

    @Autowired
    private IMyStudentService iMyStudentService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条课堂表现")
    public Result<Performance> get(@RequestParam String id){
        return new ResultUtil<Performance>().setData(iPerformanceService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部课堂表现个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iPerformanceService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部课堂表现")
    public Result<List<Performance>> getAll(){
        return new ResultUtil<List<Performance>>().setData(iPerformanceService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询课堂表现")
    public Result<IPage<Performance>> getByPage(@ModelAttribute Performance performance ,@ModelAttribute PageVo page){
        QueryWrapper<Performance> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        User user = iUserService.getById(currUser.getId());
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",user.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("stu_id",user.getMyStudent());
        }
        if(!ZwzNullUtils.isNull(performance.getTitle())) {
            qw.like("title",performance.getTitle());
        }
        if(!ZwzNullUtils.isNull(performance.getContent())) {
            qw.like("content",performance.getContent());
        }
        IPage<Performance> data = iPerformanceService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Performance>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改课堂表现")
    public Result<Performance> saveOrUpdate(Performance performance){
        if(iPerformanceService.saveOrUpdate(performance)){
            return new ResultUtil<Performance>().setData(performance);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增课堂表现")
    public Result<Performance> insert(Performance performance){
        MyStudent s = iMyStudentService.getById(performance.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        performance.setStuName(s.getName());
        iPerformanceService.saveOrUpdate(performance);
        return new ResultUtil<Performance>().setData(performance);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑课堂表现")
    public Result<Performance> update(Performance performance){
        MyStudent s = iMyStudentService.getById(performance.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        performance.setStuName(s.getName());
        iPerformanceService.saveOrUpdate(performance);
        return new ResultUtil<Performance>().setData(performance);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除课堂表现")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iPerformanceService.removeById(id);
        }
        return ResultUtil.success();
    }
}
