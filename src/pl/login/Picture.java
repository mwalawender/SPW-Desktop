package pl.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.admin.gui.AdministratorWindow;
import pl.employee.gui.EmployeeWindow;
import pl.pmanager.PManagerWindow;
import pl.softwaredeveloper.gui.SoftwareDeveloperWindow;
import db.connection.AdministratorDBOperations;
import javax.swing.JComboBox;

public class Picture extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfEmail;
	private JTextField tfPassword;
	private JButton btnLogIntoSystem;
	private int result;

	String[] blabla = { "janekbaranek@o2.pl", "pzabrzeski@o2.pl",
			"mareksitarek@gmail.com", "manieczek@o2.pl", "fajnaaneczka@wp" };
	public static String login = null;

	public Picture() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);

		JLabel lblLogin = new JLabel("Email:");
		lblLogin.setBounds(43, 67, 46, 14);
		add(lblLogin);

		tfEmail = new JTextField();
		tfEmail.setBounds(100, 54, 153, 40);
		add(tfEmail);
		tfEmail.setColumns(10);
		tfEmail.setText("janekbaranek@o2.pl");

		JLabel lblHaso = new JLabel("Has\u0142o: ");
		lblHaso.setBounds(43, 115, 46, 14);
		add(lblHaso);

		tfPassword = new JTextField();
		tfPassword.setBounds(100, 102, 153, 40);
		add(tfPassword);
		tfPassword.setColumns(10);
		tfPassword.setText("12345");

		btnLogIntoSystem = new JButton("Zaloguj");
		btnLogIntoSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String email = tfEmail.getText().toString();
				String password = tfPassword.getText().toString();

				if (email.isEmpty() || password.isEmpty()) {
					showDialogWithNotFilledFieldsWarn();
				} else {
					AdministratorDBOperations dbOperations = new AdministratorDBOperations();
					try {
						result = dbOperations.validateUser(email, password);
						System.out.println("RESULT: " + result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					launchRightWindow(result);
				}
			}

			private void launchRightWindow(int mResult) {
				switch (mResult) {
				case 0:
					showDialogWithNoAccess();
					break;
				case 1:
					System.out.println("Admin");
					AdministratorWindow adminWindow = new AdministratorWindow();
					adminWindow.runWindow();
					break;
				case 2:
					System.out.println("Pracownik");
					EmployeeWindow employeeWindowWorker = new EmployeeWindow();
					employeeWindowWorker.runWindow();
					break;
				case 3:
					SoftwareDeveloperWindow employeeWindowSoftware = new SoftwareDeveloperWindow();
					employeeWindowSoftware.runWindow();
					System.out.println("Programista");
					break;
				case 4:
					PManagerWindow projectManagerWindowSoftware = new PManagerWindow();
					projectManagerWindowSoftware.runWindow();
					System.out.println("PM");
					break;
				default:
					System.out.println("Inny");
					EmployeeWindow defaultWindowWorker = new EmployeeWindow();
					defaultWindowWorker.runWindow();
					break;
				}
			}
		});
		btnLogIntoSystem.setBounds(100, 154, 103, 40);
		add(btnLogIntoSystem);

		final JComboBox comboBoxNanana = new JComboBox(blabla);
		comboBoxNanana.setBounds(100, 205, 153, 42);
		//add(comboBoxNanana);

		comboBoxNanana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int pickedIndex = comboBoxNanana.getSelectedIndex();
				switch (pickedIndex) {
				case 0:
					login = comboBoxNanana.getSelectedItem().toString();
					tfEmail.setText(login);
					break;
				case 1:
					login = comboBoxNanana.getSelectedItem().toString();
					tfEmail.setText(login);
					break;
				case 2:
					login = comboBoxNanana.getSelectedItem().toString();
					tfEmail.setText(login);
					break;
				case 3:
					login = comboBoxNanana.getSelectedItem().toString();
					tfEmail.setText(login);
					break;
				case 4:
					login = comboBoxNanana.getSelectedItem().toString();
					tfEmail.setText(login);
					break;
				default:
					break;
				}
			}
		});
	}

	private void showDialogWithNotFilledFieldsWarn() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Prosze wypelnic wszystkie pola!");
	}

	private void showDialogWithNoAccess() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,
				"Brak dostepu! Bledny login lub haslo!");
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
