/**
 *
 */
package fr.usmb.isc.m2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

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
			SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
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
