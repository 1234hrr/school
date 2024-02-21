package cn.zwz.talk.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.Assignment;
import cn.zwz.talk.entity.MyStudent;
import cn.zwz.talk.service.IAssignmentService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.talk.service.IMyStudentService;
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
@Api(tags = "作业提交管理接口")
@RequestMapping("/zwz/assignment")
@Transactional
public class AssignmentController {

    @Autowired
    private IAssignmentService iAssignmentService;

    @Autowired
    private IMyStudentService iMyStudentService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条作业提交")
    public Result<Assignment> get(@RequestParam String id){
        return new ResultUtil<Assignment>().setData(iAssignmentService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部作业提交个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iAssignmentService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部作业提交")
    public Result<List<Assignment>> getAll(){
        return new ResultUtil<List<Assignment>>().setData(iAssignmentService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询作业提交")
    public Result<IPage<Assignment>> getByPage(@ModelAttribute Assignment assignment ,@ModelAttribute PageVo page){
        QueryWrapper<Assignment> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        User user = iUserService.getById(currUser.getId());
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",user.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("stu_id",user.getMyStudent());
        }
        if(!ZwzNullUtils.isNull(assignment.getTitle())) {
            qw.like("title",assignment.getTitle());
        }
        IPage<Assignment> data = iAssignmentService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Assignment>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改作业提交")
    public Result<Assignment> saveOrUpdate(Assignment assignment){
        if(iAssignmentService.saveOrUpdate(assignment)){
            return new ResultUtil<Assignment>().setData(assignment);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增作业提交")
    public Result<Assignment> insert(Assignment assignment){
        MyStudent s = iMyStudentService.getById(assignment.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        assignment.setStuName(s.getName());
        assignment.setDate(DateUtil.today());
        iAssignmentService.saveOrUpdate(assignment);
        return new ResultUtil<Assignment>().setData(assignment);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑作业提交")
    public Result<Assignment> update(Assignment assignment){
        MyStudent s = iMyStudentService.getById(assignment.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        assignment.setStuName(s.getName());
        iAssignmentService.saveOrUpdate(assignment);
        return new ResultUtil<Assignment>().setData(assignment);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除作业提交")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iAssignmentService.removeById(id);
        }
        return ResultUtil.success();
    }
}
