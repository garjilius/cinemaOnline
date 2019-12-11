package progetto2014;

import grafica.SchermataIniziale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *  Classe che avvia il lato utente del programma
 *
 */
public class Utente {

	public static JList<String> listSpett = new JList<String>();
	static DefaultListModel<String> spettacoliString = new DefaultListModel<String>();
	
	public static JList<String> listSale = new JList<String>();
	private static DefaultListModel<String> saleString = new DefaultListModel<String>();
	
	/**
	 * Aggiorna la lista degli spettacoli della settimana
	 */
	public void editListSettimana() {
		spettacoliString.clear();
		Collections.sort(Utils.spettacoli, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
		for (Spettacolo current : Utils.spettacoli) {
			if (current.getWeek() == SchermataIniziale.getN_week() && current.getYear() == SchermataIniziale.getYear())
			spettacoliString.addElement(current.toString());
		}
		listSpett.setModel(spettacoliString);
	}
	
	/**
	 * Aggiorna la lista degli spettacoli fruibili
	 */
	public void editListFruibili() {
		spettacoliString.clear();
		Collections.sort(Utils.spettacoli, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
		for (Spettacolo current : Utils.spettacoliFruibili) {
			spettacoliString.addElement(current.toString());
		}
		listSpett.setModel(spettacoliString);
	}

	/**
	 * Aggiorna la lista degli spettacoli aggiungendo solo quelli della sala richiesta
	 * @param input nome dello spettacolo 
	 */
	public void editListInserito(int input) {
		spettacoliString.clear();
		Collections.sort(Utils.spettacoli, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
		for (Spettacolo current : Utils.spettacoli)
			if (current.getSala().getNumeroSala () == input && current.getWeek() == SchermataIniziale.getN_week()) {
				spettacoliString.addElement(current.toString());
			}
		listSpett.setModel(spettacoliString);
	}
	
	/**
	 * Aggiorna la lista delle sale
	 */
	public void editListSale() {
		saleString.clear();
		for (Sala current : Utils.sale) {
			saleString.addElement(current.toString());
		}
		listSale.setModel(saleString);
	}

		
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine cronologico
	 */
	class OrdineCronologicoFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
			editListFruibili();
		    }
	}
	
	
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine di numero di sala crescente
	 */
	class OrdineSalaCrescenteFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerSalaCrescente());		
			editListFruibili();
			}
	}
	
	/**
	 * Ordina l'arraylist di spettacoli fruibili in ordine alfabetico
	 */
	class OrdineAlfabeticoFruibili implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerTitolo());
			editListFruibili();
			}
	}


	
}