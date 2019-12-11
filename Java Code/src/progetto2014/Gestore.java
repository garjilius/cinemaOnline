package progetto2014;

import java.util.Collections;

import grafica.SchermataIniziale;

/** La classe Gestore.java e' la classe che contiene le funzioni di gestione del cinema.
 * Per rendere il codice più pulito e di facile consultazione,
 * la gestione delle SALE, degli SPETTACOLI e degli SCONTI e' stata implementata in altre classi.
 * L'unica gestione presente in questa classe e' quella dell'INCASSO SETTIMANALE. 
 */

public class Gestore  {
	
	/**
	 * Aggiunge a spettSearch tutti gli spettacoli del film passato da parametro
	 * @param film film da aggiungere a SpettSearch
	 * 
	 */
	public void getSpettacoloByName(String film) {
		SpettacoliManager.spettSearch.clear();
		for (Spettacolo current : Utils.spettacoli) {
			if (current.getFilm().equalsIgnoreCase(film))
				SpettacoliManager.spettSearch.add(current);
		}
	}

	/**
	 * Restituisce l'incasso della settimana relativo al film passato da parametro
	 * @param film film per cui voglio ottenere l'incasso
	 * @return incasso della settimana relativo al film
	 */
	public double getIncassoFilm(String film) {
		double incasso = 0;
		getSpettacoloByName(film);
		for (Spettacolo current : SpettacoliManager.spettSearch) {
			if (current.getWeek() == SchermataIniziale.getN_week() && current.getYear() == SchermataIniziale.getYear())
				incasso += current.getIncasso();
		}
		return incasso;	
	}
	
	/**
	 * Restituisce l'incasso totale della settimana
	 * @return incasso della settimana
	 */
	public double getIncassoTotale() {
		double incasso = 0;
		Collections.sort(Utils.spettacoli);
		for (Spettacolo current : Utils.spettacoli) {
			if (current.getWeek() == SchermataIniziale.getN_week() && current.getYear() == SchermataIniziale.getYear())
				incasso += current.getIncasso();	
		}
		return incasso;
	}
}