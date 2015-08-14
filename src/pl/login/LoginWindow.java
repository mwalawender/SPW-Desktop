package pl.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Michaœ TODO:
 * 
 *         opracowac gui dla wszystkich rodzajow pracownikow crawler cloud
 *         operacje w bazie danych: administrator operacje na pracownikach,
 *         projektach, taskach pracownik - crawler cloud, wczytywanie obrazkow,
 *         konwersja pdfow na obrazki, przeszukiwanie pdfow, tekst, ocr ? pm -
 *         do przemyslenia...
 * 
 *         zrobione -> edytowanie usera zgarnianie pojedynczego usera z db
 *         wczytywanie obrazka
 * 
 * 
 */

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public LoginWindow() {
		// TODO Auto-generated constructor stub
		setTitle("Logowanie do systemu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		Picture picture = new Picture();
		getContentPane().add(picture);

		final UIManager.LookAndFeelInfo[] plafInfos = UIManager
				.getInstalledLookAndFeels();
		String[] plafNames = new String[plafInfos.length];
		for (int ii = 0; ii < plafInfos.length; ii++) {
			plafNames[ii] = plafInfos[ii].getName();
		}
		try {
			UIManager.setLookAndFeel(plafInfos[1].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
			pack();
			setMinimumSize(this.getSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pack();
		setVisible(true);
	}

}
