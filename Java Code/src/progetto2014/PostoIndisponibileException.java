package progetto2014;

/**
* Eccezione che segnala un posto non disponibile (perche' acquistato o guasto)
*/
public class PostoIndisponibileException extends Exception {

	private static final long serialVersionUID = -3463640642040683607L;

	public PostoIndisponibileException() {
		super("Il posto non e' disponibile!");
	}

	public PostoIndisponibileException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
