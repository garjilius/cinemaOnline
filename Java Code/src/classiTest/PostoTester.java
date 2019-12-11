package classiTest;

import java.util.Scanner;

import progetto2014.Posto;

public class PostoTester {
	static Scanner scanner = new Scanner(System.in);
	static int success;
	static int counter;
	static int fail;
	static double successRate;
	static double failRate;
	
	public PostoTester() {

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
		
		System.out.print("Inserire il nome del titolare del nuovo posto da creare: ");
		String nome = scanner.nextLine();
		System.out.print("Inserire il cognome del titolare del nuovo posto da creare: ");
		String cognome = scanner.nextLine();
		System.out.println("Creo il posto e poi lo stampo\n");
		Posto posto1 = new Posto();
		posto1.setNome(nome);
		posto1.setCognome(cognome);
		System.out.println(posto1);
		result();
		
		System.out.print("\nVuoi che il posto goda di agevolazioni sull'eta'?\n1 per si, 2 per no: ");
		int scelta;
		scelta = scanner.nextInt();
		if (scelta == 1)
			posto1.setAge();
		if (posto1.isAge())
			System.out.println("Le agevolazioni sull'eta' sono state attivate");
		else
			System.out.println("Le agevolazioni sull'eta' non sono state attivate");
		result();
		
		System.out.print("\nVuoi che il posto goda di agevolazioni per gli studenti?\n1 per si, 2 per no: ");
		scelta = scanner.nextInt();
		if (scelta == 1)
			posto1.setStudente();
		if (posto1.isStudente())
			System.out.println("Le agevolazioni per gli studenti sono state attivate");
		else
			System.out.println("Le agevolazioni per gli studenti non sono state attivate");
		result();
		
		System.out.println("\nIl posto viene creato come non guasto di default. Ora verrà guastato");
			posto1.setGuasto();
		if (posto1.isGuasto())
			System.out.println("Il posto è ora guasto");
		else
			System.out.println("C'e' stato un errore, non ho potuto guastare il posto");
		result();
		
		System.out.println("\nOra clono il posto e poi stampo sia originale che clone");
		Posto posto2 = posto1.clone();
		System.out.println(posto1 + "\n" + posto2);
		result();
		
		System.out.println("\nModifico nome e cognome del titolare del secondo posto in \"Caio Sempronio\"");
		posto2.setNome("Caio");
		posto2.setCognome("Sempronio");
		System.out.println("Primo posto:\n"+posto1 + "\nSecondo posto:\n" + posto2);
		result();
		
		System.out.println("\nRiparo i due posti (Il secondo è guasto perchè clonato da un guasto).\nAcquisto il primo posto e prenoto il secondo");
		posto1.setRiparato();
		posto2.setRiparato();
		posto1.setAcquistato();
		posto2.setPrenotato();
		System.out.println("\nOra mi aspetto che mi sia stampato che i due posti non sono disponibili");
		if (!posto1.isDisponibile())
			System.out.println("Posto 1 non è disponibile");
		else 
			System.out.println("Posto 1 è disponibile");
		if (!posto2.isDisponibile())
			System.out.println("Posto 2 non è disponibile");
		else 
			System.out.println("Posto 2 è disponibile");
		result();
		
		System.out.println("\nImposto il prezzo del primo posto a 7 euro e del secondo a 10");
		posto1.setPrezzo(7);
		posto2.setPrezzo(10);
		System.out.println("Prezzo posto 1: " + posto1.getPrezzo() + "\nPrezzo posto 2: " + posto2.getPrezzo());
		result();
		
		System.out.println("\nTesto l'eguaglianza dei due posti. Mi aspetto risulti che sono diversi");
		if (posto1.equals(posto2))
			System.out.println("I due posti sono uguali");
		else 
			System.out.println("I due posti sono diversi");
		result();
		
		System.out.println("\nCreo un terzo posto clonando posto 1 e poi confronto posto 3 e posto 1\nMi aspetto risultino uguali");
		Posto posto3 = posto1.clone();
		if (posto1.equals(posto3))
			System.out.println("I due posti sono uguali");
		else 
			System.out.println("I due posti sono diversi");
		result();

		

		
		System.out.println("\nTest Totali: " + counter + "\nTest superati: " + success + "			Test Falliti: " + fail 
				+ "\nTasso di successo: " + successRate + "%		Tasso di fallimento: " + failRate + "%");
	}

}
