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

public class ObrobkaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String info;
	public String puste = " ";
	public String skrawanie = "skrawaniem";
	public String plastyczna = "plastyczna ";
	public String cieplna = "cieplna ";
	public String chemicznaMetali = "chemiczna metali ";
	public String cieplnoChemiczna = "cieplno-chemiczna ";
	final JComboBox cbMain;
	final JComboBox cbSub;
	private JComboBox cbType;
	private JTextField textTyp;
	private JTextField textNazwa;
	private JTextField textDodatkowe;
	private static int ilePlk = 0 ;
	String nazwa = "zamowienie";
	private JLabel lblLokalizacja;
	private JTextField textLokalizacja;
	private JTextField textBrak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObrobkaFrame frame = new ObrobkaFrame();
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
	public ObrobkaFrame() {

		// ******SPRAWDZENIE
		JButton btnSprawdz = new JButton("Sprawdz");
		btnSprawdz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				
				if(cbMain.getSelectedIndex() == 0){
					System.out.println("BRAK DANYCH");
					textBrak.setText("BRAK DANYCH");
				}else{
				
				
				Order zamowienie = new Order();
				info = cbMain.getSelectedItem().toString();
				zamowienie.zapisywanieTekstu2(info, "zamowienie");
				
				info = "";
				
				info = cbSub.getSelectedItem().toString();
				
			
				zamowienie.zapisywanieTekstu2(info, "zamowienie");
				zamowienie.zapisywanieTekstu(textTyp, "zamowienie");
				zamowienie.zapisywanieTekstu(textNazwa, "zamowienie");
				zamowienie.zapisywanieTekstu(textDodatkowe, "zamowienie");
				zamowienie.zapisywanieTekstu(textLokalizacja, "zamowienie");
				
		
				
				Search search = new Search();
				search.szukanie();
				
				
				
				ObrobkaFrame.this.dispose();
				}//koniec else
				
				
				
			
			}
		});

		// ******SPRAWDZENIE KONIEC

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		btnSprawdz.setBounds(37, 211, 89, 23);
		contentPane.add(btnSprawdz);
		
		String[] types = { " (brak) ","skrawaniem","plastyczna","cieplna","chemiczna","cieplno - chemiczna" };
		
	cbMain = new JComboBox(types);
		cbMain.setBounds(59, 52, 167, 23);
		contentPane.add(cbMain);
		
		cbSub = new JComboBox();
		cbSub.setBounds(59, 104, 167, 23);
		contentPane.add(cbSub);
		
		textTyp = new JTextField();
		textTyp.setBounds(294, 53, 86, 20);
		contentPane.add(textTyp);
		textTyp.setColumns(10);
		
		JLabel lblTyp = new JLabel("Typ");
		lblTyp.setBounds(293, 28, 46, 14);
		contentPane.add(lblTyp);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(293, 84, 46, 14);
		contentPane.add(lblNazwa);
		
		textNazwa = new JTextField();
		textNazwa.setBounds(294, 105, 86, 20);
		contentPane.add(textNazwa);
		textNazwa.setColumns(10);
		
		textDodatkowe = new JTextField();
		textDodatkowe.setBounds(294, 212, 86, 20);
		contentPane.add(textDodatkowe);
		textDodatkowe.setColumns(10);
		
		JLabel lblDodatkowe = new JLabel("Dodatkowe");
		lblDodatkowe.setBounds(294, 187, 87, 14);
		contentPane.add(lblDodatkowe);
		
		lblLokalizacja = new JLabel("Lokalizacja");
		lblLokalizacja.setBounds(293, 138, 87, 14);
		contentPane.add(lblLokalizacja);
		
		textLokalizacja = new JTextField();
		textLokalizacja.setBounds(294, 156, 86, 20);
		contentPane.add(textLokalizacja);
		textLokalizacja.setColumns(10);
		
		textBrak = new JTextField();
		textBrak.setEditable(false);
		textBrak.setBounds(158, 212, 114, 20);
		contentPane.add(textBrak);
		textBrak.setColumns(10);

		// choice.enable();

		
		
		//akcje:
		cbMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//czyszczenie:
				cbSub.removeAllItems();
				//opcje:
				//raz
				if (cbMain.getSelectedItem().toString() == "skrawaniem") {
					cbSub.addItem("obrobka wiórowa");
					cbSub.addItem("obrobka œcierna");
				} 
				//dwa
				else if (cbMain.getSelectedItem().toString() == "plastyczna") {
					cbSub.addItem("walcowanie");
					cbSub.addItem("wyciskanie");
					cbSub.addItem("ci¹gnienie");
					cbSub.addItem("kucie i prasowanie");
					cbSub.addItem("t³oczenie");
				} 
				//trzy
				else if (cbMain.getSelectedItem().toString() == "cieplna") {
					cbSub.addItem("zwyk³a");
					cbSub.addItem("plastyczna");
					cbSub.addItem("cieplno - chemiczna");
					cbSub.addItem("magnetyczna");
				} 
				//cztery
				else if (cbMain.getSelectedItem().toString() == "chemiczna") {
					cbSub.addItem("nawêglanie");
					cbSub.addItem("wêgloazotowanie");
					cbSub.addItem("metalizowanie");
				} 
				//piec
				else if (cbMain.getSelectedItem().toString() == "cieplno - chemiczna") {
					cbSub.addItem("aluminiowanie");
					cbSub.addItem("azotowanie");
					cbSub.addItem("naborowywanie");
					cbSub.addItem("chromowanie");
					cbSub.addItem("nawêglanie");
					cbSub.addItem("siarkowanie");
					cbSub.addItem("wêgloazotowanie");
				} else {
					cbSub.addItem("brak");
				}
				
			}
		});
		
	
	}
}
