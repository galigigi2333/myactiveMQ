package com.atguigu.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProduce_topic {
    private  static  final String TOPIC="queue0805";
    private  static  final String URL="tcp://192.168.62.137:61616";

    public static void main(String[] args) throws JMSException {
        //主题目的地
        //创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection connection =factory.createConnection();
        //开启连接 创建session、
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建 主题
        Topic topic = session.createTopic(TOPIC);
        //创建订阅
        MessageProducer producer = session.createProducer(topic);

        //创建消息
        TextMessage message = session.createTextMessage();

        //topic 主题 类似微信公众号 订阅后才会接受到消息而且只能接受订阅后的消息 并且多个消费者接受全部消息
        //所以先要开启消费者才能收到生产者发送的消息
        for (int i = 1; i <=3; i++) {
            message.setText("mes--"+i);
            producer.send(message);

        }
        producer.close();
        session.close();
        connection.close();

    }


}
