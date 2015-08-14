package pl.ftp.operations;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class FrameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int ilePlk = 0;
	private JPanel contentPane; // kontener
	public boolean isRun = false;
	static JTextArea textINFO;
	public static JTextArea textArea;
	private static final String IMG_PATH = "src/image/image.jpg";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // kolejka
					public void run() {
						try {
							FrameMain frame = new FrameMain();
							frame.setVisible(true); // stworzenie granic obszaru
						} catch (Exception e) {
							e.printStackTrace(); // obs³uga b³êdu
						}
					}
				});
	}

	/**
	 * Create the frame.
	 */
	public FrameMain() throws FileNotFoundException {
		setTitle("Obs³uga Plików"); // tytu³
									// UI
		setBounds(100, 100, 913, 710); // ca³y obszar
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		// stanu

		// ******DOWNLOAD ORDER
		JButton btnDownload = new JButton("Pobierz info");
		btnDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AdDownload ordDownload = new AdDownload();
				try {
					// ordDownload.numberFiles();
					ordDownload.download();
					System.out.println("wywolanie download");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// ******DOWNLOAD ORDER KONIEC

		// OBROBKA
		JButton btnObrobka = new JButton("Obrobka");
		btnObrobka.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObrobkaFrame frame = new ObrobkaFrame();
				frame.setVisible(true);
			}
		});

		// OBROBKA KONIEC

		// Przegladanie
		JButton btnView = new JButton("Przegl\u0105daj");
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ViewFrame frame;
				try {
					frame = new ViewFrame();
					frame.setVisible(true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// frame.setVisible(true);
			}
		});
		// Przegladanie KONIEC

		// odlewanie
		JButton btnOdlewnictwo = new JButton("Odlewnictwo");
		btnOdlewnictwo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OdlewanieFrame frame = new OdlewanieFrame();
				frame.setVisible(true);
			}
		});

		// Odlewanie KONIEC

		// ******CZYSZCZENIE PLIKU ZAMÓWIENIA
		JButton btnClearOrder = new JButton("Nowe zapytanie");
		btnClearOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Order order = new Order();
				try {
					order.ClearTxtOrder("zamowienie");
					Main.numer = 0; // dodano do czyszczenia numeru ogloszenia
					Main.max = 0;
					Main.file_max = 0;

					// CZYSZCZENIE FRAME
					textINFO.setText("");

					textArea.setText("");

					// CZYSZCZENIE FRAME KONIEC

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		textINFO = new JTextArea();
		// textKartaOpisuProc.setText("0");
		textINFO.setBounds(37, 212, 165, 21);
		contentPane.add(textINFO);
		textINFO.setColumns(10);

		textArea = new JTextArea();
		textArea.setLineWrap(true);

		JScrollPane sbrText = new JScrollPane(textArea);
		sbrText.setBounds(264, 77, 610, 575);

		getContentPane().add(sbrText, BorderLayout.CENTER);

		textArea.setBounds(417, 40, 651, 668);
		textArea.setEditable(false);

		btnClearOrder.setBounds(37, 268, 139, 49);
		contentPane.add(btnClearOrder);

		btnDownload.setBounds(37, 328, 139, 49);
		contentPane.add(btnDownload);

		btnObrobka.setBounds(37, 77, 139, 49);
		contentPane.add(btnObrobka);

		btnOdlewnictwo.setBounds(37, 137, 139, 49);
		contentPane.add(btnOdlewnictwo);

		JLabel lblWybierzRodzaj = new JLabel("Wybierz rodzaj");
		lblWybierzRodzaj.setBounds(37, 40, 112, 14);
		contentPane.add(lblWybierzRodzaj);

		btnView.setBounds(37, 388, 139, 49);
		contentPane.add(btnView);

		JButton btnImage = new JButton("Image");
		btnImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					BufferedImage img = ImageIO.read(new File(IMG_PATH));
					ImageIcon icon = new ImageIcon(img);
					JLabel label = new JLabel(icon);
					JOptionPane.showMessageDialog(null, label);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnImage.setBounds(37, 448, 139, 49);
		contentPane.add(btnImage);

		JButton btnChart = new JButton("Chart");

		contentPane.add(btnChart);

	}
}
