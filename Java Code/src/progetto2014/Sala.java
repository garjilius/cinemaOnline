package progetto2014;

import java.io.Serializable;
import java.util.ArrayList;

/** Questa classe implementa il concetto di SALA CINEMATOGRAFICA.
 * Ogni oggetto SALA contiene:
 * - la CAPIENZA della sala, ossia il numero di posti fisici
 * - il n di posti INDISPONIBILI ossia quelli rotti
 * - il n di posti DISPONIBILI
 * - il n di posti PRENOTATI
 * - il n di posti ACQUISTATI
 * - una stringa col NOME della sala
 * - un'arraylist di oggetti POSTI, che implementano il concetto di POSTO fisico
 * La funzione CLONASALA serve per creare un clone della sala del cinema da passare allo spettacolo
 */
public class Sala implements Serializable, Cloneable, Comparable<Sala> {
	private static final long serialVersionUID = 6561271490655424591L;
	private int numeroSala;
	private int capienza, disponibili, guasti, prenotati, acquistati;
	private ArrayList<Posto> posti = new ArrayList<Posto>();

	// COSTRUTTORI
	/**
	 * Costruttore della sala
	 */
	public Sala() {

	}
	
	/**
	 * Costruttore della sala 
	 * @param nposti posti da prenotare
	 * @param numero numero di sala
	 */
	public Sala(int nposti, int numero) {
			creaPostiSala(nposti);
			capienza = nposti;
			disponibili = nposti;
			numeroSala = numero;
	}

	/**
	 *  Metodo per creare una copia della sala
	 *  @return clone della sala (prenotati e acquistati azzerati)
	 */	

	@Override
	public Sala clone() {
		try {
		Sala cloned = (Sala) super.clone();
		cloned.numeroSala = numeroSala;
		cloned.capienza = capienza;
		cloned.guasti = guasti;
		cloned.disponibili = cloned.capienza-cloned.guasti;
		cloned.prenotati = 0;
		cloned.acquistati = 0;
		cloned.posti = new ArrayList<>();
		for (Posto current : posti) {
			cloned.posti.add(current.clone());
		}
		return cloned; 
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}

	
	/**
	 *  Aggiunge alla sala i posti necessari
	 *  @param nposti posti da aggiungere alla sala
	 */
	public void creaPostiSala(int nposti) {
		for (int i = 0; i < nposti; i++) {
			posti.add(new Posto());
		}
	}



	/**
	 *  Restituisce il nome della sala
	 *  @return numero di sala
	 */
	public int getNumeroSala() {
		return numeroSala;
	}

	/**
	 *  Imposta un nome alla sala
	 *  @param numero numero di sala
	 */
	public void setNumeroSala(int numero) {
		this.numeroSala = numero;
	}

	/**
	 *  Restituisce la capienza della sala
	 *  @return capienza della sala
	 */
	public int getCapienza() {
		return capienza;
	}

	/**
	 *  Imposta una capienza alla sala
	 *  @param capienza capienza della sala
	 */
	public void setCapienza(int capienza) {
		this.capienza = capienza;
		disponibili  = capienza - guasti;
	}

	/**
	 *  Restituisce un arraylist di posti
	 *  @return arraylist di posti
	 */
	public ArrayList<Posto> getPosti() {
		return posti;
	}

	/**
	 *  Restituisce il numero di posti disponibili
	 *  @return numero di posti disponibili
	 */
	public int getDisponibili() {
		return disponibili;
	}

	/**
	 *  Imposta il numero di posti disponibili
	 *  @param disponibili numero di posti disponibili
	 */
	public void setDisponibili(int disponibili) {
		this.disponibili = disponibili;
	}

	/**
	 *  Restituisce il numero di posti prenotati
	 *  @return numero di posti prenotati
	 */
	public int getPrenotati() {
		return prenotati;
	}

	/**
	 *  Restituisce il numero di posti acquistati
	 *  @return numero di posti acquistati
	 */
	public int getAcquistati() {
		return acquistati;
	}
	

	/**
	 *  Restituisce il numero di posti guasti
	 *  @return numero di posti guasti
	 */
	public int getGuasti() {
		return guasti;
	}

	/**
	 *  Guasta n posti 
	 *  @param n numero di posti
	 */
	public void setGuasti(int n) {
		for (int k = 0; k<n; k++)
			guasta();
	}
	
	// METODI FUNZIONI SALA
	
	/**
	 *  Ripara un posto precedentemente guasto
	 */
	public void ripara() {
		disponibili++;
		guasti--;
	}

	/**
	 *  Guasta un posto precedentemente integro
	 */
	public void guasta() {
		disponibili--;
		guasti++;
	}

	/**
	 *  Prenota un posto
	 */
	public void prenota() {
		prenotati++;
		disponibili--;
	}
	
	/**
	 *  Annulla una prenotazione
	 */
	public void rimuoviPrenotazione() {
		prenotati--;
		disponibili++;
	}
	
	/**
	 *  Acquista un posto
	 */
	public void acquista() {
		acquistati++;
		disponibili--;
	}
	
	/**
	 *  Annulla un acquisto
	 */
	public void rimuoviAcquisto() {
		acquistati--;
		disponibili++;
	}
	/**
	 * override del compareTo per ordinare le sale per numero di sala
	 * @param arg0 sala con cui confrontare
	 */
	@Override
	public int compareTo(Sala arg0) {
		int numeroSala1 = getNumeroSala();
		int numeroSala2 = arg0.getNumeroSala();
		if (numeroSala1 < numeroSala2)
			return -1;
		else if (numeroSala1 > numeroSala2)
			return 1;
		else
			return 0;
	}
	
	/**
	 * Override di Equals per controllare se due sale sono uguali
	 * @param otherObject sala con cui effettuare il confronto
	 * @return true o false
	 */
	@Override
	public boolean equals (Object otherObject) {
		if (otherObject == null)
			return false;
		if (getClass()!= otherObject.getClass())
			return false;
		Sala other = (Sala) otherObject;
		return numeroSala == (other.numeroSala) && capienza == (other.capienza);
	}

	/**
	 *  Sovrascrive il toString di default per ottenere alcune informazioni (nome, posti totali, posti liberi, posti guasti)
	 */
	@Override
	public String toString() {
		return "NOME: Sala " + numeroSala + " POSTI: " + capienza + " LIBERI: "
				+ disponibili + " GUASTI: " + guasti;
	}
}
