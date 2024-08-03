package br.ufs.dcomp.ExemploRabbitMQ;

import java.util.Scanner;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  //private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(""); // Alterar
    factory.setUsername(""); // Alterar
    factory.setPassword(""); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

                      //(queue-name, durable, exclusive, auto-delete, params); 
    //channel.queueDeclare(QUEUE_NAME, false,   false,     false,       null);
    Scanner scan = new Scanner(System.in);
                    //  (exchange, routingKey, props, message-body             );
    for(int i=0; i<10; i++){
      if(i%2!=0){
        String msg = scan.nextLine();
        channel.basicPublish("E1",       "", null,  msg.getBytes("UTF-8"));
        System.out.println(" [x] Mensagem enviada: '" + msg + "'");
      }else{
        String msg = scan.nextLine();
        channel.basicPublish("E2",       "", null,  msg.getBytes("UTF-8"));
        System.out.println(" [x] Mensagem enviada: '" + msg + "'");
      }
    }

    channel.close();
    connection.close();
    scan.close();
  }
}

//mvn compile assembly:single