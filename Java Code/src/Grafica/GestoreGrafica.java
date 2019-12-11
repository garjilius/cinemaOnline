package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import progetto2014.Gestore;
import progetto2014.Spettacolo;
import progetto2014.Utils;


/**
 * Visualizza la grafica del lato gestore del programma
 *
 */
public class GestoreGrafica implements ActionListener {
	
	Gestore gestore;
	ArrayList<String> titoli = new ArrayList<String>();
	
	public void actionPerformed(ActionEvent event) {

		gestore = new Gestore();
		// FRAME E I SUOI SETTAGGI
		JFrame frame = new JFrame("MODALITA' GESTORE");
		frame.setSize(250, 200);
		Dimension screenSize = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
		frame.setLocation ( ( screenSize.width / 2 ) - ( frame.getWidth ( ) / 2 ), (
		screenSize.height / 2 ) - ( frame.getHeight ( ) / 2 ) );

		frame.setLayout(new GridLayout(0, 1));

		// GRAFICA
		JButton gestSale = new JButton("Gestisci sale");
		JButton gestSpett = new JButton("Gestisci spettacoli");
		JButton gestSconti = new JButton("Gestisci sconti");
		JButton viewIncasso = new JButton("Visualizza incasso");

		// ACTIONLISTENER
		gestSale.addActionListener(new SalaGrafica());
		gestSpett.addActionListener(new SpettacoloGrafica());
		gestSconti.addActionListener(new ScontiGrafica());
		viewIncasso.addActionListener(new ViewIncasso());

		// AGGIUNTA ELEMENTI GRAFICI
		frame.add(gestSale);
		frame.add(gestSpett);
		frame.add(gestSconti);
		frame.add(viewIncasso);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * Permette di visualizzare l'incasso della settimana (totale o per film)
	 */
	class ViewIncasso implements ActionListener {
		
		JLabel label;
		
		public void actionPerformed(ActionEvent event) {
			
			// FRAME E SETTAGGI
			JFrame frame = new JFrame("INCASSO");
			frame.setSize(200, 75);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation ((screenSize.width/2) - (frame.getWidth()/2), (screenSize.height/2) - (frame.getHeight()/2));

			frame.setLayout(new GridLayout(1, 0));

			JButton film = new JButton("Film");
			film.addActionListener(new Settimanale());
				
			JButton tot = new JButton("Totale");
			tot.addActionListener(new Totale());
			
			frame.add(film);
			frame.add(tot);
			frame.setResizable(false);
			frame.setVisible(true);
		}
		
		class Totale implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Incasso totale: " + gestore.getIncassoTotale()+ " euro");
			}
		}
		
		class Settimanale implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				
				JFrame filmFrame = new JFrame("Incasso Settimanale");
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				filmFrame.setLocation ((screenSize.width/2) - (filmFrame.getWidth()/2), (screenSize.height/2) - (filmFrame.getHeight()/2));
				
				filmFrame.setSize(300,150);
				filmFrame.setLayout(new GridLayout(2,1));
				
				JPanel panel = new JPanel();
				panel.setBorder(BorderFactory.createTitledBorder(null, "Incasso Settimanale", TitledBorder.CENTER, 0));
				label = new JLabel();
				panel.add(label);
				JComboBox<String> movies = new JComboBox<String>();
				movies.addActionListener(new MovieListener());
				
				for (Spettacolo current: Utils.spettacoli) {
					if (!findTitolo(current.getFilm()))
						titoli.add(current.getFilm());						
				}
			
				for (String titolo : titoli) {
					movies.addItem(titolo);
				}
				
				filmFrame.add(movies);
				filmFrame.add(panel);
				filmFrame.setVisible(true);
			}
		}
		
		class MovieListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
			String nome = (String) combo.getSelectedItem();
			label.setText(nome + " : "  + gestore.getIncassoFilm(nome) + " euro");
			}
			
		}
	}
	
	
	public boolean findTitolo(String tit) {
		boolean found = false;
		for (String current : titoli) {
			if (current.equalsIgnoreCase(tit))
				found = true;
		}
		return found;
	}
	

}