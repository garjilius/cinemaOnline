package progetto2014;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
* Questa classe racchiude cio' che serve per gestire gli spettacoli
*/
public class SpettacoliManager {
	
	public int index;
	private JList<String> listSpett = new JList<String>();
	public JList<String> listSale = new JList<String>();
	public JScrollPane paneSpett = new JScrollPane(listSpett);
	private DefaultListModel<String> spettacoliString = new DefaultListModel<String>();
	private DefaultListModel<String> saleString = new DefaultListModel<String>();

	// Questo array conserva gli spettacoli di un determinato film desiderato
	 static ArrayList<Spettacolo> spettSearch = new ArrayList<Spettacolo>();


	/**
	 * Aggiorna la lista delle sale (contiene i toString)
	 */
	public  void editListSale() {
		saleString.clear();
		for (Sala current : Utils.sale) {
			saleString.addElement(current.toString());
		}
		listSale.setModel(saleString);
	}
	
	/**
	 * Aggiorna la lista degli spettacoli (contiene i toString)
	 */
	public  void editList() {
		spettacoliString.clear();
		for (Spettacolo current : Utils.spettacoli) {
			spettacoliString.addElement(current.toString());
		}
		listSpett.setModel(spettacoliString);
	}

	// AGGIUNGI SPETTACOLO
	/**
	 * Permette di aggiungere uno spettacolo
	 * @param film film da aggiungere
	 * @param durata durata del film
	 * @param prezzo prezzo dello spettacolo
	 * @param data data d'inizio dello spettacolo
	 */
	public  void aggiungiSpettacolo(String film, int durata, float prezzo, GregorianCalendar data) {
		Spettacolo spettacolo = new Spettacolo();
		index = listSale.getSelectedIndex();
		spettacolo.setDurata(durata);
		spettacolo.setOrario(data);

		for (Spettacolo current : Utils.spettacoli ) {
			if ((current.getSala().getNumeroSala()) == (Utils.sale.get(index).getNumeroSala())) {
				if ((data.before(current.getOrarioFine()) && data.after(current.getOrario())) || (data.equals(current.getOrario())) ||
						(spettacolo.getOrarioFine().after(current.getOrario()) && (spettacolo.getOrarioFine().before(current.getOrarioFine())))) {
					try {
						throw new SalaIndisponibileException();
					} catch (SalaIndisponibileException e1) {
						SimpleDateFormat Data = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
						SimpleDateFormat OraFine = new SimpleDateFormat("HH:mm");
						JOptionPane.showMessageDialog(null, "Una delle fasce orarie richieste non e' disponibile: "
								+  Data.format(current.getOrario().getTime()) + " - " + OraFine.format(current.getOrarioFine().getTime()));		
						return;
					}
				}
			}
		}
		spettacolo.setFilm(film);
		spettacolo.setPrezzo(prezzo);		
		spettacolo.setSala(Utils.sale.get(index).clone());
		Utils.spettacoli.add(spettacolo);
		SalaManager.aggiornaSale();
		Utils.salvaDati();
		Collections.sort(Utils.spettacoli);
		editList();
	}

	// CANCELLA SPETTACOL0
	/**
	 * Cancella uno spettacolo
	 */
	public  void cancSpett() {
			index = listSpett.getSelectedIndex();
			Utils.spettacoli.remove(index);
			JOptionPane.showMessageDialog(null, "Spettacolo eliminato!");
			Utils.salvaDati();
			editList();	
	}
}
