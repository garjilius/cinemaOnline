package classiTest;

import java.util.Scanner;

import progetto2014.Sala;

public class SalaTester {
	static Scanner scanner = new Scanner(System.in);
static int success;
static int counter;
static int fail;
static double successRate;
static double failRate;
	
	public SalaTester() {
		
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

		System.out.println("Creo \" Sala 1\" con 100 posti");
		Sala salaTest1 = new Sala (100,1);
		System.out.println("Mi attendo: NOME: Sala 1 POSTI: 100 LIBERI: 100 GUASTI: 0");
		System.out.println(salaTest1);
		result();
		
		System.out.println("\nGuasto 5 posti");
		for (int i = 0; i< 5; i++)
		salaTest1.guasta();
		System.out.println("\nMi attendo che sia notificato che ci sono 100 posti di cui 95 disponibili e 5 guasti");
		System.out.println(salaTest1);
		System.out.println("Numero di posti guasti: " + salaTest1.getGuasti());
		result();
		
		System.out.println("\nNome della sala: Sala " + salaTest1.getNumeroSala());
		System.out.println("Cambio il nome da Sala 1 a Sala 2");
		salaTest1.setNumeroSala(2);
		System.out.println("Nome della sala: Sala " + salaTest1.getNumeroSala());
		result();

		System.out.println("\nModifico la capizenza della sala da 100 a 200 posti");
		salaTest1.setCapienza(200);
		System.out.println("Capienza: "+ salaTest1.getCapienza());
		System.out.println(salaTest1);
		result();
		
		System.out.println("\nRiparo uno dei posti. I guasti diventeranno 4 e i disponibili 196");
		salaTest1.ripara();
		System.out.println(salaTest1);
		result();
		
		System.out.println("\nAcquisto due posti. I disponibili diventeranno 194");
		salaTest1.acquista();
		salaTest1.acquista();
		System.out.println(salaTest1);
		System.out.println("Acquistati: " +salaTest1.getAcquistati());
		result();

		System.out.println("\nPrenoto due posti. I disponibili diventeranno 192");
		salaTest1.prenota();
		salaTest1.prenota();
		System.out.println(salaTest1);
		System.out.println("Acquistati: " +salaTest1.getAcquistati() + "	Prenotati: " + salaTest1.getPrenotati());
		result();
	
		System.out.println("\nRimuovo un acquisto e una prenotazione. I disponibili diventeranno 194");
		salaTest1.rimuoviAcquisto();
		salaTest1.rimuoviPrenotazione();
		System.out.println(salaTest1);
		System.out.println("Acquistati: " +salaTest1.getAcquistati() + "	Prenotati: " + salaTest1.getPrenotati());
		result();
		
		System.out.println("\nClono la sala e le stampo entrambe. La seconda avrà tutti posti liberi");
		Sala salaTest2 = salaTest1.clone();
		System.out.println(salaTest1 + "\n" + salaTest2);
		result();
		
		System.out.println("\nRinomino la seconda sala in Sala 3");
		salaTest2.setNumeroSala(3);
		System.out.println(salaTest1 + "\n" + salaTest2);
		result();
		
		System.out.println("\nConfronto le due sale (per numero di sala)\nMi aspetto che venga stampato che Sala 3 ha un numero maggiore di Sala 2");
		if (salaTest1.compareTo(salaTest2) == -1)
			System.out.println("Sala " + salaTest2.getNumeroSala()+  " ha un numero di sala maggiore di Sala " + salaTest1.getNumeroSala());
		else 
			System.out.println("ERRORE nel compareTO");
		result();
		
		System.out.println("\nCreo una terza Sala (Sempre Sala 2) con gli stessi posti liberi di Sala 2 e controllo se sono uguali (devono esserlo)");
		Sala salaTest3 = salaTest1.clone();
		if (salaTest3.equals(salaTest1))
			System.out.println("Le sale sono uguali");
		else
			System.out.println("Le sale sono diverse");
		result();

		System.out.println("\nAdesso confronto una delle due \"Sala 2\" con Sala 3. Mi aspetto mi dica che sono diverse");
		if (salaTest3.equals(salaTest2))
			System.out.println("Le sale sono uguali");
		else
			System.out.println("Le sale sono diverse");
		result();
		
		System.out.println("\nTest Totali: " + counter + "\nTest superati: " + success + "			Test Falliti: " + fail 
				+ "\nTasso di successo: " + successRate + "%		Tasso di fallimento: " + failRate + "%");
	
	}

}
