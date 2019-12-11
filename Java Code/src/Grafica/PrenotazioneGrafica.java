package grafica;

	import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import progetto2014.Posto;
import progetto2014.PostoIndisponibileException;
import progetto2014.Prenotazione;
import progetto2014.PrenotazioniScaduteException;
import progetto2014.Sala;
import progetto2014.Sconto;
import progetto2014.Utente;
import progetto2014.Utils;

	/**
	* Racchiude tutti gli strumenti necessari a gestire acquisti e prenotazioni
	*/
	public class PrenotazioneGrafica implements ActionListener  {
		
		private int indexSala= Utente.listSpett.getSelectedIndex();
		private int indexPosto;
		private JButton bsource;
		private JTextField nome, cognome;
		private JRadioButton[] rb;
		private JFrame src,frame;
		private Sala s= Utils.spettacoliFruibili.get(indexSala).getSala();
		private Sconto scon= Utils.sconto;
		private JCheckBox student;
		Prenotazione prenotazione = new Prenotazione();
		
		public PrenotazioneGrafica() {	
			
		}
		
		// ACTIONLISTENER INFORMAZIONI ACQUISTA - PRENOTA POSTO
		/**
		 * Permette di acquistare direttamente un posto o di prenotarlo
		 */
		
			public void actionPerformed(ActionEvent event) {
				bsource = (JButton) event.getSource();

				indexPosto = Integer.parseInt((bsource).getText()) - 1;

				if(s.getPosti().get(indexPosto).isGuasto() || s.getPosti().get(indexPosto).isAcquistato())
				{
					try {
						throw new PostoIndisponibileException();
					} catch (PostoIndisponibileException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Il posto non e' disponibile");		
						return;
					}
				}
				if (s.getPosti().get(indexPosto).isPrenotato()) {
					clickPostoOccupato();
				}
				if (s.getPosti().get(indexPosto).isDisponibile()) {
				frame = new JFrame("Prenota o acquista posto");			
				frame.setVisible(true);
				Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
				frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
				screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
				frame.setLayout(new GridLayout(0, 1));
				src = frame;
				JLabel labelN = new JLabel("Nome");
				nome = new JTextField(15);
				JLabel labelC = new JLabel("Cognome");
				cognome = new JTextField(15);
				student = new JCheckBox("Studente");		
				// GRUPPO BOTTONI
				JLabel labelE = new JLabel("Eta'");
				ButtonGroup bg = new ButtonGroup();
				rb = new JRadioButton[3];
				rb[0] = new JRadioButton("0-14");
				rb[1] = new JRadioButton("15-64");
				rb[2] = new JRadioButton("65+");
				for (int i = 0; i < 3; i++)
					bg.add(rb[i]);
				JButton button = new JButton("Prenota!");
				button.addActionListener(new Prenota2());
				JButton button2 = new JButton("Acquista!");
				button2.addActionListener(new AcquistaDirettamente());
				
				JPanel panelNome = new JPanel();
				panelNome.add(labelN);
				panelNome.add(nome);
				JPanel panelCognome = new JPanel();
				panelCognome.add(labelC);
				panelCognome.add(cognome);
				JPanel panelEta = new JPanel();
				panelEta.add(labelE);
				panelEta.add(rb[0]);
				panelEta.add(rb[1]);
				panelEta.add(rb[2]);
				JPanel panelStudent = new JPanel();
				panelStudent.add(student);
				JPanel panelButton = new JPanel();
				panelButton.add(button);
				panelButton.add(button2);
				JPanel panelText = new JPanel();
				panelText.setLayout(new GridLayout(0,1));
				JLabel label = new JLabel();
				JLabel label2 = new JLabel();
				label.setHorizontalAlignment(JLabel.CENTER);
	            label2.setHorizontalAlignment(JLabel.CENTER);
	            
	            frame.add(panelNome);
				frame.add(panelCognome);
				if(scon.isAttivo()) {
					frame.setSize(350, 365);
					label.setText("SCONTI ATTIVATI");
					label2.setText("Sara' calcolato il prezzo migliore");
					panelText.add(label);
					panelText.add(label2);
					frame.add(panelEta);
					frame.add(panelStudent);
					frame.add(panelButton);	
					frame.add(panelText);
				} else {
					frame.setSize(350, 220);
					label.setText("SCONTI DISATTIVATI");
					panelText.add(label);
					frame.add(panelButton);
					frame.add(panelText);
				}
				}
		}


		// PRENOTAZIONE EFFETTIVA
		/**
		 * Permette di eseguire la prenotazione reale del posto, se le prenotazioni non sono scadute
		 */
		class Prenota2 implements ActionListener {
			String name;
			String surname;
			boolean scontoEta =false;
			boolean scontoStudente = false;
			public void actionPerformed(ActionEvent e) {

				GregorianCalendar now = new GregorianCalendar();
				if (Utils.spettacoliFruibili.get(indexSala).getScadenzaPrenotazione().before(now)) {
					try {
						throw new PrenotazioniScaduteException();
					} catch (PrenotazioniScaduteException e1) {
					JOptionPane.showMessageDialog(null, "Prenotazioni scadute");
					return;
					}
				}
				
				if(nome.getText() == null || cognome.getText().length() <= 0 || cognome.getText() == null || nome.getText().length() <= 0) {
					JOptionPane.showMessageDialog(null, "ATTENZIONE: Inserire nome o cognome");
					return;
				}
				name = nome.getText();
				surname = cognome.getText();
				
					if (rb[0].isSelected() || rb[2].isSelected()) {
						scontoEta=true;
					}
				
				if(student.isSelected()) {
						scontoStudente=true;
				}
				
				prenotazione.prenota(name, surname,indexPosto, scontoEta, scontoStudente);
						
				src.setVisible(false);
				bsource.setBackground(Color.YELLOW);
				bsource.setForeground(Color.BLUE);
				}
		}



		/**
		 * Gestisce il click di un posto occupato (perche' prenotato) e permette di inserire le proprie generalite'
		 * per avere la possibilita' di acquistare il posto prenotato o cancellare la prenotazione
		 */
				public void clickPostoOccupato(){

				JFrame frame = new JFrame("Inserisci generalita'");
				frame.setSize(340, 240);
				Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
				frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
				screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

				frame.setLayout(new GridLayout(0, 1));
				src = frame;

				JLabel labelN = new JLabel("   Nome ");
				nome = new JTextField(15);
				JLabel labelC = new JLabel("Cognome");
				cognome = new JTextField(15);
				JButton button = new JButton("Accedi");
				button.addActionListener(new Accedi());

				JPanel panel1 = new JPanel();
				panel1.add(labelN);
				panel1.add(nome);
				JPanel panel2 = new JPanel();
				panel2.add(labelC);
				panel2.add(cognome);
				frame.add(panel1);
				frame.add(panel2);
				frame.add(button);
				frame.setVisible(true);
				frame.setResizable(false);
		}

		// ACTIONLISTENER ACCESSO AL POSTO OCCUPATO
		/**
		 * Gestisce l'accesso a un posto prenotato e offre la possibilita' di acquistare il posto prenotato o cancellare la prenotazioen
		 */
		class Accedi implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				
					Posto tmp = s.getPosti().get(indexPosto);
				if (tmp.getNome().equalsIgnoreCase(nome.getText()) && tmp.getCognome().equalsIgnoreCase(cognome.getText())) {
					src.setVisible(false);
					JFrame frame = new JFrame("Posto " + Integer.toString(indexPosto + 1));
					frame.setSize(300, 150);
					Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
					frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
					screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

					frame.setLayout(new GridLayout(0, 1));
					src = frame;

					JLabel label = new JLabel("INFORMAZIONI POSTO");
					label.setHorizontalAlignment(JLabel.CENTER);
					JLabel label1 = new JLabel(s.getPosti().get(indexPosto).toString());
					label1.setHorizontalAlignment(JLabel.CENTER);

					JButton cancellaPrenotazione = new JButton("Cancella prenotazione");
					cancellaPrenotazione.addActionListener(new CancellaPrenotazione());
					JButton acquista = new JButton("Acquista");
					acquista.addActionListener(new AcquistaDaPrenotazione());

					frame.add(label);
					frame.add(label1);
					JPanel modificaPrenotazione = new JPanel();

					modificaPrenotazione.add(cancellaPrenotazione);
					modificaPrenotazione.add(acquista);
					frame.add(modificaPrenotazione);
					frame.setVisible(true);
					frame.setResizable(false);
				} else
					JOptionPane.showMessageDialog(null, "DATI INSERITI SCORRETTI!");
			}
		}

		/**
		 * Permette di acquistare un posto prenotato in precedenza
		 */
		class AcquistaDaPrenotazione implements ActionListener {
			public void actionPerformed(ActionEvent e) {			
				prenotazione.acquistaDaPrenotazione(indexPosto);
				JOptionPane.showMessageDialog(null, "Prezzo di questo posto: "+ s.getPosti().get(indexPosto).getPrezzo());
				src.setVisible(false);
				bsource.setBackground(Color.RED);
				bsource.setForeground(Color.BLACK);
			}
		}

		/**
		 * Permette di acquistare il posto selezionato (senza passare per la prenotazione)
		 */
		class AcquistaDirettamente implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				boolean eta=false;
				boolean studente=false;
				String name = nome.getText();
				String surname = cognome.getText();
				
				if(name == null || surname.length() <= 0 || surname == null || name.length() <= 0) {
					JOptionPane.showMessageDialog(null, "ATTENZIONE: Inserire nome o cognome");
					return;
				}			
				for (int i = 0; i < 3; i++) {
					if (rb[0].isSelected() || rb[2].isSelected()) {
							eta=true;
							}
					}
				
				if(student.isSelected()) {
					studente=true;
				}
				prenotazione.acquistaDirettamente(name, surname, indexPosto, eta, studente);
				JOptionPane.showMessageDialog(null, "Prezzo di questo posto: "+ s.getPosti().get(indexPosto).getPrezzo());

				src.setVisible(false);
				bsource.setBackground(Color.RED);
				bsource.setForeground(Color.BLACK);
				Utils.salvaDati();
			}
		}

		// ACTIONLISTENER RIMUOVI PRENOTAZIONE
		/**
		 * Permette di cancellare una prenotazione
		 */
		class CancellaPrenotazione implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				prenotazione.cancellaPrenotazione(indexPosto);
				bsource.setBackground(Color.GREEN);
				bsource.setForeground(Color.BLACK);
				JOptionPane.showMessageDialog(null, "Prenotazione Cancellata");
			}
		}
		
	}
