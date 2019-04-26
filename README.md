# SpringBoot-Amqp-Project
使用Spring AMQP实现100%的可靠性投递 DEMO代码
# 1、前言
接着我们上一讲相信大家对Spring AMQP的整合和使用，已经有了一定的了解，今天我们主要以一个实战来讲解一下，在我们日常开发过程中常用的可靠性投递方案；
对于什么是可靠性投递，请大家移步我之前的一篇文章 [RabbitMQ教程系列（五）RabbitMQ可靠性投递生产者确认机制](https://blog.csdn.net/lhmyy521125/article/details/88064322)

# 2、流程
或许每个企业（每个人）都有着自己的一套更好的方案，这里我们仅仅以中小企业中比较常用的一种来进行讲解；该方案的流程图如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190425143908432.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xobXl5NTIxMTI1,size_16,color_FFFFFF,t_70)
**分析：** 我们模拟一个用户下单的操作，并进行后续的业务处理（消费者处理）；

 - 用户下单落库，订单表 和 消息状态记录表
 - 发送消息 ：将信息投递到 MQ broker
 - MQ broker Confirm ：返回确认消息 通知发送端我已经收到信息
 - 更新消息状态表的状态（成功 status = 1）
 - 定时任务获取数据库消息状态（投递中 status  = 0）
 - 消息重试发送

# 3、具体实现

样例使用的技术架构：
 - SpringBoot 2.1.4
 - Mybatis
 - TkMapper (tk.mybatis)
 - Druid
开发工具：IDEA

由于代码文件过多，在这里我们就不贴代码了
具体实现请参考Github项目地址：


**注：博主因为上一篇文章中已经创建好了Exchange 和对应的 Queue ,发送消息方法中我就采用了上次配置的Exchange 和 Queue 的信息了，Test测试包中，有一个下单的操作；
因为使用了定时任务，项目RUN起来后即可看到定时任务的执行； 如需测试重试，可以修改发送消息方法，不往MQ发送消息来模拟；**

# 4、结语
至此本篇100%的可靠性投递方案介绍到此，感兴趣的朋友可以参考样例代码进行了解，如果该DEMO样例帮助到了你，希望给我一个star，谢谢大家！
