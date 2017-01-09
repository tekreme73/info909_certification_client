/**
 *
 */
package fr.usmb.isc.m2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author Abdou ABDELMOUMNI(abdou.abdelmoumni@etu.univ-smb.fr)
 * @author Rémi REBILLARD (remi.rebillard@etu.univ-smb.fr)
 * @author Fanny RIBARD (fanny.ribard@etu.univ-smb.fr)
 * @author Loïc ROBERGEON (loic.robergeon@etu.univ-smb.fr)
 */
public class ConnexionClient
{
	private Socket			client;

	private InputStream		is;

	private BufferedReader	in;

	private OutputStream	os;

	private PrintWriter		out;

	public ConnexionClient ( InetAddress address, int port ) {
		try
		{
			Security.setProperty("ocsp.enabled", "true");
			
			/* instancier le KeyStore */
			KeyStore ks = KeyStore.getInstance( "JKS" );
			ks.load( new FileInputStream( System.getProperty( "user.dir" ) + "/resources/security/clientkeystore" ), "azerty".toCharArray() );

			PKIXBuilderParameters params = new PKIXBuilderParameters( ks, null );
			/* désactivation la révocation */
			params.setRevocationEnabled( false );

			TrustManagerFactory tmf = TrustManagerFactory.getInstance( "PKIX" );
			tmf.init( new CertPathTrustManagerParameters( params ) );
			
			/* créer un SSLContext utilisant cette TrustManagerFactory */
			SSLContext ctx = SSLContext.getInstance( "TLS" );
			/* ici on configure le contexte SSL */
			ctx.init( null, tmf.getTrustManagers(), null );
			
			SSLSocketFactory sslSocketFactory = ctx.getSocketFactory();
			
			SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket( address, port );
			
			this.setClient( sslSocket );
			
			this.setIs( this.getClient().getInputStream() );
			InputStreamReader isr = new InputStreamReader( this.getIs() );
			this.setIn( new BufferedReader( isr ) );

			this.setOut( new PrintWriter( this.getClient().getOutputStream() ) );
		} catch ( UnknownHostException e )
		{
			System.err.println( "Connexion impossible sur adresse serveur local" );
		} catch ( IOException e )
		{
			System.err.println( "I/O invalides pour la connexion" );
		} catch ( KeyStoreException e )
		{
			System.err.println( "KeyStoreException" );
		} catch ( NoSuchAlgorithmException e )
		{
			System.err.println( "NoSuchAlgorithmException" );
		} catch ( CertificateException e )
		{
			System.err.println( "CertificateException" );
		} catch ( KeyManagementException e )
		{
			System.err.println( "KeyManagementException" );
		} catch ( InvalidAlgorithmParameterException e )
		{
			System.err.println( "InvalidAlgorithmParameterException" );
		}
	}

	public ConnexionClient ( String ip, int port ) throws UnknownHostException {
		this( InetAddress.getByName( ip ), port );
	}

	public Socket getClient ()
	{
		return this.client;
	}

	public BufferedReader getIn ()
	{
		return this.in;
	}

	public InputStream getIs ()
	{
		return this.is;
	}

	public OutputStream getOs ()
	{
		return this.os;
	}

	public PrintWriter getOut ()
	{
		return this.out;
	}
	
	public String serverCalculation ( String text )
	{
		String response = "";
		try
		{
			this.getOut().println( text );
			this.getOut().flush();

			while ( response.isEmpty() )
			{
				response = this.getIn().readLine();
			}
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
		return response;
	}

	public void setClient ( Socket client )
	{
		this.client = client;
	}

	public void setIn ( BufferedReader in )
	{
		this.in = in;
	}
	
	public void setIs ( InputStream is )
	{
		this.is = is;
	}
	
	public void setOs ( OutputStream os )
	{
		this.os = os;
	}

	public void setOut ( PrintWriter out )
	{
		this.out = out;
	}
}
