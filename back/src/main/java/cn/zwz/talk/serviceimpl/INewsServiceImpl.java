package cn.zwz.talk.serviceimpl;

import cn.zwz.talk.mapper.NewsMapper;
import cn.zwz.talk.entity.News;
import cn.zwz.talk.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 校园通知 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class INewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    private NewsMapper newsMapper;
}