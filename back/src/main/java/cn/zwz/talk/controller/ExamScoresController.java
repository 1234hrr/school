package cn.zwz.talk.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.ExamScores;
import cn.zwz.talk.entity.MyStudent;
import cn.zwz.talk.service.IExamScoresService;
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
@Api(tags = "考试成绩管理接口")
@RequestMapping("/zwz/examScores")
@Transactional
public class ExamScoresController {

    @Autowired
    private IExamScoresService iExamScoresService;

    @Autowired
    private IMyStudentService iMyStudentService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条考试成绩")
    public Result<ExamScores> get(@RequestParam String id){
        return new ResultUtil<ExamScores>().setData(iExamScoresService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部考试成绩个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iExamScoresService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部考试成绩")
    public Result<List<ExamScores>> getAll(){
        return new ResultUtil<List<ExamScores>>().setData(iExamScoresService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询考试成绩")
    public Result<IPage<ExamScores>> getByPage(@ModelAttribute ExamScores examScores ,@ModelAttribute PageVo page){
        QueryWrapper<ExamScores> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        User user = iUserService.getById(currUser.getId());
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",user.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("stu_id",user.getMyStudent());
        }
        if(!ZwzNullUtils.isNull(examScores.getTitle())) {
            qw.like("title",examScores.getTitle());
        }
        if(!ZwzNullUtils.isNull(examScores.getContent())) {
            qw.like("content",examScores.getContent());
        }
        IPage<ExamScores> data = iExamScoresService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ExamScores>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改考试成绩")
    public Result<ExamScores> saveOrUpdate(ExamScores examScores){
        if(iExamScoresService.saveOrUpdate(examScores)){
            return new ResultUtil<ExamScores>().setData(examScores);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增考试成绩")
    public Result<ExamScores> insert(ExamScores examScores){
        MyStudent s = iMyStudentService.getById(examScores.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        examScores.setStuName(s.getName());
        iExamScoresService.saveOrUpdate(examScores);
        return new ResultUtil<ExamScores>().setData(examScores);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑考试成绩")
    public Result<ExamScores> update(ExamScores examScores){
        MyStudent s = iMyStudentService.getById(examScores.getStuId());
        if(s == null) {
            return ResultUtil.error("学生不存在");
        }
        examScores.setStuName(s.getName());
        iExamScoresService.saveOrUpdate(examScores);
        return new ResultUtil<ExamScores>().setData(examScores);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除考试成绩")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iExamScoresService.removeById(id);
        }
        return ResultUtil.success();
    }
}
