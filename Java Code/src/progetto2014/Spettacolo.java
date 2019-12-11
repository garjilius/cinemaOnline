package progetto2014;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/** Questa e' la classe che concretizza il concetto Spettacolo
 * contiene tutte le informazioni riguardanti uno spettacolo.
 */
public class Spettacolo implements Serializable, Comparable<Spettacolo>, Cloneable {
	
	private static final long serialVersionUID = 4497435095875696201L;
	private String film;
	private int durata;
	private float prezzo, incasso;
	private Sala sala;
	private GregorianCalendar orario, orariofine, scadenzaprenotazione;
	private SimpleDateFormat printData = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat printorario = new SimpleDateFormat("HH:mm");

	// COSTRUTTORI
	public Spettacolo() {
		// COSTRUTTORI SENZA PARAMETRI
	}
	
	/**
	 *  Metodo per creare una copia dello spettacolo
	 *  @return clone dello spettacolo
	 */	
	@Override
	public Spettacolo clone() {
		try {
		Spettacolo cloned = (Spettacolo) super.clone();
		cloned.film = film;
		cloned.durata = durata;
		cloned.prezzo = prezzo;
		cloned.sala = sala.clone();
		cloned.orario = orario;
		cloned.orariofine = orariofine;
		cloned.scadenzaprenotazione = scadenzaprenotazione;
		return cloned; 
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}

	// METODI PER LA SALA
	/**
	 * Restituisce il numero di posti prenotati nella sala a cui e' assegnato lo spettacolo
	 * @return numero di posti prenotati
	 */
	public int getPrenotatiSala() {
		return sala.getPrenotati();
	}

	/**
	 * Restituisce il nome della sala a cui e' assegnato lo spettacolo
	 * @return numero di sala
	 */
	public int getNumeroSala() {
		return sala.getNumeroSala();
	}

	/**
	 * Imposta una sala per lo spettacolo
	 * @param sala sala assegnata allo spettacolo
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}

	
	/**
	 * Restituisce la sala assegnata allo spettacolo
	 * @return sala assegnata allo spettacolo
	 */
	public Sala getSala() {
		return sala;
	}

	// METODI INFORMAZIONE FILM E SPETTACOLO
	/**
	 * Imposta il film 
	 * @param film nome del film
	 */
	public void setFilm(String film) {
		this.film = film;
	}

	/**
	 * Restituisce il nome del film
	 * @return nome del film
	 */
	public String getFilm() {
		return film;
	}

	/** 
	 * Imposta la durata del film
	 * @param durata durata del film
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}

	/** 
	 * Restituisce la durata del film 
	 * @return durata del film
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * Restituisce il prezzo di base dello spettacolo (al netto degli sconti)
	 * @return prezzo dello spettacolo
	 */
	public float getPrezzo() {
		return prezzo;
	}

	/**
	 * Imposta il prezzo di base dello spettacolo (al netto degli sconti)
	 * @param prezzo prezzo dello spettacolo
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Imposta l'orario di inizio dello spettacolo e calcola quello di fine (Vedi setOrarioFine)
	 * @param orario orario d'inizio dello spettacolo
	 */
	public void setOrario(GregorianCalendar orario) {
		this.orario = orario;
		setScadenzaPrenotazione();
		setOrarioFine();
	}

	/**
	 * Calcola l'orario di fine dello spettacolo
	 */
	public void setOrarioFine() {
		int h = durata / 60;
		int m = (durata - (h * 60));
		orariofine = (GregorianCalendar) orario.clone();
		orariofine.add(GregorianCalendar.HOUR_OF_DAY, h);
		orariofine.add(GregorianCalendar.MINUTE, m);
	}
	
	/**
	 * Calcola l'orario in cui scadra' la possibilita' di prenotare
	 */
	public void setScadenzaPrenotazione() {
		scadenzaprenotazione = (GregorianCalendar) orario.clone();
		scadenzaprenotazione.add(GregorianCalendar.HOUR_OF_DAY, -12);
	}
	
	/**
	 * Restituisce l'orario in cui scadra' la possibilita' di prenotare
	 * @return GregorianCalendar settato alla scadenza della prenotazione
	 */
	public GregorianCalendar getScadenzaPrenotazione() {
		return scadenzaprenotazione;
	}
	/**
	 * Restituisce l'orario di inizio dello spettacolo
	 * @return GregorianCalendar settato all'inizio dello spettacolo
	 */
	public GregorianCalendar getOrario() {
		return orario;
	}
	
	/**
	 * Restituisce l'orario di fine dello spettacolo
	 * @return GregorianCalendar settato alla fine dello spettacolo
	 */
	public GregorianCalendar getOrarioFine() {
		return orariofine;
	}

	/**
	 * Restituisce la settimana dell'anno in cui e' programmato lo spettacolo
	 * @return settimana di programmazione dello spettacolo
	 */

	public int getWeek() {
		int week1 = orario.get(GregorianCalendar.WEEK_OF_YEAR);
		return week1;
	}
	
	/**
	 * Restituisce l'anno in cui e' programmato lo spettacolo
	 * @return anno in cui è programmato lo spettacolo
	 */
	public int getYear() {
		int year = orario.get(GregorianCalendar.YEAR);
		return year;
	}

	/**
	 * Imposta l'incasso  
	 * @param euro incasso
	 */
	public void setIncasso(float euro) {
		incasso = incasso + euro;
	}
	
	/**
	 * Restituisce l'incasso 
	 * @return incasso dello spettacolo
	 */
	public float getIncasso() {
		return incasso;
	}
/**
 * Override del metodo equals per controllare se due spettacoli sono uguali
 */
	@Override
	public boolean equals (Object otherObject) {
		if (otherObject == null)
			return false;
		if (getClass()!= otherObject.getClass())
			return false;
		Spettacolo other = (Spettacolo) otherObject;
		return film.equals(other.film) && durata==other.durata;
	}
	
	/**
	 * Override del toString per ottenere tutte le informazioni utili sullo spettacolo
	 */
	@Override
	public String toString() {
		return   "SETT: " + getWeek() + "  || FILM: " + film + "  || SALA: "
				+ sala.getNumeroSala() + "  || POSTI LIBERI: "
				+ sala.getDisponibili() + "  || ORARIO: "
				+ printData.format(orario.getTime()) + " - "
				+ printorario.format(orario.getTime()) + " - "
				+ printorario.format(orariofine.getTime());
	}

	/**
	 * override del compareTo per ordinare gli spettacoli per posti disponibili
	 */
	@Override
	public int compareTo(Spettacolo arg0) {
		int posti = sala.getDisponibili();
		int posti2 = arg0.sala.getDisponibili();
		if (posti < posti2)
			return -1;
		else if (posti > posti2)
			return 1;
		else
			return 0;
	}
}
