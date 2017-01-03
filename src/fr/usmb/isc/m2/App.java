/**
 * 
 */
package fr.usmb.isc.m2;

import java.awt.EventQueue;

/**
 * Classe principale de l'application client (03/01/2017)
 * 
 * @author Abdou ABDELMOUMNI(abdou.abdelmoumni@etu.univ-smb.fr)
 * @author Rémi REBILLARD (remi.rebillard@etu.univ-smb.fr)
 * @author Fanny RIBARD (fanny.ribard@etu.univ-smb.fr)
 * @author Loïc ROBERGEON (loic.robergeon@etu.univ-smb.fr)
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageFrame frame = new MessageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

}
