package progetto2014;

/**
* Gestisce la richiesta di una sala non disponibile
*/
public class SalaIndisponibileException extends Exception {


	private static final long serialVersionUID = 1L;

	public SalaIndisponibileException() {
		super("La Sala non e' disponibile!");	}

	public SalaIndisponibileException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
