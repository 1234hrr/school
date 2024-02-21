package cn.zwz.talk.serviceimpl;

import cn.zwz.talk.mapper.ExamScoresMapper;
import cn.zwz.talk.entity.ExamScores;
import cn.zwz.talk.service.IExamScoresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试成绩 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IExamScoresServiceImpl extends ServiceImpl<ExamScoresMapper, ExamScores> implements IExamScoresService {

    @Autowired
    private ExamScoresMapper examScoresMapper;
}