package pl.ftp.operations;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JPasswordField passwordField;
	
	static int two =2;
	public String password, mail ;

	
	ArrayList<String> lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	//*****
public void sprawdz2( ArrayList<String> kolekcja) {

		

		
	
			for (int j = 0; j < kolekcja.size(); j++) {
				try {

					

					BufferedReader bf = new BufferedReader(new FileReader("Uzytkownicy.txt"));

					
					int linecount = 0;

					String line;

					
					System.out.println("Przeszukiwanie " + kolekcja.get(j));

					// ******************************************************************

					Pattern p = Pattern.compile(
							kolekcja.get(j),
							Pattern.CASE_INSENSITIVE);
					
					while ((line = bf.readLine()) != null) {
						linecount++;

						Matcher m = p.matcher(line);
					
						while (m.find()) {

							System.out.println("Slowo \"" + kolekcja.get(j)
									+ " \"znalezione na pozycji " + m.start()
									+ " w lini " + linecount);

							
							two++;
							

						}
					}

					// ******************************************************************
					System.out.println("ZGADZA SIÊ" + two);
					bf.close();
				} catch (IOException e) {
					System.out.println("IO Error Occurred: " + e.toString());
				}
			}
		}
	
	
	//****
	
	
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lista = new ArrayList<String>();
		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		// ******SPRAWDZENIE
				JButton btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent arg0) {

						lista.clear();
						two = 0;
						password = passwordField.getText();
						mail = textEmail.getText();
						
						System.out.println(password);
						
						
						
						lista.add(mail+password);
						//lista.add(password);
						
						
						System.out.println(lista);
						
						sprawdz2(lista);
						
						if(two==1){
							System.out.println("Has³o prawodlowe");
							FrameMain frame;
							try {
								frame = new FrameMain();
								frame.setVisible(true);
								LoginFrame.this.dispose();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else{
							System.out.println("Has³o nieprawid³owe");
							textArea.setText("Niepoprawne dane");
						}
						
						
						
						
					
					}
				});

				// ******SPRAWDZENIE KONIEC
		
		
		
		
		
		
		
		
		textEmail = new JTextField();
		textEmail.setBounds(199, 85, 140, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(92, 88, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(92, 162, 84, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 159, 140, 20);
		contentPane.add(passwordField);
		
		
		btnLogin.setBounds(160, 228, 89, 23);
		contentPane.add(btnLogin);
		
		
		textArea.setBounds(50, 31, 114, 16);
		contentPane.add(textArea);
	}
}
