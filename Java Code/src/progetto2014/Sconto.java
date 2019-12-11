package progetto2014;

import java.io.Serializable;
import java.util.Arrays;

/**Questa classe implementa il concetto di SCONTO */
public class Sconto implements Serializable {
	private static final long serialVersionUID = 757379600301076637L;
	private boolean active, age, afternoon, students;
	private float[] risparmio = new float[3];
	private float[] prezzi = new float[3];

	// COSTRUTTORE
	public Sconto() {
		active = false;
	}

	// METODI ATTIVAZIONE SCONTI
	/**
	 * Attiva gli sconti
	 */
	public void attiva() {
		active = true;
	}

	/**
	 * Disattiva gli sconti
	 */
	public void disattiva() {
		active = false;
	}

	/**
	 * Controlla lo stato di attivazione degli sconti
	 * @return stato di attivazione degli sconti
	 */
	public boolean isAttivo() {
		return active;
	}

	// METODI SETTAGGI SCONTI
	/** 
	 * Attiva gli sconti sull'eta'
	 */
	public void ageOn() {
		age = true;
	}

	/**
	 * Disattiva gli sconti sull'eta'
	 */
	public void ageOff() {
		age = false;
	}

	/**
	 * Controlla lo stato di attivazione degli sconti sull'eta'
	 * @return stato di attivazione dello sconto eta'
	 */
	public boolean isAgeActive() {
		return age;
	}

	/**
	 * Attiva lo sconto orario
	 */
	public void afternoonOn() {
		afternoon = true;
	}

	/**
	 * Disattiva lo sconto orario
	 */
	public void afternoonOff() {
		afternoon = false;
	}

	/**
	 * Controlla lo stato di attivazione dello sconto orario
	 * @return stato di attivazione dello sconto orario
	 */
	public boolean isAfternoonActive() {
		return afternoon;
	}

	/**
	 * Attiva lo sconto studenti
	 */
	public void studentsOn() {
		students = true;
	}

	/**
	 * Disattiva lo sconto studenti
	 */
	public void studentsOff() {
		students = false;
	}

	/** 
	 * Controlla lo stato di attivazione dello sconto studenti
	 * @return stato di attivazione dello sconto studenti
	 */
	public boolean isStudentsActive() {
		return students;
	}
	
	/**
	 * Disattiva tutti gli sconti
	 */
	public void allOff() {
		students = false;
		afternoon = false;
		age = false;
	}
	
	/**
	 * Attiva tutti gli sconti
	 */
	public void allOn() {
		students = true;
		afternoon = true;
		age = true;
	}
	
	/**
	 * Resetta il risparmio relativo ai vari sconti
	 */
	public void resetRisparmio() {
		for(int i=0; i<3; i++)
			risparmio[i] = 0;
	}

	/** 
	 * Imposta il risparmio relativo ai vari sconti
	 * @param euro soldi risparmiati
	 * @param index indice nell'array per cui impostare il risparmio (0 eta', 1 orario, 2 studenti)
	 */
	public void setRisparmio(float euro, int index) {
		risparmio[index] = euro;
	}
	
	/**
	 * Restituisce il risparmio per lo sconto eta'
	 * @return risparmio sconto eta'
	 */
	public float getRisparmioAge() {
		return risparmio[0];
	}
	/**
	 * Restituisce il risparmio per lo sconto sulla fascia oraria
	 * @return risparmio sconto fascia oraria
	 */
	public float getRisparmioPomeriggio() {
		return risparmio[1];
	}
	
	/**
	 * Restituisce il risparmio per lo sconto studenti
	 * @return risparmio sconto studenti
	 */
	public float getRisparmioStudenti() {
		return risparmio[2];
	}
	
	/** 
	 * Ordina i prezzi in ordine crescente
	 * @param prezzo prezzo di partenza
	 */
	private void ordinaPrezzi(float prezzo) {
		for(int i=0;i<3;i++)
			prezzi[i] = prezzo - risparmio[i];
		Arrays.sort(prezzi);
	}
	
	/**
	 * Ottiene il miglior prezzo
	 * @param prezzo prezzo di partenza
	 * @param i indice del risparmio del primo sconto da confrontare
	 * @param y indice del risparmio del secondo sconto da confrontare
	 * @return sconto che offre il miglior risparmio
	 */
	private float getBest(float prezzo, int i, int y) {
		if((prezzo - risparmio[i]) < (prezzo - risparmio[y]))
			return prezzo - risparmio[i];
		else
			return prezzo - risparmio[y];
	}

	
	/**
	 * Ottiene il miglior prezzo
	 * @param prezzo prezzo di partenza
	 * @param eta stato di attivazione sconto eta'
	 * @param stud stato di attivazione sconto studenti
	 * @param pome stato di attivazione sconto orario
	 * @return miglior prezzo
	 */
	public float getBestPrezzo(float prezzo, boolean eta, boolean stud, boolean pome)
	{
		if(active) {
			if(age && !students && !afternoon) {
				if(eta) 
					return prezzo - risparmio[0];
				else
					return prezzo;
			} else if(!age && !students && !afternoon) {
					return prezzo;
			} else if(!age && students && !afternoon) {
				if(stud)
					return prezzo - risparmio[2];
				else
					return prezzo;
			} else if(!age && !students && afternoon) {
				if(pome)
					return prezzo - risparmio[1];
				else
					return prezzo;
			} else if(age && students && !afternoon) {
				if(eta && !stud)
					return prezzo - risparmio[0];
				else if(!eta && stud)
					return prezzo - risparmio[2];
				else if(!eta && !stud)
					return prezzo;
				else if(eta && stud)
					return getBest(prezzo, 0, 2);	
			} else if(age && !students && afternoon) {
				if(eta && !pome)
					return prezzo - risparmio[0];
				else if(!eta && pome)
					return prezzo - risparmio[1];
				else if(!eta && !pome)
					return prezzo;
				else if(eta && pome)
					return getBest(prezzo, 0, 1);
			} else if(!age && students && afternoon) {
				if(pome && !stud)
					return prezzo - risparmio[1];
				else if(!pome && stud)
					return prezzo - risparmio[2];
				else if(!pome && !stud)
					return prezzo;
				else if(pome && stud)
					return getBest(prezzo, 1, 2);
			} else if(age && students && afternoon) {
				if(!eta && !pome && !stud)
					return prezzo;
				else if(eta && !pome && !stud)
					return prezzo - risparmio[0];
				else if(!eta && pome && !stud)
					return prezzo - risparmio[1];
				else if(!eta && !pome && stud)
					return prezzo - risparmio[2];
				else if(eta && pome && !stud)
					return getBest(prezzo, 0, 1);
				else if(eta && !pome && stud)
					return getBest(prezzo, 0, 2);
				else if(!eta && pome && stud)
					return getBest(prezzo, 1, 2);
				else if(eta && pome && stud) {
					ordinaPrezzi(prezzo);
					return prezzi[0];
				}
			}
		}
		return prezzo;
	}

	/**
	 * Override del toString  per avere informazioni sul risparmio per l'utente relativo alle varie tipologie di sconto
	 */
	@Override
	public String toString() {
		return "Eta': " + risparmio[0] + " Pome.: " + risparmio[1] + " Stud.: " + risparmio[2];
	}

}
