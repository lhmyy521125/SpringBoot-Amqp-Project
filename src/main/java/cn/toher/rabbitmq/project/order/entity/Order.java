package cn.toher.rabbitmq.project.order.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 同恒科技-李怀明
 * @Date: 2019/4/25 19:13
 */
@Table(name="th_order")
public class Order {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable = false, updatable = false)
    private Integer id;
    private String name;
    private String messageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
