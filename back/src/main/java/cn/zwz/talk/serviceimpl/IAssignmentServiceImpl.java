package cn.zwz.talk.serviceimpl;

import cn.zwz.talk.mapper.AssignmentMapper;
import cn.zwz.talk.entity.Assignment;
import cn.zwz.talk.service.IAssignmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作业提交 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAssignmentServiceImpl extends ServiceImpl<AssignmentMapper, Assignment> implements IAssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;
}