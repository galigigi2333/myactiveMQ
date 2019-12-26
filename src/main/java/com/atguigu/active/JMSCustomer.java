package com.atguigu.active;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSCustomer {

    private  static  final String QUEUENAME="queue0805";
    private  static  final String URL="tcp://192.168.62.137:61616";
    public static void main(String[] args) throws JMSException {

        //创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        //创建连接
        Connection connection =factory.createConnection();
        //打开链接 创建session
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(QUEUENAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

          //异步非阻塞读取消息
        //创建监听器
          consumer.setMessageListener((message)->{
              if (message!=null&& message instanceof  TextMessage){
                  try {
                      System.out.println(((TextMessage) message).getText());
                  } catch (JMSException e) {
                      e.printStackTrace();
                  }
              }
          });


          //同步阻塞 方式读取
     /*   //读取消息
        while (true){

           TextMessage message = (TextMessage) consumer.receive();
            if (null!=message){
                System.out.println(message.getText());
            }else {
                break;
            }

        }*/


    }
}
