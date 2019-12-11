package grafica;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import progetto2014.InvalidNumberException;
import progetto2014.Sala;
import progetto2014.SalaIndisponibileException;
import progetto2014.SalaManager;
import progetto2014.Utils;

/**
 *  Visualizza la grafica di gestione delle sale e permette di richiamare i metodi di SalaManager
 *
 */
public class SalaGrafica implements ActionListener {
	
	private int index;
	private JTextField numeroSalaT, numeroPostiT;
	private Sala s;
	private JFrame src;
	public static JList<String> list = new JList<String>();
	private JScrollPane pane = new JScrollPane(list);
	private static DefaultListModel<String> saleString = new DefaultListModel<String>();

	public static void editList() {
		saleString.clear();
		Collections.sort(Utils.sale);
		for (Sala current : Utils.sale) {
			saleString.addElement(current.toString());
		}
		list.setModel(saleString);
	}

	// ACTIONPERFORMED INIZALE
	public void actionPerformed(ActionEvent event) {
		
		editList();
		JFrame frame = new JFrame("GESTIONE SALE");
		frame.setSize(350, 150);
		frame.setLayout(new GridLayout(2, 1));
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );


		JButton addSala = new JButton("Aggiungi");
		JButton viewSala = new JButton("Visualizza");
		JButton cancSala = new JButton("Elimina");
		addSala.addActionListener(new AddSala());
		viewSala.addActionListener(new ViewSala());
		cancSala.addActionListener(new CancSala());
		frame.add(pane);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));
		panel.add(addSala);
		panel.add(viewSala);
		panel.add(cancSala);
		frame.add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	// ACTIONLISTENER AGGIUNGI INFORMAZIONI SALA
	/**
	 * Permette di inserire tutte le informazioni necessarie per creare una nuova sala 
	 */
	class AddSala implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			JFrame frame = new JFrame("Aggiungi una sala");
			frame.setLayout(new GridLayout(3, 1));
			frame.setSize(225, 150);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );
            src = frame;
			
			JLabel numeroSala = new JLabel("Numero Sala");
			numeroSalaT = new JTextField(3);
			JLabel numeroPosti = new JLabel("Numero di posti");
			numeroPostiT = new JTextField(3);
			JButton salvaSala = new JButton("Salva");

			JPanel panel = new JPanel();
			panel.add(numeroSala);
			panel.add(numeroSalaT);
			JPanel panel1 = new JPanel();
			panel1.add(numeroPosti);
			panel1.add(numeroPostiT);
			JPanel panel2 = new JPanel();
			panel2.add(salvaSala);
			frame.add(panel);
			frame.add(panel1);
			frame.add(panel2);
			frame.setResizable(false);
			frame.setVisible(true);
			
			salvaSala.addActionListener(new SalvaSala()); 
		}
	}

	// ACTIONLISTENER AGGIUNTA EFFETTIVA NUOVA SALA
	/**
	 * Permette di salvare la sala tramite le informazioni ottenute da AddSala
	 */
	class SalvaSala implements ActionListener {
		int numSala;
		int postiSala;
		public void actionPerformed(ActionEvent e) {
			try {
				numSala = (Integer.parseInt(numeroSalaT.getText()));
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ATTENZIONE: Numero sala ERRATO");
				return;
			}
			try {
				postiSala = (Integer.parseInt(numeroPostiT.getText()));
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "ATTENZIONE: Numero posti ERRATO");
				return;
			}
			if (numSala < 0 || postiSala <0) {
				try {
					throw new InvalidNumberException();
				} catch (InvalidNumberException e1) {
					JOptionPane.showMessageDialog(null, "ATTENZIONE: I numeri di sala e di posti devono essere positivi");
					return;
				}
			}
			for (Sala current : Utils.sale) {
				if (current.getNumeroSala()==numSala) {
					try {
						throw new SalaIndisponibileException();
					} catch (SalaIndisponibileException e1) {
						JOptionPane.showMessageDialog(null, "ATTENZIONE: Esiste gia' una sala con quel numero");
						return;
					}
				}
			}
				
			SalaManager.creaSala(numSala, postiSala);
			editList();
			JOptionPane.showMessageDialog(null, "Sala aggiunta!");
			src.setVisible(false);
		}
	}
	
	// ACTIONLISTENER VISUALIZZA SALA
	/**
	 * Permette al gestore di visualizzare una sala, con distinzioni visive tra posti guasti e posti funzionanti
	 */
	class ViewSala implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			index = list.getSelectedIndex();
			s = Utils.sale.get(index);
			new SalaViewer(s,"gestore");
		}
	}

	// ACTIONLISTENER CANCELLA SALA
	/**
	 * Permette di cancellare una sala
	 */
	class CancSala implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			index = list.getSelectedIndex();
			SalaManager.cancellaSala(index);
			JOptionPane.showMessageDialog(null, "Sala eliminata!");
			editList();
		}
	}
}