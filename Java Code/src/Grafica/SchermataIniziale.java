package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import progetto2014.Utils;

/** La classe SchermataIniziale.java e' la classe di partenza del software,
 * ed infatti e' l'unica a contenere il MAIN.
 * Rappresenta la prima schermata del programma 
 * e permette di indicare la modalita' in cui si vuole operare(Gestore o Utente),
 *  visualizza la data e l'ora esatta in cui si avvia il programma,
 * oltre al numero della settimana corrente, che viene salvato per successivi confronti sulle date.
 */
public class SchermataIniziale {
	private static int n_week;
	private static int year;

	
	public static void main(String[] args) {
		// CARICHIAMO TUTTI I DATI
		Utils.caricaDati();
		
		// SETTAGGI FRAME
		JFrame frame = new JFrame();
		frame.setSize(250, 125);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

		frame.setTitle("Benvenuti al Cinema");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 1));

		// ELEMENTI GRAFICI
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 2));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		JLabel label = new JLabel("Sei il gestore o un utente?");
		JButton button1 = new JButton("Gestore");
		JButton button2 = new JButton("Utente");

		// ACTIONLISTENER
		button1.addActionListener(new GestoreGrafica());
		button2.addActionListener(new UtenteGrafica());

		// VISUALIZZARE DATA E ORARIO
	    GregorianCalendar data = new GregorianCalendar();
		Date now = data.getTime();
		n_week = (data.get(GregorianCalendar.WEEK_OF_YEAR));
		year = (data.get(GregorianCalendar.YEAR));
		SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		JLabel label1 = new JLabel(dataformat.format(now));
		JLabel label2 = new JLabel("Numero settimana: " + getN_week());
		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);

		// AGGIUNTE ELEMENTI GRAFICI
		panel.add(label);
		panel1.add(button1);
		panel1.add(button2);
		panel2.add(label1);
		panel2.add(label2);
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Restituisce l'anno in cui è stato avviato il programma
	 * @return anno 
	 */
	public static int getYear() {
		return year;
	}
	
	/**
	 * Restituisce la settimana dell'anno in cui è stato avviato il programma
	 * @return settimana dell'anno
	 */
	public static int getN_week() {
		return n_week;
	}

}
