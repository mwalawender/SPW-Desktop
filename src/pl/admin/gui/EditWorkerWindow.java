package pl.admin.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.connection.AdministratorDBOperations;

public class EditWorkerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public boolean isRun = false;

	private JTextField tfWorkerFirstName, tfWorkerLastName, tfWorkerPosition,
			tfWorkerSection, tfWorkerPhoneNumber, tfWorkerEmail,
			tfWorkerPassword;
	private JLabel lblNazwisko;
	private JLabel lblStanowisko;
	private JLabel lblNumerTel;
	private JLabel lblEmail;
	private JLabel lblHaso;
	private JTextArea textArea;
	private JComboBox<String> comboBoxColors;

	AdministratorDBOperations dBOperations;
	static EditWorkerWindow frame;
	String mWorkerFirstName, mWorkerLastName, mWorkerPosition, mWorkerColor,
			mWorkerSection, mWorkerPhoneNumber, mWorkerEmail, mWorkerPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new EditWorkerWindow();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditWorkerWindow() {
		setTitle("Edytuj u¿ytkownika");

		setBounds(100, 100, 521, 394);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblImie = new JLabel("Imie: ");
		lblImie.setBounds(10, 25, 52, 14);
		contentPane.add(lblImie);

		tfWorkerFirstName = new JTextField();
		tfWorkerFirstName.setBounds(92, 17, 171, 31);
		contentPane.add(tfWorkerFirstName);
		tfWorkerFirstName.setColumns(10);

		lblNazwisko = new JLabel("Nazwisko: ");
		lblNazwisko.setBounds(10, 61, 62, 14);
		contentPane.add(lblNazwisko);

		tfWorkerLastName = new JTextField();
		tfWorkerLastName.setBounds(92, 53, 172, 31);
		contentPane.add(tfWorkerLastName);
		tfWorkerLastName.setColumns(10);

		lblStanowisko = new JLabel("Stanowisko: ");
		lblStanowisko.setBounds(10, 95, 62, 14);
		contentPane.add(lblStanowisko);

		tfWorkerPosition = new JTextField();
		tfWorkerPosition.setBounds(92, 87, 171, 31);
		contentPane.add(tfWorkerPosition);
		tfWorkerPosition.setColumns(10);

		JLabel lblNewLabel = new JLabel("Dzial:");
		lblNewLabel.setBounds(10, 131, 46, 14);
		contentPane.add(lblNewLabel);

		tfWorkerSection = new JTextField();
		tfWorkerSection.setBounds(92, 123, 171, 31);
		contentPane.add(tfWorkerSection);
		tfWorkerSection.setColumns(10);

		lblNumerTel = new JLabel("Numer tel: ");
		lblNumerTel.setBounds(10, 165, 62, 14);
		contentPane.add(lblNumerTel);

		tfWorkerPhoneNumber = new JTextField();
		tfWorkerPhoneNumber.setBounds(92, 157, 172, 31);
		contentPane.add(tfWorkerPhoneNumber);
		tfWorkerPhoneNumber.setColumns(10);

		lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(10, 202, 46, 14);
		contentPane.add(lblEmail);

		tfWorkerEmail = new JTextField();
		tfWorkerEmail.setBounds(92, 194, 172, 31);
		contentPane.add(tfWorkerEmail);
		tfWorkerEmail.setColumns(10);

		lblHaso = new JLabel("Has\u0142o: ");
		lblHaso.setBounds(10, 240, 46, 14);
		contentPane.add(lblHaso);

		tfWorkerPassword = new JTextField();
		tfWorkerPassword.setBounds(92, 232, 171, 31);
		contentPane.add(tfWorkerPassword);
		tfWorkerPassword.setColumns(10);

		textArea = new JTextArea();
		textArea.setBounds(273, 17, 159, 246);
		contentPane.add(textArea);

		// textArea.

		JButton btnEditWorker = new JButton("Dodaj");
		btnEditWorker.setBounds(10, 303, 96, 42);
		contentPane.add(btnEditWorker);

		JButton btnCancel = new JButton("Anuluj");
		btnCancel.setBounds(109, 303, 89, 42);
		contentPane.add(btnCancel);

		JButton btnClearAllFields = new JButton("Czysc");
		btnClearAllFields.setBounds(200, 303, 89, 42);
		contentPane.add(btnClearAllFields);

		// textArea.

		loadWorkerDataAtBegin();

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.setVisible(false);
			}
		});

		btnClearAllFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearWorkerData();
			}
		});

		btnEditWorker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// editPickedWorkerFromDb();

				dBOperations = new AdministratorDBOperations();

				System.out.println("aaaaaa" + mWorkerFirstName);

				mWorkerFirstName = tfWorkerFirstName.getText();
				mWorkerLastName = tfWorkerLastName.getText();
				mWorkerPosition = tfWorkerPosition.getText();
				mWorkerSection = tfWorkerSection.getText();
				mWorkerPhoneNumber = tfWorkerPhoneNumber.getText();
				mWorkerEmail = tfWorkerEmail.getText();
				mWorkerPassword = tfWorkerPassword.getText();

				if (!mWorkerFirstName.isEmpty() && !mWorkerLastName.isEmpty()
						&& !mWorkerPosition.isEmpty()
						&& !mWorkerSection.isEmpty()
						&& !mWorkerPhoneNumber.isEmpty()
						&& !mWorkerEmail.isEmpty()
						&& !mWorkerPassword.isEmpty()) {

					dBOperations.updateUserInDataBase(
							AdministratorWindow.emailOfUserToDelete,
							tfWorkerFirstName, tfWorkerLastName,
							tfWorkerPosition, tfWorkerSection,
							tfWorkerPhoneNumber, tfWorkerEmail,
							tfWorkerPassword);
				} else {
					JOptionPane
							.showMessageDialog(EditWorkerWindow.this,
									"Coœ nie jest wype³nione, prosze wype³niæ wszystko!");
				}
			}
		});
	}

	private void loadWorkerDataAtBegin() {
		System.out.println("WCZYTUJE WYBRANEGO UZYTKOWNIKA!");
		System.out.println(AdministratorWindow.emailOfUserToDelete);
		dBOperations = new AdministratorDBOperations();
		dBOperations.getSingleUserWithSpecifiedEmailFromDataBase(
				AdministratorWindow.emailOfUserToDelete, tfWorkerFirstName,
				tfWorkerLastName, tfWorkerPosition, tfWorkerSection,
				tfWorkerPhoneNumber, tfWorkerEmail, tfWorkerPassword);
	}

	public void clearWorkerData() {
		mWorkerFirstName = "";
		mWorkerLastName = "";
		mWorkerPosition = "";
		mWorkerSection = "";
		mWorkerPhoneNumber = "";
		mWorkerEmail = "";
		mWorkerPassword = "";

		tfWorkerFirstName.setText(mWorkerFirstName);
		tfWorkerLastName.setText(mWorkerLastName);
		tfWorkerPosition.setText(mWorkerPosition);
		tfWorkerSection.setText(mWorkerSection);
		tfWorkerPhoneNumber.setText(mWorkerPhoneNumber);
		tfWorkerEmail.setText(mWorkerEmail);
		tfWorkerPassword.setText(mWorkerPassword);
	}

	public void editPickedWorkerFromDb() {
		mWorkerFirstName = tfWorkerFirstName.getText();
		mWorkerLastName = tfWorkerLastName.getText();
		mWorkerPosition = tfWorkerPosition.getText();
		mWorkerColor = comboBoxColors.getSelectedItem().toString();
		mWorkerSection = tfWorkerSection.getText();
		mWorkerPhoneNumber = tfWorkerPhoneNumber.getText();
		mWorkerEmail = tfWorkerEmail.getText();
		mWorkerPassword = tfWorkerPassword.getText().toString();

		if (mWorkerFirstName.isEmpty() || mWorkerLastName.isEmpty()
				|| mWorkerPosition.isEmpty() || mWorkerSection.isEmpty()
				|| mWorkerPhoneNumber.isEmpty() || mWorkerEmail.isEmpty()
				|| mWorkerPassword.isEmpty()) {
			JOptionPane.showMessageDialog(EditWorkerWindow.this,
					"Coœ nie jest wype³nione, prosze wype³niæ wszystko!");
		} else {

			dBOperations = new AdministratorDBOperations();
			try {
				dBOperations.insertNewWorkerIntoDataBase(mWorkerFirstName,
						mWorkerLastName, mWorkerPosition, mWorkerSection,
						mWorkerPhoneNumber, mWorkerEmail, mWorkerPassword);
				System.out.println("Dodano nowego pracownika do bazy!");
				textArea.append("Dodano nowego u¿ytkownika do bazy!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
