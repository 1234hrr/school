package cn.zwz.talk.serviceimpl;

import cn.zwz.talk.mapper.MessageMapper;
import cn.zwz.talk.entity.Message;
import cn.zwz.talk.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 家校留言 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IMessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;
}