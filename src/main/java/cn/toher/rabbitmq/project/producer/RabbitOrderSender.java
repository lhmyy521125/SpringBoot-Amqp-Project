package cn.toher.rabbitmq.project.producer;

import cn.toher.rabbitmq.project.message.service.MessageService;
import cn.toher.rabbitmq.project.order.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/3/19 18:50
 */
@Component
public class RabbitOrderSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageService messageServiceImpl;


    public void sendMsg(Order o) throws Exception {
        CorrelationData cd = new CorrelationData(o.getMessageId());
        rabbitTemplate.setReturnCallback(this);
        //rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("topicExchange001", "topicKey1.send",o.getName(),cd);
        //官方最新文档
        //从版本2.1开始，该CorrelationData对象具有ListenableFuture您可以用来获取结果的对象，而不是ConfirmCallback在模板上使用
        boolean isAck = cd.getFuture().get(10, TimeUnit.SECONDS).isAck();
        //如果ACK true 进行业务处理
        if(isAck){
            //获取发送消息时候的 CorrelationData
            System.err.println("通过cd.getFuture() 获取ID:" + cd.getId());
            //消息确认更新消息表
            cn.toher.rabbitmq.project.message.entity.Message m = new cn.toher.rabbitmq.project.message.entity.Message();
            m.setMessageId(cd.getId());
            m.setStatus(1);
            m.setUpdateTime(new Date());
            messageServiceImpl.editObjectSelective(m);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        System.err.println("--------------进入Return-------------");
        System.err.println("return exchange: " + exchange + ", routingKey: "
                + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
    }

//采用旧版的模式继续实现 RabbitTemplate.ConfirmCallback 即可
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("--------------进入Confirm-------------");
        System.err.println("correlationData: " + correlationData);
        System.err.println("ack: " + ack);
        if(!ack){
            System.err.println("异常处理....");
        }
    }
}
