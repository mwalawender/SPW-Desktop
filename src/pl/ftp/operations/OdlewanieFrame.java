package pl.ftp.operations;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OdlewanieFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
public String info;
private JTextField textWsad;
private JTextField textNazwa;
private JTextField textPrzemysl;
private JTextField textDodatkowe;
private JTextField textLokalizacja;
private JTextField textBrak;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OdlewanieFrame frame = new OdlewanieFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OdlewanieFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		String[] types = { " (brak) ","kokilowe","pod ciœnieniem","do form wiruj¹cych","w masach szybkowi¹¿¹cych" , "w ciek³ych masach samoutwardzalnych", "w formy skorupowe", "form z modeli wytapialnych" };
		final JComboBox cbOdlewanie = new JComboBox(types);
		cbOdlewanie.setBounds(37, 32, 248, 20);
		contentPane.add(cbOdlewanie);
		
		JLabel lblRodzajOdlewania = new JLabel("Rodzaj odlewania");
		lblRodzajOdlewania.setBounds(37, 11, 116, 14);
		contentPane.add(lblRodzajOdlewania);
		
		JButton btnSprawdz = new JButton("Sprawdz");
		btnSprawdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(cbOdlewanie.getSelectedIndex() == 0){
					System.out.println("BRAK DANYCH");
					textBrak.setText("BRAK DANYCH");
				}else{
				
				
				Order zamowienie = new Order();
				info = cbOdlewanie.getSelectedItem().toString();
				zamowienie.zapisywanieTekstu2(info, "zamowienie");
				
				zamowienie.zapisywanieTekstu(textWsad, "zamowienie");
				zamowienie.zapisywanieTekstu(textPrzemysl, "zamowienie");
				zamowienie.zapisywanieTekstu(textNazwa, "zamowienie");
				zamowienie.zapisywanieTekstu(textDodatkowe, "zamowienie");
				zamowienie.zapisywanieTekstu(textLokalizacja, "zamowienie");
				
				Search search = new Search();
				search.szukanie(); 
				
				
				
				OdlewanieFrame.this.dispose();
				}//koniec else
			}
		});
		
		btnSprawdz.setBounds(37, 278, 89, 23);
		contentPane.add(btnSprawdz);
		
		JLabel lblWsad = new JLabel("Wsad");
		lblWsad.setBounds(333, 35, 46, 14);
		contentPane.add(lblWsad);
		
		textWsad = new JTextField();
		textWsad.setBounds(333, 60, 86, 20);
		contentPane.add(textWsad);
		textWsad.setColumns(10);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(333, 91, 46, 14);
		contentPane.add(lblNazwa);
		
		textNazwa = new JTextField();
		textNazwa.setBounds(333, 116, 86, 20);
		contentPane.add(textNazwa);
		textNazwa.setColumns(10);
		
		JLabel lblPrzemysDocelowy = new JLabel("Przemys\u0142 docelowy");
		lblPrzemysDocelowy.setBounds(333, 153, 151, 14);
		contentPane.add(lblPrzemysDocelowy);
		
		textPrzemysl = new JTextField();
		textPrzemysl.setBounds(333, 178, 86, 20);
		contentPane.add(textPrzemysl);
		textPrzemysl.setColumns(10);
		
		JLabel lblDodatkowe = new JLabel("Dodatkowe");
		lblDodatkowe.setBounds(333, 266, 86, 14);
		contentPane.add(lblDodatkowe);
		
		textDodatkowe = new JTextField();
		textDodatkowe.setBounds(333, 291, 86, 20);
		contentPane.add(textDodatkowe);
		textDodatkowe.setColumns(10);
		
		JLabel lblLokalizacja = new JLabel("Lokalizacja");
		lblLokalizacja.setBounds(333, 207, 98, 14);
		contentPane.add(lblLokalizacja);
		
		textLokalizacja = new JTextField();
		textLokalizacja.setBounds(333, 232, 86, 20);
		contentPane.add(textLokalizacja);
		textLokalizacja.setColumns(10);
		
		textBrak = new JTextField();
		textBrak.setEditable(false);
		textBrak.setBounds(136, 279, 116, 20);
		contentPane.add(textBrak);
		textBrak.setColumns(10);
		
	}
}
