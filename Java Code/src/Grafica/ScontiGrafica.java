package grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progetto2014.Utils;

/** La classe ScontiManager.java implementa le impostazioni degli sconti.
 * Gli sconti implementati sono ORARIO POMERIDIANO, Utils.sconto ETA' e Utils.sconto STUDENTE.
 * Questa classe permette di attivare gli sconti, e di decidere quali devono essere attivati,
 * e lo Utils.sconto sul prezzo per ogni tipologia.
 * Tutte le impostazioni vengono salvate in un OGGETTO Utils.sconto, 
 * il quale viene caricato e salvato ogni volta in un file. 
 */
public class ScontiGrafica implements ActionListener {
	
	private JButton bsource;
	JLabel statoSconti;

	// PULSANTI IMPOSTAZIONI SCONTI
	private JCheckBox age = new JCheckBox("Eta'");
	private JCheckBox orario = new JCheckBox("Fascia pomeridiana");
	private JCheckBox student = new JCheckBox("Studenti");
	private JTextField ageT = new JTextField(4);
	private JTextField orarioT = new JTextField(4);
	private JTextField studentT = new JTextField(4);

	private void controllo() {
		if (!(Utils.sconto.isAttivo())) {
			age.setEnabled(false);
			orario.setEnabled(false);
			student.setEnabled(false);
			ageT.setEditable(false);
			orarioT.setEditable(false);
			studentT.setEditable(false);
		} else {
			age.setEnabled(true);
			orario.setEnabled(true);
			student.setEnabled(true);
			ageT.setEditable(true);
			orarioT.setEditable(true);
			studentT.setEditable(true);
		}
	}

	public void actionPerformed(ActionEvent event) {
		
		// SETTAGGI FRAME

		JFrame frame = new JFrame("IMPOSTAZIONI SCONTI");
		frame.setSize(300, 250);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

		frame.setLayout(new GridLayout(0, 1));

		// ATTIVA/DISATTIVA SCONTI
		JButton attiva = new JButton();
		if (Utils.sconto.isAttivo()) {
			attiva.setBackground(Color.GREEN);
			attiva.setText("SCONTI ATTIVATI");
		} else {
			attiva.setBackground(Color.RED);
			attiva.setText("SCONTI DISATTIVATI");
		}
		if (Utils.sconto.isAgeActive()) {
		ageT.setText(""+Utils.sconto.getRisparmioAge());
		age.setSelected(true);
		}
		if (Utils.sconto.isAfternoonActive()) {
		orarioT.setText(""+Utils.sconto.getRisparmioPomeriggio());	
		orario.setSelected(true);
		}
		if (Utils.sconto.isStudentsActive()) {
		studentT.setText(""+Utils.sconto.getRisparmioStudenti());	
		student.setSelected(true);
		}
		
		controllo();
		JButton aggiorna = new JButton("Aggiorna");
		JLabel labelP = new JLabel("Usare . per cifre decimali");
		labelP.setHorizontalAlignment(JLabel.CENTER);

		attiva.addActionListener(new Attiva());
		aggiorna.addActionListener(new Aggiorna());

		JPanel panel1 = new JPanel();
		panel1.add(age);
		panel1.add(ageT);
		JPanel panel2 = new JPanel();
		panel2.add(orario);
		panel2.add(orarioT);
		JPanel panel3 = new JPanel();
		panel3.add(student);
		panel3.add(studentT);
		frame.add(attiva);
		frame.add(labelP);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(aggiorna);

		statoSconti = new JLabel();
		if (Utils.sconto.isAttivo()) {
			statoSconti.setText(Utils.sconto.toString());
		} else {
			statoSconti.setText("NESSUN RISPARMIO");
		}
		statoSconti.setHorizontalAlignment(JLabel.CENTER);
		frame.add(statoSconti);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/**
	 * Permette di attivare/disattivare gli sconti
	 */
	class Attiva implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			bsource = (JButton) event.getSource();
			
			if (Utils.sconto.isAttivo()) {
				Utils.sconto.disattiva();
				Utils.sconto.allOff();
				bsource.setBackground(Color.RED);
				bsource.setText("SCONTI DISATTIVATI");
				age.setEnabled(false);
				orario.setEnabled(false);
				student.setEnabled(false);
				ageT.setEditable(false);
				orarioT.setEditable(false);
				studentT.setEditable(false);
			} else {
				Utils.sconto.attiva();
				bsource.setBackground(Color.GREEN);
				bsource.setText("SCONTI ATTIVATI");
				age.setEnabled(true);
				orario.setEnabled(true);
				student.setEnabled(true);
				ageT.setEditable(true);
				orarioT.setEditable(true);
				studentT.setEditable(true);
			}

			Utils.sconto.resetRisparmio();
			Utils.salvaDati();
		}
	}

	/**
	 * Permette di aggiornare le informazioni sugli sconti presenti precedentemente con quelle piu' aggiornate
	 */
	class Aggiorna implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (age.isSelected()) {
				Utils.sconto.ageOn();
				Utils.sconto.setRisparmio(Float.parseFloat(ageT.getText()), 0);
			} else {
				Utils.sconto.ageOff();
				ageT.setText("");
				Utils.sconto.setRisparmio(0, 0);

			}

			if (orario.isSelected()) {
				Utils.sconto.afternoonOn();
				Utils.sconto.setRisparmio(Float.parseFloat(orarioT.getText()), 1);
			} else {
				orarioT.setText("");
				Utils.sconto.afternoonOff();
				Utils.sconto.setRisparmio(0, 1);
			}

			if (student.isSelected()) {
				Utils.sconto.studentsOn();
				Utils.sconto.setRisparmio(Float.parseFloat(studentT.getText()), 2);
			} else {
				studentT.setText("");
				Utils.sconto.studentsOff();
				Utils.sconto.setRisparmio(0, 2);
			}
			statoSconti.setText(Utils.sconto.toString());
			Utils.salvaDati();
			JOptionPane.showMessageDialog(null, "Impostazioni sconti aggiornate");

		}
	}

}
