package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Exchanges {

  private final static String QUEUE_NAME = "minha-fila";
  private final static String EXCHANGE_NAME = "E1"; //fanout
  private final static String EXCHANGE_NAME2 = "E2"; //direct
  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(""); // Alterar
    factory.setUsername(""); // Alterar
    factory.setPassword(""); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    boolean A=true;
    
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    channel.exchangeDeclare(EXCHANGE_NAME2, "direct");
    
    for(int i=0;i<10;i++){
      if(i%2!=0){
        channel.queueBind(QUEUE_NAME+i, "E1", "");//fanout - todos os ímpares
      }else if(A){
        channel.queueBind(QUEUE_NAME+i, "E2", "");//direct - pares de chaveA
        A = false;
      }else if(A==false){
        channel.queueBind(QUEUE_NAME+i,"E2", "");//direct - pares de chaveB
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