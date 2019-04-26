package cn.toher.rabbitmq.project.order.service;

import cn.toher.rabbitmq.project.message.entity.Message;
import cn.toher.rabbitmq.project.message.mapper.MessageMapper;
import cn.toher.rabbitmq.project.order.entity.Order;
import cn.toher.rabbitmq.project.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/25 19:24
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private MessageMapper messageMapper;

    @Override
    public int saveObject(Order o) {
        Message m = new Message();
        m.setMessageId(o.getMessageId());
        messageMapper.insertSelective(m);
        return orderMapper.insert(o);
    }

    @Override
    public int editObject(Order o) {
        return orderMapper.updateByPrimaryKey(o);
    }

    @Override
    public int saveObjectSelective(Order o) {
        return orderMapper.insertSelective(o);
    }

    @Override
    public int editObjectSelective(Order o) {
        return orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order getOrderByMessageId(String messageId) {
        Order o = new Order();
        o.setMessageId(messageId);
        return orderMapper.selectOne(o);
    }
}
