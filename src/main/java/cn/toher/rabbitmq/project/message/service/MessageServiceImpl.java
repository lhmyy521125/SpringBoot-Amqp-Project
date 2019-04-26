package cn.toher.rabbitmq.project.message.service;

import cn.toher.rabbitmq.project.message.entity.Message;
import cn.toher.rabbitmq.project.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/26 11:12
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessage() {
        Example example = new Example(Message.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        criteria.andLessThanOrEqualTo("retryCount", 5);
        example.setOrderByClause("id asc");
        return messageMapper.selectByExample(example);
    }

    @Override
    public int editObjectSelective(Message m) {
        return messageMapper.updateByPrimaryKeySelective(m);
    }
}
