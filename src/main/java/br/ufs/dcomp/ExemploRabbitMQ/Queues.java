package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Queues {

  private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("44.206.104.42"); // Alterar
    factory.setUsername("jcadmin"); // Alterar
    factory.setPassword("jcpass"); // Alterar
    factory.setVirtualHost("/");    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    for(int i=0; i<10; i++){
      channel.queueDeclare(QUEUE_NAME+i, false,   false,     false,       null);
    }

    channel.close();
    connection.close();
  }
}

//mvn compile assembly:single