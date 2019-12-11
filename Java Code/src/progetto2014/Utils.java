package progetto2014;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
/**
* Raccoglie i metodi utili a salvare e caricare informazioni su spettacoli, sale e sconti. 
*/
public class Utils {
	public static Sconto sconto;
	static File fileSale = new File("ArchivioSale.dat");
	static File fileSpettacoli = new File("Spettacoli.dat");
	static File fileSconti = new File("Sconti.dat");
	public static ArrayList<Sala> sale = new ArrayList<Sala>();
	// Questo Array contiene tutte le sale disponibili del cinema.

	public static ArrayList<Spettacolo> spettacoli;
	// Questo Array contiene tutti gli spettacoli
	
	public static ArrayList<Spettacolo> spettacoliFruibili = new ArrayList<Spettacolo>();
	
	/**
	 *  Restituisce la sala che ha come nome quello passato da parametro, se esiste
	 *  @param i numero di sala della sala che vogliamo ottenere
	 *  @return sala con numero uguale a quello dato in input
	 */
	public static Sala getSalaByNumber(int i) {
		for (Sala current : sale) {
			if (current.getNumeroSala()==i)
				return current;
		}
		return null;
	}
	
	/**
	 *  Genera un arraylist contenente i soli spettacoli fruibili
	 */
	public static void getSpettacoliFruibili() {
		spettacoliFruibili.clear();
		Collections.sort(spettacoli, new SpettacoliComparator.SpettacoliPerOrdineCronologico());
		for (Spettacolo current : spettacoli) {
			if (current.getOrario().after(new GregorianCalendar())) {
				spettacoliFruibili.add(current);
			}
		}
	}
	

	
	@SuppressWarnings("unchecked")

	/**
	 * Carica informazioni su sale, spettacoli e sconti 
	 */
	public static void caricaDati() {
		if (!fileSconti.exists())
			sconto = new Sconto();
		
		try {
			
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("ArchivioSale.dat"));
			sale = (ArrayList<Sala>) input.readObject();
			ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("ArchivioSpettacoli.dat"));
			spettacoli = (ArrayList<Spettacolo>) input2.readObject();
			ObjectInputStream input3 = new ObjectInputStream(new FileInputStream("Sconti.dat"));
			sconto = (Sconto) input3.readObject();	
			input.close();
			input2.close();
			input3.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			sale = new ArrayList<Sala>();
			spettacoli = new ArrayList<Spettacolo>();
			sconto = new Sconto();
			JOptionPane.showMessageDialog(null, "Errore nel caricamento dei file");
		}
	}

	/**
	 * Salva informazioni su sale, spettacoli e sconti
	 */
	public static void salvaDati() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("ArchivioSale.dat"));
			output.writeObject(sale);
			output.close();
			ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("ArchivioSpettacoli.dat"));
			output2.writeObject(spettacoli);
			output2.close();
			ObjectOutputStream output3 = new ObjectOutputStream(new FileOutputStream("Sconti.dat"));
			output3.writeObject(sconto);
			output3.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore nel salvataggio dei file");
		}
	}


	
}
