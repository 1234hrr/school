package cn.zwz.talk.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.talk.entity.News;
import cn.zwz.talk.service.INewsService;
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
@Api(tags = "校园通知管理接口")
@RequestMapping("/zwz/news")
@Transactional
public class NewsController {

    @Autowired
    private INewsService iNewsService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条校园通知")
    public Result<News> get(@RequestParam String id){
        return new ResultUtil<News>().setData(iNewsService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部校园通知个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iNewsService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部校园通知")
    public Result<List<News>> getAll(){
        return new ResultUtil<List<News>>().setData(iNewsService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询校园通知")
    public Result<IPage<News>> getByPage(@ModelAttribute News news ,@ModelAttribute PageVo page){
        QueryWrapper<News> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(news.getTitle())) {
            qw.like("title",news.getTitle());
        }
        if(!ZwzNullUtils.isNull(news.getContent())) {
            qw.like("content",news.getContent());
        }
        IPage<News> data = iNewsService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<News>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改校园通知")
    public Result<News> saveOrUpdate(News news){
        if(iNewsService.saveOrUpdate(news)){
            return new ResultUtil<News>().setData(news);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增校园通知")
    public Result<News> insert(News news){
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑校园通知")
    public Result<News> update(News news){
        iNewsService.saveOrUpdate(news);
        return new ResultUtil<News>().setData(news);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除校园通知")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iNewsService.removeById(id);
        }
        return ResultUtil.success();
    }
}
