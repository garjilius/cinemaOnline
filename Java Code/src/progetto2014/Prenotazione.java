package progetto2014;

import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**
* Racchiude tutti gli strumenti necessari a gestire acquisti e prenotazioni
*/
public class Prenotazione  {
	
	private int index;
	private Sala salaSpett;
	private boolean afternoon;
	private Sconto scon;
	private GregorianCalendar time;
	
	/**
	 * Aggiorna la lista degli spettacoli ancora fruibili
	 */
	private void editListFruibili() {
		Utente.spettacoliString.clear();
		Collections.sort(Utils.spettacoliFruibili, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
		for (Spettacolo current : Utils.spettacoliFruibili) {
			Utente.spettacoliString.addElement(current.toString());
		}
		Utente.listSpett.setModel(Utente.spettacoliString);
	}

	public Prenotazione() {
		scon = Utils.sconto;
		time = new GregorianCalendar();
		index = Utente.listSpett.getSelectedIndex();
		salaSpett = Utils.spettacoliFruibili.get(index).getSala();
		time = (GregorianCalendar) Utils.spettacoliFruibili.get(index).getOrario().clone();
		time.set(GregorianCalendar.HOUR_OF_DAY, 18);
		time.set(GregorianCalendar.MINUTE, 0);
		if(Utils.spettacoliFruibili.get(index).getOrario().before(time)) {
			afternoon = true;
		} else {
			afternoon = false;
		}
	}
				
	// PRENOTAZIONE EFFETTIVA
	/**
	 * Permette di eseguire la prenotazione reale del posto, se le prenotazioni non sono scadute
	 * @param nome nome di chi prenota
	 * @param cognome cognome di chi prenota
	 * @param indicePosto indice del posto da prenotare
	 * @param eta indica se chi prenota ha diritto allo sconto eta
	 * @param studente indica se chi prenota ha diritto allo sconto studente
	 */
		public void prenota(String nome, String cognome, int indicePosto, boolean eta, boolean studente) {
		
			GregorianCalendar now = new GregorianCalendar();
			if (Utils.spettacoliFruibili.get(index).getScadenzaPrenotazione().before(now)) {
				try {
					throw new PrenotazioniScaduteException();
				} catch (PrenotazioniScaduteException e1) {
				JOptionPane.showMessageDialog(null, "Prenotazioni scadute");
				return;
				}
			}
			
			salaSpett.getPosti().get(indicePosto).setNome(nome);
			salaSpett.getPosti().get(indicePosto).setCognome(cognome);
			if (eta)
			salaSpett.getPosti().get(indicePosto).setAge();
			if (studente)
			salaSpett.getPosti().get(indicePosto).setStudente();
			
			salaSpett.prenota();
			salaSpett.getPosti().get(indicePosto).setPrenotato();
			Utils.salvaDati();
			editListFruibili();
			}

	
	/**
	 * Permette di acquistare un posto prenotato in precedenza
	 * @param indicePosto indice del posto da acquistare
	 */
			public void acquistaDaPrenotazione(int indicePosto) {
				Posto tempPosto = salaSpett.getPosti().get(indicePosto);
			float prezzospett = Utils.spettacoliFruibili.get(index).getPrezzo();
			tempPosto.setPrezzo(scon.getBestPrezzo(prezzospett, tempPosto.isAge() , 
					tempPosto.isStudente() , afternoon));
			
			salaSpett.rimuoviPrenotazione();
			tempPosto.setLibero();
			tempPosto.setAcquistato();
			salaSpett.acquista();
			Utils.spettacoliFruibili.get(index).setIncasso(tempPosto.getPrezzo());
			Utils.salvaDati();
			editListFruibili();
		}
	

	/**
	 * Permette di acquistare il posto selezionato (senza passare per la prenotazione)
	 * @param nome nome di chi acquista
	 * @param cognome cognome di chi acquista
	 * @param indicePosto indice del posto da acquista
	 * @param eta indica se chi acquista ha diritto allo sconto eta
	 * @param studente indica se chi acquista ha diritto allo sconto studente
	 */
	
		public void acquistaDirettamente(String nome, String cognome,int indicePosto, boolean eta, boolean studente) {
			Posto tempPosto = salaSpett.getPosti().get(indicePosto);
			tempPosto.setNome(nome);
			tempPosto.setCognome(cognome);
			
			
			if (eta) 
				tempPosto.setAge();
									
			if(studente) {
				tempPosto.setStudente();
			}
			float prezzospett = Utils.spettacoliFruibili.get(index).getPrezzo();
			tempPosto.setPrezzo(scon.getBestPrezzo(prezzospett, tempPosto.isAge() , 
					tempPosto.isStudente() , afternoon));
						
			salaSpett.acquista();
			tempPosto.setAcquistato();
			Utils.spettacoliFruibili.get(index).setIncasso(tempPosto.getPrezzo());
			Utils.salvaDati();
			editListFruibili();
		}
	

	// ACTIONLISTENER RIMUOVI PRENOTAZIONE
	/**
	 * Permette di cancellare una prenotazione
	 * @param indicePosto indice del posto per cui cancellare la prenotazione
	 */

		public void cancellaPrenotazione(int indicePosto) {
			
			salaSpett.getPosti().get(indicePosto).setLibero();
			salaSpett.rimuoviPrenotazione();
			Utils.salvaDati();
			editListFruibili();
		}
	

}	

