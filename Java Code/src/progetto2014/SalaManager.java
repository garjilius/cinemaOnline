package progetto2014;

import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * La classe SalaManager.java contiene le funzioni per gestire le sale di un
 * cinema. La funzione per AGGIUNGERE o ELIMINARE una sala, 
 * la funzione per rendere un posto GUASTO o RIPARATO.
 */
public class SalaManager {
	private Sala salaSpett;

	public SalaManager(Sala sala) {
		salaSpett = sala;
	}

	/**
	 * Permette di salvare la sala tramite le informazioni ottenute da AddSala
	 * @param numSala numero di sala
	 * @param numPosti numero dei posti della sala
	 */
	public static void creaSala(int numSala, int numPosti) {
		Sala sala = new Sala(numPosti, numSala);
		Utils.sale.add(sala);
		Utils.salvaDati();
	}

	/**
	 * Aggiorna le sale di tutti gli spettacoli controllando i guasti e riparati
	 */
	public static void aggiornaSale() {
		for (Sala sal : Utils.sale) {
			for (Spettacolo spett : Utils.spettacoli) {
				if (spett.getSala().getNumeroSala()==sal.getNumeroSala()) {
					for (int i = 0; i < sal.getCapienza(); i++) {
						if (sal.getPosti().get(i).isGuasto() && !spett.getSala().getPosti().get(i).isGuasto()) {
							if (spett.getSala().getPosti().get(i).isAcquistato())
								spett.getSala().rimuoviAcquisto();
							if (spett.getSala().getPosti().get(i).isPrenotato()) {
								spett.getSala().getPosti().get(i).setWasPrenotato();
								spett.getSala().rimuoviPrenotazione();
							}
							spett.getSala().getPosti().get(i).setGuasto();
							spett.getSala().guasta();
						}
						if (!sal.getPosti().get(i).isGuasto() && spett.getSala().getPosti().get(i).isGuasto()) {
							spett.getSala().getPosti().get(i).setLibero();
							spett.getSala().ripara();
							spett.getSala().getPosti().get(i).setRiparato();
							if (spett.getSala().getPosti().get(i).wasPrenotato()) {
								spett.getSala().getPosti().get(i).setPrenotato();
								spett.getSala().prenota();
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Permette di cancellare una sala
	 * @param indice indice della sala da rimuovere
	 */
	public static void cancellaSala(int indice) {
		ArrayList<Spettacolo> toRemove = new ArrayList<Spettacolo>();
		for (Spettacolo current : Utils.spettacoli) {
			if (current.getSala().equals(Utils.sale.get(indice))) {
				toRemove.add(current);
			}
		}
		for (Spettacolo current : toRemove) {
			Utils.spettacoli.remove(current);
		}
		Utils.sale.remove(indice);
		Utils.salvaDati();
	}

	/**
	 * Segnala come guasto un posto precedentemente utilizzabile
	 * @param indice indice del posto da guastare
	 */
	public void guasta(int indice) {

		if (salaSpett.getPosti().get(indice).isGuasto()) {
			JOptionPane.showMessageDialog(null, "Il posto e' gia' guasto");
		} else {
			salaSpett.guasta();
			salaSpett.getPosti().get(indice).setGuasto();
			aggiornaSale();
			Utils.salvaDati();
		}
	}

	/**
	 * Segnala come riparato un posto precedentemente guasto
	 * @param indice del posto da riparare
	 */
	public void ripara(int indice) {

		if (salaSpett.getPosti().get(indice).isGuasto()) {
			salaSpett.getPosti().get(indice).setRiparato();
			salaSpett.ripara();
			aggiornaSale();
			Utils.salvaDati();

		} else {
			JOptionPane.showMessageDialog(null, "Il posto non e' guasto");
		}
	}
}
	