package grafica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import progetto2014.Sala;
import progetto2014.SalaManager;

/**
 * Questa classe serve a visualizzare graficamente la sala, con impostazioni diverse a secondo
 * che sia visualizzata in modalita' utente o in modalita' gestore
 * 
 */
public class SalaViewer {
	int index1;
	JButton bsource;
	JButton button;
	SalaManager salaManager;

	public SalaViewer(Sala sala,String mode) {
		salaManager = new SalaManager(sala);
		try { // PERMETTE DI AVERE UN USER INTERFACE CROSS PLATFORM E
			// EVITARE ALCUNI PROBLEMI CON LA SALA
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	JFrame frame1 = new JFrame();
	Toolkit tk = Toolkit.getDefaultToolkit();
	int xSize = ((int) tk.getScreenSize().getWidth());
	int ySize = ((int) tk.getScreenSize().getHeight());
	frame1.setSize(xSize, ySize);
	Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
	frame1.setLocation ( ( screenSize.width / 2 ) - ( frame1.getWidth ( ) / 2 ), (
	screenSize.height / 2 ) - ( frame1.getHeight ( ) / 2 ) );

	frame1.setLayout(new GridLayout(10, 10));
	if (mode.equalsIgnoreCase("gestore")) {
	for (int i = 0; i < sala.getCapienza(); i++) {
		button = new JButton(Integer.toString(i + 1));
		button.addActionListener(new ClickPosto());
		button.addMouseListener(new MouseEnteredListener());
		button.addMouseListener(new MouseExitedListener());
		button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		if (sala.getPosti().get(i).isGuasto()) {
			button.setBackground(Color.BLACK);
		    button.setForeground(Color.WHITE);
		}
		else
			button.setBackground(Color.GREEN);
		frame1.add(button);
	}
	}
	
	if (mode.equalsIgnoreCase("utente")) {
		for (int i = 0; i < sala.getCapienza(); i++) {
			button = new JButton(Integer.toString(i + 1));
			button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			button.addMouseListener(new MouseEnteredListener());
			button.addMouseListener(new MouseExitedListener());
			boolean guasto = sala.getPosti().get(i).isGuasto();
			boolean acquistato = (sala.getPosti().get(i).isAcquistato());
			if (guasto) {
				button.setBackground(Color.BLACK);
				button.setForeground(Color.WHITE);
				button.addActionListener(new PrenotazioneGrafica());
			}
			if (!guasto && !acquistato && sala.getPosti().get(i).isPrenotato()) {
				button.setBackground(Color.YELLOW);
				button.setForeground(Color.BLUE);
				button.addActionListener(new PrenotazioneGrafica());
			}
			if (!guasto && !acquistato&& sala.getPosti().get(i).isPrenotato() == false) {
				button.setBackground(Color.GREEN);
				button.addActionListener(new PrenotazioneGrafica());
			}
			if (!guasto && acquistato) {
				button.setBackground(Color.RED);
				button.addActionListener(new PrenotazioneGrafica());
			}
			frame1.add(button);
		}
	}
	
	frame1.setVisible(true);

	try { // TORNO AL LOOK DI DEFAULT DEL SISTEMA
		UIManager.setLookAndFeel(UIManager
				.getSystemLookAndFeelClassName());
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	}
	
	
	
	// ACTIONLISTENER CLICK POSTO
	/**
	 * Permette di accedere a un frame che consente di riparare un posto guasto 
	 * o segnalare come guasto un posto precedentemente utilizzabile
	 */
	class ClickPosto implements ActionListener {
		JFrame frame;
		public void actionPerformed(ActionEvent event) {
			
			index1 = Integer.parseInt(((JButton) event.getSource()).getText()) - 1;
			bsource = (JButton) event.getSource();
			frame = new JFrame("Posto " + Integer.toString(index1 + 1));
			frame.setSize(250, 75);
			Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
			frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
			screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

			frame.setLayout(new GridLayout(1, 0));
			JButton guasto = new JButton("GUASTO");
			JButton riparato = new JButton("RIPARATO");
			guasto.addActionListener(new Guasta());
			riparato.addActionListener(new Ripara());
			frame.add(guasto);
			frame.add(riparato);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		// ACTION LISTENER GUASTA POSTO
		/**
		 * Segnala come guasto un posto precedentemente utilizzabile
		 */
		class Guasta implements ActionListener {
			public void actionPerformed(ActionEvent event) {
					salaManager.guasta(index1);
					SalaGrafica.editList();
					frame.setVisible(false);
					bsource.setBackground(Color.BLACK);
					bsource.setForeground(Color.WHITE);
				}
			}
		

		// ACTIONLISTENER RIPARA POSTO
		/**
		 * Segnala come riparato un posto precedentemente guasto
		 */
		class Ripara implements ActionListener {
			public void actionPerformed(ActionEvent event) {
					salaManager.ripara(index1);
					SalaGrafica.editList();
					frame.setVisible(false);
					bsource.setBackground(Color.GREEN);
					bsource.setForeground(Color.BLACK);
			}
		}
	}


	/**
	 * Setta un bordo rosso quando si passa su un tasto
	 */
	class MouseEnteredListener extends MouseAdapter {
		public void mouseEntered (MouseEvent event) {
			((JButton) event.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		}
	}
	
	/**
	 * Ri-setta il bordo del tasto a nero quando il cursore esce dalla zona del tasto
	 */
	class MouseExitedListener extends MouseAdapter {
		public void mouseExited (MouseEvent event) {
			((JButton) event.getSource()).setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
	}

}
