package progetto2014;

import java.util.Comparator;

/**
 * Questa classe consente di comparare gli spettacoli secondo vari criteri
 */
public class SpettacoliComparator {

	/**
	 * Compara gli spettacoli in base al numero di sala
	 */
	public static class SpettacoliPerSalaCrescente implements Comparator<Spettacolo> {

		@Override
		public int compare(Spettacolo Spett1, Spettacolo Spett2) {
			if (Spett1.getNumeroSala() < Spett2.getNumeroSala()) {
				return -1;
			}
			if (Spett1.getNumeroSala() > Spett2.getNumeroSala()) {
				return 1;
			}
			else
				return 0;
		}
	}	
	
	/**
	 * Compara gli spettacoli in base alla data
	 */
	public static class SpettacoliPerOrdineCronologico implements Comparator<Spettacolo> {

		@Override
		public int compare(Spettacolo Spett1, Spettacolo Spett2) {
			if (Spett1.getOrario().before(Spett2.getOrario())) {
				return -1;
			}
			if (Spett2.getOrario().before(Spett1.getOrario()))  {
				return 1;
			}
			else 
				return 0;	
		}
	}	
	
	/**
	 * Compara gli spettacoli in base al titolo
	 */
	public static class SpettacoliPerTitolo implements Comparator<Spettacolo> {

		@Override
		public int compare(Spettacolo Spett1, Spettacolo Spett2) {
			return Spett1.getFilm().compareToIgnoreCase(Spett2.getFilm());
		}
	}	
	
}
