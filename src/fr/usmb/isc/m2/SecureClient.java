package fr.usmb.isc.m2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class SecureClient
{
	public static void main ( String args[] ) throws InvalidNameException, InvalidKeyException, CertificateException,
			NoSuchAlgorithmException, NoSuchProviderException, SignatureException
	{
		new SecureClient();
	}
	
	public SecureClient () throws InvalidNameException, InvalidKeyException, CertificateException, NoSuchAlgorithmException,
			NoSuchProviderException, SignatureException {
		try
		{			
			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) socketFactory.createSocket( "localhost", 8080 );
			X509Certificate cert = (X509Certificate) socket.getSession().getPeerCertificates()[ 0 ];
			
			System.out.println( cert.getSubjectDN().toString() );
			System.out.println( cert.getIssuerDN().toString() );
			
			LdapName ln = new LdapName( cert.getSubjectDN().getName() );
			String cname = "";
			for ( Rdn rdn : ln.getRdns() )
			{
				if ( rdn.getType().equalsIgnoreCase( "CN" ) )
				{
					System.err.println( "CN is: " + rdn.getValue() );
					cname = rdn.getValue().toString();
					break;
				}
			}
			cert.checkValidity();
			
			if ( cert.getSubjectDN().toString() != cname )
			{
				throw new NullPointerException( "Wrong CN" );
			}
			
			cert.verify( cert.getPublicKey() );
			PrintWriter output = new PrintWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			String caMarche = "ca marche!";
			output.println( caMarche );
			output.flush();
			BufferedReader input = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			String response = input.readLine();
			System.out.println( response );
			output.close();
			input.close();
			socket.close();
			
		}
		
		catch ( IOException ioException )
		{
			System.out.println( "SecureClient IOException" );
			ioException.printStackTrace();
		} finally
		{
			System.exit( 0 );
		}
	}
	
}