package com.atguigu.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMCustomer_topic {
    private  static  final String TOPIC="queue0805";
    private  static  final String URL="tcp://192.168.62.137:61616";

    public static void main(String[] args) throws JMSException {

        //创建工厂类
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection connection = factory.createConnection();
        //开启连接  创建session
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建主题
        Topic topic = session.createTopic(TOPIC);
        //创建 消费者
        MessageConsumer consumer = session.createConsumer(topic);

        //接受消息
        consumer.setMessageListener(message -> {
            if (message!=null&&message instanceof TextMessage){
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
