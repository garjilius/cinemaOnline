package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import progetto2014.InvalidNumberException;
import progetto2014.SpettacoliManager;
import progetto2014.Utils;

/**
* Questa classe racchiude cio' che serve per gestire gli spettacoli
*/
public class SpettacoloGrafica implements ActionListener {
	SpettacoliManager spettMgr;
	private JScrollPane paneSale;
	JTextField film;
	JTextField durata;
	JTextField gi;
	JTextField mi;
	JTextField ai;
	JTextField giorniprogText;
	JTextField h;
	JTextField mm;
	JTextField prezzo;

	public void actionPerformed(ActionEvent event) {
		spettMgr = new SpettacoliManager();
		paneSale=  new JScrollPane(spettMgr.listSale);
		Collections.sort(Utils.spettacoli);
		spettMgr.editList();
		JFrame frame = new JFrame("GESTIONE SPETTACOLI");
		frame.setSize(780, 150);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

		frame.setLayout(new GridLayout(0, 1));

		JButton addSpett = new JButton("Aggiungi");
		JButton cancSpett = new JButton("Elimina");
		addSpett.addActionListener(new AddSpett());
		cancSpett.addActionListener(new CancSpett());

		frame.add(spettMgr.paneSpett);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));
		panel.add(addSpett);
		panel.add(cancSpett);
		frame.add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	// AGGIUNGI SPETTACOLO
	/**
	 * Permette di raccogliere tutte le informazioni necessarie ad aggiungere uno spettacolo
	 */
	class AddSpett implements ActionListener {
		JFrame addSpettFrame;
		public void actionPerformed(ActionEvent event) {
			
			spettMgr.editListSale();
			addSpettFrame = new JFrame("Informazioni spettacolo");
			addSpettFrame.setLayout(new GridLayout(0, 1));
			addSpettFrame.setSize(400, 300);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			addSpettFrame.setLocation ( ( screenSize.width / 2 ) - ( addSpettFrame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( addSpettFrame.getHeight ( ) / 2 ) );


			// ELEMENTI FRAME
			JLabel label = new JLabel("Film");
			film = new JTextField(25);
			JLabel label1 = new JLabel("Durata");
			durata = new JTextField(3);
			JLabel label3 = new JLabel("Data Inizio(GG/MM/AAAA)");
			gi = new JTextField(2);
			JLabel label4 = new JLabel("/");
			mi = new JTextField(2);
			JLabel label5 = new JLabel("/");
			ai = new JTextField(4);
			JLabel giorniprog = new JLabel("Giorni di programmazione");
			giorniprogText = new JTextField(2);
			JLabel labelora = new JLabel("Ora(HH:MM)");
			h = new JTextField(2);
			JLabel label7 = new JLabel(":");
			mm = new JTextField(2);
			JLabel labelprezzo = new JLabel("Prezzo");
			prezzo = new JTextField(5);
			JButton aggiungi = new JButton("Aggiungi");

			// AGGIUNTA ELEMENTI GRAFICI
			JPanel panel = new JPanel();
			panel.add(label);
			panel.add(film);
			JPanel panel1 = new JPanel();
			panel1.add(label1);
			panel1.add(durata);
			JPanel panelinizio = new JPanel();
			panelinizio.add(label3);
			panelinizio.add(gi);
			panelinizio.add(label4);
			panelinizio.add(mi);
			panelinizio.add(label5);
			panelinizio.add(ai);
			JPanel setProg = new JPanel();
			setProg.add(giorniprog);
			setProg.add(giorniprogText);
			JPanel panel4 = new JPanel();
			panel4.add(labelora);
			panel4.add(h);
			panel4.add(label7);
			panel4.add(mm);
			JPanel panel5 = new JPanel();
			panel5.add(labelprezzo);
			panel5.add(prezzo);
			JPanel panel6 = new JPanel();
			panel6.add(aggiungi);
			addSpettFrame.add(panel);
			addSpettFrame.add(panel1);
			addSpettFrame.add(panelinizio);
			addSpettFrame.add(setProg);
			addSpettFrame.add(panel4);
			addSpettFrame.add(panel5);
			addSpettFrame.add(paneSale);
			addSpettFrame.add(panel6);
			addSpettFrame.setVisible(true);
			addSpettFrame.setResizable(false);

			aggiungi.addActionListener(new AddSpett2());
			
		
	}
		
		
		class AddSpett2 implements ActionListener{
			int contatore, annoInizio, meseInizio, giornoInizio, oraInizio, minutoInizio,lunghezza;
			float euro;
			String movie;
			public void actionPerformed(ActionEvent e) {
				try {
					 contatore = Integer.parseInt(giorniprogText.getText());
					 annoInizio = Integer.parseInt(ai.getText());
					 meseInizio = Integer.parseInt(mi.getText());
					 giornoInizio = Integer.parseInt(gi.getText());
					 oraInizio = Integer.parseInt(h.getText());
					 minutoInizio = Integer.parseInt(mm.getText());
					 euro = Float.parseFloat(prezzo.getText());
					 lunghezza = Integer.parseInt(durata.getText());
					 movie = film.getText();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Attenzione a compilare i campi numerici con valori corretti");
					return;
				}
				if (contatore < 1 || euro <0 || lunghezza <1 || annoInizio <0 || giornoInizio <0 || oraInizio <0 || minutoInizio <0) {
					try {
						throw new InvalidNumberException();
					} catch (InvalidNumberException e1) {
						JOptionPane.showMessageDialog(null, "Assicurarsi che tutti i campi numerici siano compilati con valori positivi");
						return;
					}
				}
				
				GregorianCalendar data;
				for (int i = 0; i < contatore; i++) {
					data = new GregorianCalendar(annoInizio, meseInizio, giornoInizio, oraInizio, minutoInizio);
					
					data.add((GregorianCalendar.MONTH), -1);
					data.add((GregorianCalendar.DAY_OF_YEAR), i);
					spettMgr.aggiungiSpettacolo(movie, lunghezza, euro, data);			
				}	
				
				addSpettFrame.setVisible(false);
				
			}	
		}
}
		
	// CANCELLA SPETTACOL0
	/**
	 * Cancella uno spettacolo
	 */
	class CancSpett implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			spettMgr.cancSpett();
		}
	}
}
