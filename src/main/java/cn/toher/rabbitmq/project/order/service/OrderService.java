package cn.toher.rabbitmq.project.order.service;

import cn.toher.rabbitmq.project.order.entity.Order;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/25 19:22
 */
public interface OrderService {

    int saveObject(Order o);

    int editObject(Order o);

    int saveObjectSelective(Order o);

    int editObjectSelective(Order o);

    Order getOrderByMessageId(String messageId);

}
