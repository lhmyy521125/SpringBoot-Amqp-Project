package cn.toher.rabbitmq.project.task;

import cn.toher.rabbitmq.project.message.entity.Message;
import cn.toher.rabbitmq.project.message.service.MessageService;
import cn.toher.rabbitmq.project.order.entity.Order;
import cn.toher.rabbitmq.project.order.service.OrderService;
import cn.toher.rabbitmq.project.producer.RabbitOrderSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/25 19:24
 */
@Component
public class RetryMessageTasker {

	
	@Resource
	private RabbitOrderSender rabbitOrderSender;

	@Resource
	private MessageService messageServiceImpl;

	@Resource
	private OrderService orderServiceImpl;
	
	@Scheduled(initialDelay = 3000, fixedDelay = 10000)
	public void reSend(){
		System.err.println("---------------定时任务开始---------------");
		//获取status = 0 and retry_count<=5 的数据 进行重新发送
		List<Message> list = messageServiceImpl.getMessage();
		for(Message m : list){
			//如果重试次数已经达到5次，更新发送状态status = 2
			if(m.getRetryCount()>=5){
				m.setStatus(2);
				messageServiceImpl.editObjectSelective(m);
			}else{
				Order o = orderServiceImpl.getOrderByMessageId(m.getMessageId());
				try {
				rabbitOrderSender.sendMsg(o);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
