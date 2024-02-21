package cn.zwz.talk.serviceimpl;

import cn.zwz.talk.mapper.MyStudentMapper;
import cn.zwz.talk.entity.MyStudent;
import cn.zwz.talk.service.IMyStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IMyStudentServiceImpl extends ServiceImpl<MyStudentMapper, MyStudent> implements IMyStudentService {

    @Autowired
    private MyStudentMapper myStudentMapper;
}