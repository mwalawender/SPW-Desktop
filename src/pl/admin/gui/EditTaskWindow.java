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

public class EditTaskWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public boolean isRun = false;

	private JTextField tfTaskName, tfTaskStartTimeDate, tfTaskEndTimeDate;
	private JLabel lblNazwisko;
	private JLabel lblStanowisko;

	private JTextArea taTaskDescription;

	AdministratorDBOperations dBOperations;
	static EditTaskWindow frame;
	String mTaskName, mTaskDescription, mTaskStartTimeDate, mTaskEndTimeDate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new EditTaskWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditTaskWindow() {
		setTitle("Dodaj nowe zadanie");

		setBounds(100, 100, 355, 481);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		dBOperations = new AdministratorDBOperations();

		JLabel lblImie = new JLabel("Nazwa: ");
		lblImie.setBounds(10, 25, 52, 14);
		contentPane.add(lblImie);

		tfTaskName = new JTextField();
		tfTaskName.setBounds(92, 17, 171, 31);
		contentPane.add(tfTaskName);
		tfTaskName.setColumns(10);

		lblNazwisko = new JLabel("Data rozp:");
		lblNazwisko.setBounds(10, 61, 62, 14);
		contentPane.add(lblNazwisko);

		tfTaskStartTimeDate = new JTextField();
		tfTaskStartTimeDate.setBounds(92, 53, 172, 31);
		contentPane.add(tfTaskStartTimeDate);
		tfTaskStartTimeDate.setColumns(10);

		lblStanowisko = new JLabel("Data zak:");
		lblStanowisko.setBounds(10, 95, 72, 14);
		contentPane.add(lblStanowisko);

		tfTaskEndTimeDate = new JTextField();
		tfTaskEndTimeDate.setBounds(92, 87, 171, 31);
		contentPane.add(tfTaskEndTimeDate);
		tfTaskEndTimeDate.setColumns(10);

		taTaskDescription = new JTextArea();
		taTaskDescription.setBounds(92, 129, 171, 246);
		taTaskDescription.setLineWrap(true);
		contentPane.add(taTaskDescription);

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

		loadTaskDataAtBegin();

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

				editPickedTaskFromDb();

			}
		});
	}

	public void clearWorkerData() {
		mTaskName = "";
		mTaskStartTimeDate = "";
		mTaskDescription = "";
		mTaskEndTimeDate = "";

		tfTaskName.setText(mTaskName);
		taTaskDescription.setText(mTaskDescription);
		tfTaskStartTimeDate.setText(mTaskStartTimeDate);
		tfTaskEndTimeDate.setText(mTaskDescription);

	}

	private void loadTaskDataAtBegin() {
		System.out.println("WCZYTUJE WYBRANY TASK!");
		System.out.println(AdministratorWindow.projectIdToDelete);

		dBOperations.getSingleTaskWithSpecifiedIdNumberFromDataBase(
				AdministratorWindow.taskIdToDelete, tfTaskName,
				taTaskDescription, tfTaskStartTimeDate, tfTaskEndTimeDate);
	}

	public void editPickedTaskFromDb() {
		mTaskName = tfTaskName.getText();
		mTaskDescription = taTaskDescription.getText();
		mTaskStartTimeDate = tfTaskStartTimeDate.getText();
		mTaskEndTimeDate = tfTaskEndTimeDate.getText();

		if (mTaskName.isEmpty() || mTaskDescription.isEmpty()
				|| mTaskStartTimeDate.isEmpty() || mTaskEndTimeDate.isEmpty()) {
			JOptionPane.showMessageDialog(EditTaskWindow.this,
					"Coœ nie jest wype³nione, prosze wype³niæ wszystko!");
		} else {

			try {
				dBOperations.updateTaskInDataBase(
						Integer.parseInt(AdministratorWindow.taskIdToDelete),
						tfTaskName, taTaskDescription, tfTaskStartTimeDate,
						tfTaskEndTimeDate);
				System.out.println("Zmieniono task w bazie!");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
