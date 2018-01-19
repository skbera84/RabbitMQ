/**
 * 
 */
package com.gopivotal.rabbitmq;

import java.io.*;
import java.security.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.rabbitmq.client.*;

/**
 * 
 *
 */
public class SslClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        // TODO use the appropriate port
        factory.setPort(5672);

        // TODO activate SSL at the factory level
        
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare("ssl-client-exclusive-queue", false, true, true, null);
        channel.basicPublish("", "ssl-client-exclusive-queue", null, "Hello, TLS World!".getBytes());

        GetResponse chResponse = channel.basicGet("ssl-client-exclusive-queue", false);
        if(chResponse == null) {
            System.out.println("No message retrieved");
        } else {
            byte[] body = chResponse.getBody();
            System.out.println("Received: " + new String(body));
        }
        
        channel.close();
        conn.close();

	}

	/** code for server authentication
	 
	char[] trustPassphrase = "TODO trust store password here".toCharArray();
    KeyStore tks = KeyStore.getInstance("JKS");
    tks.load(new FileInputStream("TODO trust store file here"), trustPassphrase);

    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
    tmf.init(tks);

    SSLContext c = SSLContext.getInstance("TLSv1.1");
    c.init(null, tmf.getTrustManagers(), null);
	 
	 */
	
}
