package classiTest;

import java.util.Scanner;

import progetto2014.Sconto;

public class ScontoTester {
	static Scanner scanner = new Scanner(System.in);
	static int success;
	static int counter;
	static int fail;
	static double successRate;
	static double failRate;
	
	public ScontoTester() {
		// TODO Auto-generated constructor stub
	}

	public static void result() {
		System.out.print("\nIl risultato e' quello atteso?\n1 per si, 2 per no: ");
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
			System.out.println("Genero nuovo gruppo di sconti. Disattivi di default (controllo...)");
			Sconto sconto1 = new Sconto();
			if (sconto1.isAttivo())
				System.out.println("Gli sconti sono attivi");
			else 
				System.out.println("Gli sconti non sono attivi");
			result();
			
			System.out.println("\nAttivo gli sconti (e controllo)");
			sconto1.attiva();
			if (sconto1.isAttivo())
				System.out.println("Gli sconti sono attivi");
			else 
				System.out.println("Gli sconti non sono attivi");
			result();
			
			System.out.println("\nControllo all'interno degli sconti generici quali sono attivi (mi aspetto tutti disattivi)");
			if (!sconto1.isAgeActive() && !sconto1.isStudentsActive() && !sconto1.isAfternoonActive())
				System.out.println("Gli sconti (eta', studenti e pomeriggio) non sono attivi");
			else
				System.out.println("Qualcosa non va, ci sono politiche di sconto attive");
			result();
			
			System.out.println("\nAttivo gli sconti sull'eta'");
			sconto1.ageOn();
			if (sconto1.isAgeActive() && !sconto1.isStudentsActive() && !sconto1.isAfternoonActive())
				System.out.println("Lo sconto sull'eta' e' attivo, gli altri sono disattivi");
			else
				System.out.println("Ci sono attivi sconti che dovrebbero essere disattivi");
			result();
			
			System.out.println("\nImposto i risparmi per eta', pomeriggio e studenti rispettivamente a 1, 2 e 3 euro");
			sconto1.setRisparmio(1, 0); // Sconto eta'
			sconto1.setRisparmio(2, 1);	// Sconto pomeriggio
			sconto1.setRisparmio(3, 2); // Sconto studenti
			System.out.println(sconto1);
			result();
			
			System.out.println("\nAttivo tutti gli sconti");
			sconto1.allOn();
			if (sconto1.isAgeActive() && sconto1.isStudentsActive() && sconto1.isAfternoonActive())
				System.out.println("Tutti gli sconti sono attivi");
			else
				System.out.println("Errore nell'attivazione di tutti gli sconti");
			result();
			
			System.out.println("\nCerco il prezzo scontato migliore partendo da un prezzo base di 7\nMi aspetto sia 4euro(sconto studenti)");
			float best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nLascio attivo solo quello sull'eta'\nMi aspetto che il miglior prezzo sia 6 euro ");
			sconto1.allOff();
			sconto1.ageOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nLascio attivo solo quello sul pomeriggio\nMi aspetto che il miglior prezzo sia 5 euro ");
			sconto1.allOff();
			sconto1.afternoonOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nLascio attivo solo quello studenti\nMi aspetto che il miglior prezzo sia 4 euro ");
			sconto1.allOff();
			sconto1.studentsOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nAttivo sconto eta' e pomeriggio\nMi aspetto il miglior prezzo sia 5 euro ");
			sconto1.allOff();
			sconto1.ageOn();
			sconto1.afternoonOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nAttivo sconto eta' e studenti\nMi aspetto il miglior prezzo sia 4 euro ");
			sconto1.allOff();
			sconto1.ageOn();
			sconto1.studentsOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nAttivo sconto pomeriggio e studenti\nMi aspetto il miglior prezzo sia 4 euro ");
			sconto1.allOff();
			sconto1.afternoonOn();
			sconto1.studentsOn();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();
			
			System.out.println("\nDisattivo tutti gli sconti\nMi aspetto il miglior prezzo sia 7 euro ");
			sconto1.allOff();
			best = sconto1.getBestPrezzo(7, sconto1.isAgeActive(), sconto1.isStudentsActive(), sconto1.isAfternoonActive());
			System.out.println("Miglior prezzo: " + best);
			result();


		
		System.out.println("\nTest Totali: " + counter + "\nTest superati: " + success + "			Test Falliti: " + fail 
				+ "\nTasso di successo: " + successRate + "%		Tasso di fallimento: " + failRate + "%");
	}

}
