package pl.ftp.operations;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ViewFrame extends JFrame {

	private JPanel contentPane;
	public static JTextArea textArea;
	public int ilosc = 0;
	static int liczbaS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFrame frame = new ViewFrame();
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
	public ViewFrame() throws FileNotFoundException{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 841, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//*****
		textArea = new JTextArea();
		textArea.setLineWrap(true);

		JScrollPane sbrText = new JScrollPane(textArea);
		sbrText.setBounds(10, 11, 610, 575);
		
		getContentPane().add(sbrText, BorderLayout.CENTER);

		

		
		textArea.setBounds(417, 40, 651, 668);
		textArea.setEditable(false);
		//***
		
		
		//***
		BufferedReader plikDoWyswietlenia = new BufferedReader(new FileReader(
				"OgloszenieNr1.txt"));
		
			try {
				textArea.read(plikDoWyswietlenia, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//***

		
		final JTextArea textArea_1 = new JTextArea();
		
		
				//Ile plików
				IlePlikow ile = new IlePlikow();
				ilosc = ile.liczIlePlikow();
				liczbaS = ile.liczIlePlikow();;
				 //final BufferedReader plikDoWyswietlenia = null;
				ilosc = ilosc - (liczbaS-1);
				
				
				
				
				final JButton btnPoprzednie = new JButton("Poprzednie");
				final JButton btnNastpne = new JButton("Nast\u0119pne");
				btnNastpne.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						btnPoprzednie.setVisible(true);
						
						try {
							BufferedReader plikDoWyswietlenia = new BufferedReader(new FileReader(
									"OgloszenieNr" + ilosc + ".txt"));
							try {
								textArea.read(plikDoWyswietlenia, null);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
				
						if(ilosc <= liczbaS-1 ){
						ilosc++;
							
						
						
						}else{
							
							
							//btnNastpne.setVisible(false);
							//if(ilosc == liczbaS){btnNastpne.setVisible(false);}
							//ilosc--;
						}
						
						textArea_1.setText(ilosc+"/"+liczbaS);
						if(ilosc == liczbaS){btnNastpne.setVisible(false);}
					}
				});
				btnNastpne.setBounds(668, 11, 120, 50);
				contentPane.add(btnNastpne);
				
				
				btnPoprzednie.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						btnNastpne.setVisible(true);
						
						try {
							BufferedReader plikDoWyswietlenia = new BufferedReader(new FileReader(
									"OgloszenieNr" + ilosc + ".txt"));
							try {
								textArea.read(plikDoWyswietlenia, null);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				
						if(ilosc > 0 ){
						ilosc--;
						
						
						
						}else{
							//btnPoprzednie.setVisible(false);
							ilosc++;
						}
						textArea_1.setText(ilosc+"/"+liczbaS);
						if(ilosc == 1){btnPoprzednie.setVisible(false);}
					}
				});
				btnPoprzednie.setBounds(668, 85, 120, 50);
				contentPane.add(btnPoprzednie);
				
				
				textArea_1.setBounds(668, 159, 120, 16);
				contentPane.add(textArea_1);
		
		
		
	}
}
