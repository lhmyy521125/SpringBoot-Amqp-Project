package cn.toher.rabbitmq;

import cn.toher.rabbitmq.project.order.entity.Order;
import cn.toher.rabbitmq.project.order.service.OrderService;
import cn.toher.rabbitmq.project.producer.RabbitOrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Resource
    private OrderService orderServiceImpl;
    @Resource
    private RabbitOrderSender rabbitOrderSender;


    @Test
    public void contextLoads() {
    }

    @Test
    public void SendOrder() throws Exception {
        Order o = new Order();
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        o.setMessageId(uuid);
        o.setName(uuid + " : 订单消息");
        int success = orderServiceImpl.saveObject(o);
        if(success>0) {
            rabbitOrderSender.sendMsg(o);
        }
    }
}
