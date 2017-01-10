package fr.usmb.isc.m2.test;

import java.io.*;
import javax.net.ssl.*;


class SecureClient {
  public SecureClient() {
    try {
    	
    	
    	System.setProperty( "javax.net.ssl.trustStore", System.getProperty( "user.dir" ) + "/resources/security/monCertificat" );
		System.setProperty( "javax.net.ssl.trustStorePassword", "azerty" );
		
		
      SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket socket = (SSLSocket) socketFactory.createSocket("127.0.0.1", 8080);
      PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      String caMarche =  "ca marche!";
      output.println(caMarche);
      output.flush();
      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String response = input.readLine();
      System.out.println(response);
      output.close();
      input.close();
      socket.close();
      
    } 
   
    catch (IOException ioException) {
      System.out.println("SecureClient IOException");
      ioException.printStackTrace();
    } 
    finally {
      System.exit(0);
    }
    
  }

  public static void main(String args[]) {
    new SecureClient();
  }
}