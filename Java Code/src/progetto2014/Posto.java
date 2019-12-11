package progetto2014;

import java.io.Serializable;

/** La classe POSTO implementa il concetto di POSTO in una sala.
 * Ogni posto contiene:
 * - il NOME ed il COGNOME di chi prenota
 * - variabili booleane per indicare un BAMBINO o un ANZIANO
 * - variabili booleane per indicare GUASTO, PRENOTATO, ACQUISTATO
 */

public class Posto implements Serializable, Cloneable {
	private static final long serialVersionUID = 7133059581732259364L;
	private String nome;
	private String cognome;
	private boolean age, studente;
	private boolean prenotato, acquistato, wasPrenotato;
	private boolean guasto;
	private float prezzo;

	/**
	 * Costruttore
	 */
	public Posto() {
		prenotato = false;
		guasto = false;
		acquistato = false;
	}
	/**
	 *  Metodo per creare una copia del posto
	 *  @return clone del posto
	 */	
	@Override
	public Posto clone() {
		try {
		Posto cloned = (Posto) super.clone();
		cloned.nome = nome;
		cloned.cognome = cognome;
		cloned.age = age;
		cloned.studente = studente;
		cloned.prenotato = prenotato;
		cloned.acquistato = acquistato;
		cloned.guasto = guasto;
		cloned.prezzo = prezzo;
		return cloned; 
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	/**
	 * Override di Equals per controllare se due posti sono uguali
	 * @param otherObject posto con cui confrontare
	 */
	@Override
	public boolean equals (Object otherObject) {
		if (otherObject == null)
			return false;
		if (getClass()!= otherObject.getClass())
			return false;
		Posto other = (Posto) otherObject;
		return nome.equals(other.getNome()) && cognome.equals(other.getCognome()) 
				&& studente == other.isStudente() && age == other.isAge() 
				&& guasto == other.isGuasto();
	}
	
	
	// METODI GUASTO
	
	/**
	 *  Ripara il posto(da guasto a funzionante)
	 */
	public void setRiparato() {
		guasto = false;
	}

	/**
	 *  Imposta il posto a guasto
	 */
	public void setGuasto() {
		guasto = true;
	}

	/**
	 * Controlla se il posto e' guasto
	 * @return stato del posto
	 */
	public boolean isGuasto() {
		return guasto;
	}
	
	public boolean wasPrenotato() {
		return wasPrenotato;
	}
	
	public void setWasPrenotato() {
		wasPrenotato = true;
	}

	
	// METODI PRENOTATO O ACQUISTATO
	
	
	/**
	 *  Prenota il posto
	 */
	public void setPrenotato() {
		if (!guasto)
		prenotato = true;
	}

	/**
	 *  Acquista il posto
	 */
	public void setAcquistato() {
		if (!guasto)
		acquistato = true;
	}

	/**
	 * Libera il posto azzerandone tutti gli attributi
	 */
	public void setLibero() {
		prenotato = false;
		acquistato = false;
		studente = false;
		age = false;
	}

	/**
	 * Controlla se il posto e' prenotato
	 * @return stato di prenotazione del posto
	 */
	public boolean isPrenotato() {
		if (prenotato)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il posto e' acquistato
	 * @return stato di acquisto del posto
	 */
	public boolean isAcquistato() {
		if (acquistato)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il posto e' disponibile
	 * @return stato di disponibilita' del posto
	 */
	public boolean isDisponibile() {
		if (!acquistato && !prenotato)
			return true;
		else
			return false;
	}

	// METODI INFORMAZIONE POSTO
	
	/**
	 *  Restituisce il nome di chi ha prenotato o acquistato
	 *  @return nome di chi ha prenotato
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il nome di chi ha prenotato o acquistato
	 * @param nome nome di chi prenota
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 *  Restituisce il cognome di chi ha prenotato o acquistato
	 *  @return cognome di chi ha prenotato
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Imposta il cognome di chi ha prenotato o acquistato
	 * @param cognome cognome di chi prenota
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Dichiara che chi ha prenotato o acquistato il posto e' uno studente
	 */
	public void setStudente() {
		studente = true;
	}

	/**
	 * Controlla se chi ha prenotato o acquistato e' uno studente
	 * @return stato dello sconto studenti
	 */
	public boolean isStudente() {
		return studente;
	}

	/**
	 * Imposta la fascia d'eta' di chi ha prenotato o acquistato
	 */
	public void setAge() {
		age = true;
	}

	/**
	 * Controlla se chi ha acquistato o prenotato rientra nelle fasce d'eta' agevolate 
	 * @return stato dello sconto eta'
	 */
	public boolean isAge() {
		return age;
	}

	/**
	 * Imposta il prezzo relativo al posto in questione
	 * @param euro prezzo da impostare al posto in questione
	 */
	public void setPrezzo(float euro) {
		prezzo = euro;
	}

	/**
	 * Restituisce il prezzo relativo al posto in questione
	 * @return prezzo del posto
	 */
	public float getPrezzo() {
		return prezzo;
	}

	
	/**
	 * Override del metodo toString per avere maggiori informazioni (nome e cognome di chi prenota o acquista)
	 * @return stringa contenente nome e cognome del titolare del posto
	 */
	@Override
	public String toString() {
		if (nome != null && cognome != null) {
			return "Nome: " + nome + "     " + "Cognome: " + cognome;
		} else
			return "Nessuna informazione disponibile";
	}
}
