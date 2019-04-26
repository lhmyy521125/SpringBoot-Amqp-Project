package cn.toher.rabbitmq.project.message.service;

import cn.toher.rabbitmq.project.message.entity.Message;

import java.util.List;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/26 11:10
 */
public interface MessageService {

    List<Message> getMessage();

    int editObjectSelective(Message m);


}
