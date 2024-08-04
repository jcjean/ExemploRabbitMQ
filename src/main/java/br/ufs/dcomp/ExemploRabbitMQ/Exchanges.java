package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Exchanges {

  private final static String QUEUE_NAME = "minha-fila";
  private final static String EXCHANGE_FAN_NAME = "E1"; //fanout
  private final static String EXCHANGE_DIR_NAME = "E2"; //direct
  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("44.206.104.42"); // Alterar
    factory.setUsername("jcadmin"); // Alterar
    factory.setPassword("jcpass"); // Alterar
    factory.setVirtualHost("/");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    boolean A=true;
    
    channel.exchangeDeclare(EXCHANGE_FAN_NAME, "fanout");
    channel.exchangeDeclare(EXCHANGE_DIR_NAME, "direct");
    
    for(int i=0;i<10;i++){
      if(i%2!=0){
        channel.queueBind(QUEUE_NAME+i, EXCHANGE_FAN_NAME, "");//fanout - todos os ímpares
      }else if(A==true){
        channel.queueBind(QUEUE_NAME+i, EXCHANGE_DIR_NAME, "A");//direct - pares de chaveA
        A = false;
      }else if(A==false){
        channel.queueBind(QUEUE_NAME+i,EXCHANGE_DIR_NAME, "B");//direct - pares de chaveB
        A = true;
      }
    }
    channel.close();
    connection.close(); 
  }
}
//criar um exchange fanout 'E1' que chaveie apenas os impares e depois criar um excg direct 'E2' que chaveia apenas os pares mas alternando entre msgA e msgB.
/*channel.exchangeDeclare(EXCHANGE_NAME, "direct");
channel.queueBind(queueName, EXCHANGE_NAME, "");
*/