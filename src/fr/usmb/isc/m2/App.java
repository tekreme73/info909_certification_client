/**
 *
 */
package fr.usmb.isc.m2;

import java.awt.EventQueue;
import java.net.InetAddress;

/**
 * Classe principale de l'application client (03/01/2017)
 *
 * @author Abdou ABDELMOUMNI(abdou.abdelmoumni@etu.univ-smb.fr)
 * @author Rémi REBILLARD (remi.rebillard@etu.univ-smb.fr)
 * @author Fanny RIBARD (fanny.ribard@etu.univ-smb.fr)
 * @author Loïc ROBERGEON (loic.robergeon@etu.univ-smb.fr)
 */
public class App
{
	
	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		
		EventQueue.invokeLater( new Runnable() {
			@Override
			public void run ()
			{
				try
				{
					ConnexionClient client = new ConnexionClient( InetAddress.getLocalHost(), 8080 );
					MessageFrame frame = new MessageFrame( client );
					frame.setVisible( true );
				} catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		} );

	}

}
