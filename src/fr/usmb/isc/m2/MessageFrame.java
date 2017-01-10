package fr.usmb.isc.m2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdou ABDELMOUMNI(abdou.abdelmoumni@etu.univ-smb.fr)
 * @author Rémi REBILLARD (remi.rebillard@etu.univ-smb.fr)
 * @author Fanny RIBARD (fanny.ribard@etu.univ-smb.fr)
 * @author Loïc ROBERGEON (loic.robergeon@etu.univ-smb.fr)
 */
public class MessageFrame extends JFrame
{
	
	private static final long		serialVersionUID	= 4577266928824451092L;
	
	private final JPanel			contentPane;
	
	private final ConnexionClient	connexion;

	/**
	 * Create the frame.
	 */
	public MessageFrame ( ConnexionClient connexion ) {
		this.setTitle( "Sending message" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setBounds( 100, 100, 450, 300 );
		this.contentPane = new JPanel();
		this.contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		this.contentPane.setLayout( new BorderLayout( 0, 0 ) );
		this.setContentPane( this.contentPane );
		
		this.connexion = connexion;
		
		JPanel panelText = new JPanel();
		this.contentPane.add( panelText, BorderLayout.CENTER );
		
		JTextArea textAreaSend = new JTextArea();
		textAreaSend.setRows( 10 );
		textAreaSend.setColumns( 30 );
		panelText.add( textAreaSend );
		
		JPanel panelSend = new JPanel();
		this.contentPane.add( panelSend, BorderLayout.SOUTH );
		
		JButton buttonSend = new JButton( "Send message" );
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed ( ActionEvent arg0 )
			{
				String response = MessageFrame.this.connexion.serverCalculation( textAreaSend.getText() );
				System.out.println( "Response : " + response );
			}
		} );
		panelSend.add( buttonSend );
	}

}
