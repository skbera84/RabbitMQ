/**
 * 
 */
package com.gopivotal.rabbitmq;

import com.gopivotal.rabbitmq.QuotationService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 */
public class QuotationSender {

	private static final QuotationService quotationService = new QuotationService();

	public static void main(String[] args) throws Exception {
		
		// TODO setup the connection with a ConnectionFactory (see slides for examples)
		
		// TODO setup a new channel using the newly created connection
		
		// TODO In the infinite loop while(true) add the following: 
		// 1) wait for some period of time, call letsWait() 
		// 2) get a quotation from the service by calling next()
		// 3) Send a message to the quotations exchange with routing key "nasq" by calling basicPublish() on the channel
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		
		Channel channel = connection.createChannel();
		
		while(true) {
			letsWait();
			String quotation = quotationService.next();
			channel.basicPublish("quotations", "nasq", null, quotation.getBytes());
		}
	}

	private static void letsWait() throws Exception {
		Thread.sleep(1000);
	}

}
