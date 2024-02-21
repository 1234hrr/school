package cn.zwz.talk.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.Message;
import cn.zwz.talk.service.IMessageService;
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
@Api(tags = "家校留言管理接口")
@RequestMapping("/zwz/message")
@Transactional
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条家校留言")
    public Result<Message> get(@RequestParam String id){
        return new ResultUtil<Message>().setData(iMessageService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部家校留言个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iMessageService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部家校留言")
    public Result<List<Message>> getAll(){
        return new ResultUtil<List<Message>>().setData(iMessageService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询家校留言")
    public Result<IPage<Message>> getByPage(@ModelAttribute Message message ,@ModelAttribute PageVo page){
        QueryWrapper<Message> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(message.getContent())) {
            qw.like("content",message.getContent());
        }
        if(!ZwzNullUtils.isNull(message.getUserName())) {
            qw.like("user_name",message.getUserName());
        }
        IPage<Message> data = iMessageService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Message>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改家校留言")
    public Result<Message> saveOrUpdate(Message message){
        if(iMessageService.saveOrUpdate(message)){
            return new ResultUtil<Message>().setData(message);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增家校留言")
    public Result<Message> insert(Message message){
        User currUser = securityUtil.getCurrUser();
        message.setUserId(currUser.getId());
        message.setUserName(currUser.getNickname());
        message.setTime(DateUtil.now());
        message.setReplyContent("");
        message.setReplyId("");
        message.setReplyName("");
        message.setReplyTime("");
        iMessageService.saveOrUpdate(message);
        return new ResultUtil<Message>().setData(message);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑家校留言")
    public Result<Message> update(Message message){
        User currUser = securityUtil.getCurrUser();
        message.setReplyId(currUser.getId());
        message.setReplyName(currUser.getNickname());
        message.setReplyTime(DateUtil.now());
        iMessageService.saveOrUpdate(message);
        return new ResultUtil<Message>().setData(message);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除家校留言")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iMessageService.removeById(id);
        }
        return ResultUtil.success();
    }
}
