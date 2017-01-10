package fr.usmb.isc.m2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.Socket;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClientSSL {
  public static void main(String args[]) throws Exception {
    System.setProperty("javax.net.ssl.trustStore", "clienttrust");
    

    SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket s = ssf.createSocket("127.0.0.1", 8080);

    SSLSession session = ((SSLSocket) s).getSession();
    Certificate[] cchain = session.getPeerCertificates();
    System.out.println("The Certificates used by peer");
    for (int i = 0; i < cchain.length; i++) {
      System.out.println(((X509Certificate) cchain[i]).getSubjectDN());
    }
    System.out.println("Peer host is " + session.getPeerHost());
    System.out.println("Cipher is " + session.getCipherSuite());
    System.out.println("Protocol is " + session.getProtocol());
    System.out.println("ID is " + new BigInteger(session.getId()));
    System.out.println("Session created in " + session.getCreationTime());
    System.out.println("Session accessed in " + session.getLastAccessedTime());

    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    String x = in.readLine();
    System.out.println(x);
    in.close();

  }
}