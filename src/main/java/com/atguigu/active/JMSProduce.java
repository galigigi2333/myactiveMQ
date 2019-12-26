package com.atguigu.active;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProduce {
    private  static  final String QUEUENAME="queue0805";
    private  static  final String URL="tcp://192.168.62.137:61616";
    public static void main(String[] args) throws JMSException {
    //
        //创建工厂类
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection connection =factory.createConnection();
        //打开链接
        connection.start();
        //创建session
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(QUEUENAME);
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //创建消息message
        TextMessage textMessage = session.createTextMessage();

        for (int i = 1; i <=3; i++) {
            textMessage.setText("mes---"+i);
            producer.send(textMessage);
        }
        System.out.println("发送完成");
        producer.close();
        session.commit();
        session.close();
        connection.close();
    }





}
