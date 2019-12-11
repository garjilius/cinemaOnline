package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import progetto2014.SpettacoliComparator;
import progetto2014.Utente;
import progetto2014.Utils;

/**
 *  Classe che avvia il lato utente del programma
 *
 */
public class UtenteGrafica implements ActionListener {
	
	private Utente user;
	private int index;
	private JScrollPane paneSpett = new JScrollPane(Utente.listSpett);
	private JScrollPane paneSale = new JScrollPane(Utente.listSale);
	
	/**
	 * Aggiorna la lista degli spettacoli della settimana
	 */

	public void actionPerformed(ActionEvent event) {
		
		user = new Utente();
		// FRAME E I SUOI SETTAGGI
		JFrame frame = new JFrame("MODALITA' CLIENTE");
		frame.setSize(250, 200);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

		frame.setLayout(new GridLayout(0, 1));

		// GRAFICA
		JButton viewProg = new JButton("Programma Settimanale");
		JButton viewSpett = new JButton("Spettacoli Fruibili");

		// ACTIONLISTENER
		viewProg.addActionListener(new Programmazione());
		viewSpett.addActionListener(new ViewSpett());

		// AGGIUNTA ELEMENTI GRAFICI
		frame.add(viewProg);
		frame.add(viewSpett);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Permette di  visualizzare la programmazione (per sala o generale)
	 */
	class Programmazione implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			user.editListSale();
			// FRAME E SETTAGGI
			JFrame frame = new JFrame("PROGRAMMAZIONE");
			frame.setSize(500, 200);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

			frame.setLayout(new GridLayout(0, 1));

			JButton perSala = new JButton("Sala");
			JButton totale = new JButton("Tutte le sale");
			perSala.addActionListener(new PerSala());
			totale.addActionListener(new ViewSpettSettimana());

			frame.add(paneSale);
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0));
			panel.add(perSala);
			panel.add(totale);
			frame.add(panel);
			frame.setResizable(false);
			frame.setVisible(true);
		}
	}

	/**
	 * Permette di visualizzare la programmazione per sala
	 */
	class PerSala implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			index = Utente.listSale.getSelectedIndex();
			user.editListInserito(Utils.sale.get(index).getNumeroSala());
			JFrame frame = new JFrame("Programmazione Sala "+ Utils.sale.get(index).getNumeroSala());
			frame.setSize(700, 150);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

			frame.add(paneSpett);
			frame.setVisible(true);
			frame.setResizable(false);
		}
	}
	/**
	 * Permette di visualizzare la programmazione della settimana
	 */
		class ViewSpettSettimana implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				user.editListSettimana();
				JFrame frame = new JFrame("Spettacoli della settimana");
				frame.setSize(780, 200);
				Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
				frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
				screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
				
				frame.setLayout(new GridLayout(0, 1));
				frame.add(paneSpett);
				frame.setVisible(true);
				frame.setResizable(false);
			}
		}
	
	/**
	 * Permette di visualizzare la programmazione generale
	 */
	class ViewSpett implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Utils.getSpettacoliFruibili();
			user.editListFruibili();
			JFrame frame = new JFrame("Spettacoli Fruibili");
			frame.setSize(780, 250);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
			frame.setLayout(new GridLayout(0, 1));

			JButton openSpett = new JButton("Visualizza sala spettacolo");
			openSpett.addActionListener(new ViewSala());
			ButtonGroup ordinaGroup = new ButtonGroup();
			JPanel ordinePanel = new JPanel();
			ordinePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 00, 0));
			JRadioButton cronologico = new JRadioButton("Ordine Cronologico",true);
			cronologico.addActionListener(new OrdineCronologicoFruibili());
			JRadioButton salaCrescente = new JRadioButton("Numero di Sala Crescente");
			salaCrescente.addActionListener(new OrdineSalaCrescenteFruibili());
			JRadioButton alfabetico = new JRadioButton("Ordine Alfabetico");
			alfabetico.addActionListener(new OrdineAlfabeticoFruibili());
			ordinaGroup.add(cronologico);
			ordinaGroup.add(salaCrescente);
			ordinaGroup.add(alfabetico);
			ordinePanel.add(cronologico);
			ordinePanel.add(salaCrescente);
			ordinePanel.add(alfabetico);

			frame.add(paneSpett);
			frame.add(ordinePanel);
			frame.add(openSpett);
			frame.setVisible(true);
			frame.setResizable(false);
		}
	}
	
	/**
	 * Chiama la classe ViewSala per visualizzare la sala selezionata in modalita' utente
	 */
	class ViewSala implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			new SalaViewer(Utils.spettacoliFruibili.get(Utente.listSpett.getSelectedIndex()).getSala(), "utente");
		}
		
	}
		
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine cronologico
	 */
	class OrdineCronologicoFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
			user.editListFruibili();
		    }
	}
	
	
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine di numero di sala crescente
	 */
	class OrdineSalaCrescenteFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerSalaCrescente());		
			user.editListFruibili();
			}
	}
	
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine alfabetico
	 */
	class OrdineAlfabeticoFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerTitolo());
			user.editListFruibili();
			}
	}
	
}