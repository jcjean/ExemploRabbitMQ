package br.ufs.dcomp.ExemploRabbitMQ;

import java.util.Scanner;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  //private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("44.206.104.42"); // Alterar
    factory.setUsername("jcadmin"); // Alterar
    factory.setPassword("jcpass"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

                      //(queue-name, durable, exclusive, auto-delete, params); 
    //channel.queueDeclare(QUEUE_NAME, false,   false,     false,       null);
    Scanner scan = new Scanner(System.in);
                    //  (exchange, routingKey, props, message-body             );
    
    String msgFanout = scan.nextLine();
    channel.basicPublish("E1",       "", null,  msgFanout.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + msgFanout + "'");

    String msgDirectA = scan.nextLine();
    channel.basicPublish("E2",       "A", null,  msgDirectA.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + msgDirectA + "'");
      
    String msgDirectB = scan.nextLine();
    channel.basicPublish("E2",       "B", null,  msgDirectB.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + msgDirectB + "'");
    
    channel.close();
    connection.close();
    scan.close();
  }
}

//mvn compile assembly:single