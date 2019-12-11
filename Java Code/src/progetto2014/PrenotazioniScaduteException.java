package progetto2014;
/**
 *  Eccezione che segnala l'impossibilità di prenotare in quanto mancano meno di 12 ore dall'inizio dello spettacolo
 */
public class PrenotazioniScaduteException extends Exception {


	private static final long serialVersionUID = 1L;

	public PrenotazioniScaduteException() {
		super("Le prenotazioni sono scadute!");
	}

	public PrenotazioniScaduteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
