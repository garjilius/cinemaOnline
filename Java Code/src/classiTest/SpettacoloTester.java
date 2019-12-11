package classiTest;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

import progetto2014.Sala;
import progetto2014.Spettacolo;
public class SpettacoloTester {

static Scanner scanner = new Scanner(System.in);
static int success;
static int counter;
static int fail;
static double successRate;
static double failRate;
static GregorianCalendar orario1 = new GregorianCalendar(2015,0,1,20,30); 
private static SimpleDateFormat printorario = new SimpleDateFormat("HH:mm");
private static SimpleDateFormat printTutto = new SimpleDateFormat("dd/MM/yyyy - HH:mm");


	public SpettacoloTester() {
		// TODO Auto-generated constructor stub
	}

	public static void result() {
		System.out.print("\nIl risultato è quello atteso?\n1 per si, 2 per no: ");
		int risposta = scanner.nextInt();
		if (risposta == 1) {
			success++;
		}
		if (risposta == 2) {
			fail++;
		}
		counter++;
		successRate = (success * 100) / counter;
		failRate = (fail * 100) / counter;
	}
	
	public static void main(String[] args) {

		System.out.println("Creo uno spettacolo e gli assegno quello che serve");
		Spettacolo spettacoloTest1 = new Spettacolo();
		spettacoloTest1.setFilm("Shrek");
		spettacoloTest1.setDurata(90);
		spettacoloTest1.setOrario(orario1);
		Sala salaspett1 = new Sala(150,1);
		spettacoloTest1.setOrarioFine();
		spettacoloTest1.setSala(salaspett1);
		System.out.println("Mi aspetto stampi:\nSETT: 1  || FILM: Shrek  || SALA: 1  || POSTI LIBERI: 150  || ORARIO: 01/01/2015 - 20:30 - 22:00\nRisultato:");
		System.out.println(spettacoloTest1);
		result();
		
		System.out.println("\nStampo il nome del film (Shrek)");
		System.out.println(spettacoloTest1.getFilm());
		result();
		
		System.out.println("\nStampo la durata del film (90min)");
		System.out.println(spettacoloTest1.getDurata() + " minuti");
		result();
		
		System.out.println("Setto il prezzo dello spettacolo a 7 euro");
		spettacoloTest1.setPrezzo(7);
		System.out.println("Il prezzo è di " + spettacoloTest1.getPrezzo() + " euro");
		result();
		
		
		System.out.println("Stampo l'orario di inizio (20.30) e di fine (22.00):\n" 
							+ printTutto.format(spettacoloTest1.getOrario().getTime()) + " - "
							+ printorario.format(spettacoloTest1.getOrarioFine().getTime()) );
		result();
		
		System.out.println("Stampo l'orario di fine delle prenotazioni (12h prima dell'inizio)");
		System.out.println(printTutto.format(spettacoloTest1.getScadenzaPrenotazione().getTime()));
		result();
		
		System.out.println("Clono il film e stampo la copia e l'originale");
		Spettacolo spettacoloTest2 = spettacoloTest1.clone();
		System.out.println(spettacoloTest1 + "\n" + spettacoloTest2);
		result();
		
		System.out.println("Setto l'incasso del primo spettacolo a 100 euro");
		spettacoloTest1.setIncasso(100);
		System.out.println("Incasso: " + spettacoloTest1.getIncasso() + " euro");
		result();
		
		System.out.println("Rinomino la sala del secondo spettacolo in Sala 2.\n"
				+ "Occupo 10 (5 acquisti e 5 prenotazioni) posti per il primo e\n3 (1 acquisto e 2 prenotazioni) per il secondo spettacolo.");
		spettacoloTest2.getSala().setNumeroSala(2);
		for (int i = 0; i<5; i++) {
		spettacoloTest1.getSala().acquista();
		spettacoloTest1.getSala().prenota();
		}
		spettacoloTest2.getSala().acquista();
		spettacoloTest2.getSala().prenota();
		spettacoloTest2.getSala().prenota();	
		System.out.println(spettacoloTest1 + "\n" + spettacoloTest2);
		result();
		
		System.out.println("Provo il metodo equals sui due spettacolo\nSiccome il film è lo stesso mi aspetto che risultino uguali");
		if (spettacoloTest1.equals(spettacoloTest2))
			System.out.println("I due spettacoli sono uguali");
		else
			System.out.println("I due spettacoli non sono uguali");
		result();
		
		System.out.println("Provo il metodo compareTo sui due spettacolo\nMi aspetto risulti che il secondo ha più posti disponibili del primo");
		if ((spettacoloTest1.compareTo(spettacoloTest2)) == -1)
			System.out.println("Spettacolo 1 ha meno posti di Spettacolo 2");
		if ((spettacoloTest1.compareTo(spettacoloTest2)) == 1)
			System.out.println("Spettacolo 2 ha meno posti di Spettacolo 1");
		if ((spettacoloTest1.compareTo(spettacoloTest2)) == 0)
			System.out.println("Spettacolo 1 ha gli stessi posti di Spettacolo 2");
		result();

		System.out.println("\nTest Totali: " + counter + "\nTest superati: " + success + "			Test Falliti: " + fail 
				+ "\nTasso di successo: " + successRate + "%		Tasso di fallimento: " + failRate + "%");
	}

}
