package pl.admin.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.connection.AdministratorDBOperations;

public class EditProjectWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public boolean isRun = false;

	private JTextField tfProjectName, tfProjectStartTimeDate,
			tfProjectEndTimeDate;
	private JLabel lblNazwisko;
	private JLabel lblStanowisko;

	private JTextArea taProjectDescription;

	AdministratorDBOperations dBOperations;
	static EditProjectWindow frame;
	String mProjectName, mProjectDescription, mProjectStartTimeDate,
			mProjectEndTimeDate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new EditProjectWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditProjectWindow() {
		setTitle("Dodaj nowy projekt");

		setBounds(100, 100, 355, 481);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblImie = new JLabel("Nazwa: ");
		lblImie.setBounds(10, 25, 52, 14);
		contentPane.add(lblImie);

		tfProjectName = new JTextField();
		tfProjectName.setBounds(92, 17, 171, 31);
		contentPane.add(tfProjectName);
		tfProjectName.setColumns(10);

		lblNazwisko = new JLabel("Data rozp:");
		lblNazwisko.setBounds(10, 61, 62, 14);
		contentPane.add(lblNazwisko);

		tfProjectStartTimeDate = new JTextField();
		tfProjectStartTimeDate.setBounds(92, 53, 172, 31);
		contentPane.add(tfProjectStartTimeDate);
		tfProjectStartTimeDate.setColumns(10);

		lblStanowisko = new JLabel("Data zak:");
		lblStanowisko.setBounds(10, 95, 72, 14);
		contentPane.add(lblStanowisko);

		tfProjectEndTimeDate = new JTextField();
		tfProjectEndTimeDate.setBounds(92, 87, 171, 31);
		contentPane.add(tfProjectEndTimeDate);
		tfProjectEndTimeDate.setColumns(10);

		taProjectDescription = new JTextArea();
		taProjectDescription.setBounds(92, 129, 171, 246);
		taProjectDescription.setLineWrap(true);
		contentPane.add(taProjectDescription);

		// textArea.

		JButton btnProject = new JButton("Edytuj");
		btnProject.setBounds(10, 389, 96, 42);
		contentPane.add(btnProject);

		JButton btnCancel = new JButton("Anuluj");
		btnCancel.setBounds(116, 389, 89, 42);
		contentPane.add(btnCancel);

		JButton btnClearAllFields = new JButton("Czysc");
		btnClearAllFields.setBounds(211, 389, 89, 42);
		contentPane.add(btnClearAllFields);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(10, 129, 46, 14);
		contentPane.add(lblOpis);

		loadProjectDataAtBegin();

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

		btnProject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				editPickedWorkerFromDb();

			}
		});
	}

	public void clearWorkerData() {
		mProjectName = "";
		mProjectStartTimeDate = "";
		mProjectDescription = "";
		mProjectEndTimeDate = "";

		tfProjectName.setText(mProjectName);
		taProjectDescription.setText(mProjectDescription);
		tfProjectStartTimeDate.setText(mProjectStartTimeDate);
		tfProjectEndTimeDate.setText(mProjectDescription);

	}

	private void loadProjectDataAtBegin() {
		System.out.println("WCZYTUJE WYBRANY PROJEKT!");
		System.out.println(AdministratorWindow.projectIdToDelete);
		dBOperations = new AdministratorDBOperations();
		dBOperations.getSingleProjectWithSpecifiedIdNumberFromDataBase(
				AdministratorWindow.projectIdToDelete, tfProjectName,
				taProjectDescription, tfProjectStartTimeDate,
				tfProjectEndTimeDate);

	}

	public void editPickedWorkerFromDb() {
		mProjectName = tfProjectName.getText();
		mProjectDescription = taProjectDescription.getText();
		mProjectStartTimeDate = tfProjectStartTimeDate.getText();
		mProjectEndTimeDate = tfProjectEndTimeDate.getText();

		if (mProjectName.isEmpty() || mProjectDescription.isEmpty()
				|| mProjectStartTimeDate.isEmpty()
				|| mProjectEndTimeDate.isEmpty()) {
			JOptionPane.showMessageDialog(EditProjectWindow.this,
					"Coœ nie jest wype³nione, prosze wype³niæ wszystko!");
		} else {
			dBOperations = new AdministratorDBOperations();
			try {
				dBOperations
						.updateProjectInDataBase(
								Integer.parseInt(AdministratorWindow.projectIdToDelete),
								tfProjectName, taProjectDescription,
								tfProjectStartTimeDate, tfProjectEndTimeDate);
				System.out.println("Zmieniono projekt w bazie!");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
