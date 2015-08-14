package pl.employee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pl.admin.gui.AddProjectWindow;
import pl.admin.gui.AddTaskWindow;
import pl.admin.gui.EditProjectWindow;
import pl.admin.gui.EditTaskWindow;
import pl.admin.gui.WorkerDetailsWindow;
import pl.chat.ChatUI;
import pl.crawler.CrawlerWindow;
import pl.ftp.operations.FrameMain;
import pl.gantt.chart.MyGanttChart;
import pl.pdf.to.txt.converter.ConverterWindow;
import pl.project.Employee;
import pl.project.Project;
import pl.project.Task;
import db.connection.AdministratorDBOperations;

/**
 * 
 * @author Michaœ TODO: edit, add, new project, new task, tabele krzyzowe, to
 *         samo z taskami... gantt + chat prowizorka, pdf zrobiony, crawler niby
 *         te¿ apka mobilna bleh
 * 
 */

public class EmployeeWindow {

	AdministratorDBOperations dBo;
	DefaultTableModel workersModel, projectsModel, tasksModel;
	String[] workersHeader = { "Imie", "Nazwisko", "Stanowisko", "Dzial",
			"Email", "Telefon" };
	String[] projectsHeader = { "ID", "Nazwa", "Opis", "Data start", "Data End" };
	String[] tasksHeader = { "ID", "Nazwa", "Opis", "Data start", "Data End" };
	String[] displayOptions = { "Pracownicy", "Projekty", "Zadania"

	};
	static String emailOfUserToDelete = null, taskIdToDelete = null,
			projectIdToDelete = null;
	JButton addNewWorkerButton, deleteWorkerButton, editWorkerButton,
			addNewProjectButton, deleteProjectButton, editProjectButton,
			addNewTaskButton, deleteTaskButton, editTaskButton;

	JButton btnStartChat, addCrawler, loadProjectImage, convertPDFtoTxtBtn,
			ganttChart, pobierzDaneZFTP;

	JTable workersTable, projectsTable, tasksTable;
	BufferedImage bi;
	JPanel imagePanel, userOptionsPanel, projectOptionsPanel, taskOptionsPanel,
			dbButtonsPanel, extraFeatures, mainPanel;
	JSplitPane mainPanel2;
	JFrame frame;

	JScrollPane workersTableScroll, projectsTableScroll, tasksTableScroll;

	int flag = 0;

	public void runWindow() {

		Runnable r = new Runnable() {

			private JPanel imagePanel2;
			private JButton projectDetails;
			private JButton taskDetails;

			public void run() {

				frame = new JFrame("Panel Pracownika");
				frame.getContentPane()
						.setPreferredSize(new Dimension(700, 700));

				dBo = new AdministratorDBOperations();
				ustawSkorkeNaPoczatku();

				final JPanel gui = new JPanel(new BorderLayout(5, 5));
				gui.setBorder(new TitledBorder("Lista zadañ"));

				initializeJPanels();
				initializeButtons();

				gui.add(dbButtonsPanel, BorderLayout.NORTH);
				gui.add(extraFeatures, BorderLayout.WEST);

				addElementsToExtraFeaturesPanel();

				createTablesAndDataModels();
				addTableListeners();
				prepareTables();

				imagePanel = new JPanel(new GridBagLayout());
				imagePanel.setBorder(new TitledBorder(
						"Podgl¹d graficzny projektu"));

				mainPanel.add(projectsTableScroll);
				mainPanel.add(tasksTableScroll);
				mainPanel.add(workersTableScroll);

				mainPanel2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
						imagePanel, mainPanel);

				mainPanel2.setOneTouchExpandable(true);
				mainPanel2.setResizeWeight(0.8);

				mainPanel2.setBackground(Color.BLUE);

				// gui.add(mainPanel);
				gui.add(mainPanel2);

				frame.setContentPane(gui);

				frame.pack();

				frame.setLocationRelativeTo(null);
				try {
					// 1.6+
					frame.setLocationByPlatform(true);
					frame.setMinimumSize(new Dimension(900, 1200));
				} catch (Throwable ignoreAndContinue) {
				}
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}

			private void addElementsToExtraFeaturesPanel() {
				btnStartChat = new JButton("Live Chat");
				addCrawler = new JButton("Crawler");
				loadProjectImage = new JButton("Wczytaj obrazek");
				convertPDFtoTxtBtn = new JButton("PDF to txt");
				ganttChart = new JButton("Wykresy Gantta");
				pobierzDaneZFTP = new JButton("Pobierz dane z FTP");

				extraFeatures.add(btnStartChat);
				extraFeatures.add(addCrawler);
				extraFeatures.add(loadProjectImage);
				extraFeatures.add(convertPDFtoTxtBtn);
				extraFeatures.add(ganttChart);
				extraFeatures.add(pobierzDaneZFTP);

				btnStartChat.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						ChatUI chat = new ChatUI();
					}

				});

				addCrawler.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						CrawlerWindow crawlerWindow = new CrawlerWindow();
						crawlerWindow.setVisible(true);

					}
				});

				convertPDFtoTxtBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						ConverterWindow convertWindow = new ConverterWindow();

					}
				});

				ganttChart.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						MyGanttChart rysujGantta = new MyGanttChart();
						rysujGantta.generateGantt();
					}
				});

				pobierzDaneZFTP.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						FrameMain frame;
						try {
							frame = new FrameMain();
							frame.setVisible(true);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});

				loadProjectImage.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						loadImageFromFile();
					}

				});
			}

			private void prepareTables() {
				workersTableScroll = new JScrollPane(workersTable);
				projectsTableScroll = new JScrollPane(projectsTable);
				tasksTableScroll = new JScrollPane(tasksTable);

				workersTableScroll.setBorder(new TitledBorder(
						"Lista pracowników"));
				projectsTableScroll.setBorder(new TitledBorder(
						"Lista projektów"));
				tasksTableScroll.setBorder(new TitledBorder("Lista zadañ"));

				Dimension tablePreferred = workersTableScroll
						.getPreferredSize();
				workersTableScroll.setPreferredSize(new Dimension(
						tablePreferred.width, tablePreferred.height / 2));
				projectsTableScroll.setPreferredSize(new Dimension(
						tablePreferred.width, tablePreferred.height / 2));
				tasksTableScroll.setPreferredSize(new Dimension(
						tablePreferred.width, tablePreferred.height / 2));

			}

			private void addTableListeners() {
				workersTable
						.addMouseListener(new java.awt.event.MouseAdapter() {
							@Override
							public void mouseClicked(
									java.awt.event.MouseEvent evt) {
								int row = workersTable.rowAtPoint(evt
										.getPoint());
								int col = workersTable.columnAtPoint(evt
										.getPoint());
								if (row >= 0 && col >= 0) {
									System.out.println(row + " " + col);
								}
							}
						});

				projectsTable
						.addMouseListener(new java.awt.event.MouseAdapter() {
							@Override
							public void mouseClicked(
									java.awt.event.MouseEvent evt) {
								int row = projectsTable.rowAtPoint(evt
										.getPoint());
								int col = projectsTable.columnAtPoint(evt
										.getPoint());
								if (row >= 0 && col >= 0) {
									System.out.println(row + " " + col);
								}
							}
						});

				tasksTable.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int row = tasksTable.rowAtPoint(evt.getPoint());
						int col = tasksTable.columnAtPoint(evt.getPoint());
						if (row >= 0 && col >= 0) {
							System.out.println(row + " " + col);
						}
					}
				});

			}

			private void createTablesAndDataModels() {
				workersModel = new DefaultTableModel(workersHeader, 0);

				projectsModel = new DefaultTableModel(projectsHeader, 0);
				tasksModel = new DefaultTableModel(tasksHeader, 0);

				workersTable = new JTable(workersModel);
				workersTable.setName("Lista pracownikow");

				projectsTable = new JTable(projectsModel);
				tasksTable = new JTable(tasksModel);

				try {
					// 1.6+
					workersTable.setAutoCreateRowSorter(true);
					projectsTable.setAutoCreateRowSorter(true);
					tasksTable.setAutoCreateRowSorter(true);
				} catch (Exception continuewithNoSort) {
				}

				createTableWithWorkers();
				createTableWithProjects();
				createTableWithTasks();

			}

			private void initializeJPanels() {
				userOptionsPanel = new JPanel(new GridLayout(0, 3));
				userOptionsPanel.setBorder(new TitledBorder(
						"Projekty i zadania"));

				projectOptionsPanel = new JPanel(new GridLayout(0, 3));
				projectOptionsPanel.setBorder(new TitledBorder("Projekty"));

				taskOptionsPanel = new JPanel(new GridLayout(0, 3));
				taskOptionsPanel.setBorder(new TitledBorder("Zadania"));

				dbButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3,
						3));
				dbButtonsPanel
						.setBorder(new TitledBorder("Obs³uga bazy danych"));

				extraFeatures = new JPanel(new GridLayout(0, 1));
				extraFeatures.setBorder(new TitledBorder("Dodatki"));
				mainPanel = new JPanel(new GridLayout(3, 1));

			}

			private void initializeButtons() {
				addNewWorkerButton = new JButton("Poka¿ projekty i zadania");

				addNewProjectButton = new JButton("Dodaj nowy projekt");
				deleteProjectButton = new JButton("Usun projekt");
				editProjectButton = new JButton("Edytuj projekt");

				addNewTaskButton = new JButton("Dodaj zadanie");
				deleteTaskButton = new JButton("Usun zadanie");
				editTaskButton = new JButton("Edytuj zadanie");

				userOptionsPanel.add(addNewWorkerButton);

				dbButtonsPanel.add(userOptionsPanel);

				projectOptionsPanel.add(addNewProjectButton);
				projectOptionsPanel.add(editProjectButton);

				dbButtonsPanel.add(projectOptionsPanel);

				taskOptionsPanel.add(addNewTaskButton);
				taskOptionsPanel.add(editTaskButton);

				dbButtonsPanel.add(taskOptionsPanel);

				// PRACOWNIK

				// PROJEKT
				addNewProjectButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						AddProjectWindow myWindow = new AddProjectWindow();
						myWindow.setVisible(true);
					}
				});

				editProjectButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						EditProjectWindow editWorkerWindow = new EditProjectWindow();
						editWorkerWindow.setVisible(true);
					}
				});

				deleteProjectButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						if (!projectIdToDelete.isEmpty()) {
							System.out.println("NIE PUSTY");
							int response = JOptionPane.showConfirmDialog(null,
									"Czy napewno usun¹æ projekt?",
									"Potwierdzenie", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.NO_OPTION) {
								System.out.println("No button clicked");
							} else if (response == JOptionPane.YES_OPTION) {
								System.out.println("Yes button clicked");
								dBo.deleteProjectFromDataBase(projectIdToDelete);
								System.out.println("Usuniêto u¿ytkownika: "
										+ emailOfUserToDelete);
							} else if (response == JOptionPane.CLOSED_OPTION) {
								System.out.println("JOptionPane closed");
							}

						} else {
							System.out.println("PUSTY");
						}
					}
				});

				addNewWorkerButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ProjectTasksDetailsWindow wDw = new ProjectTasksDetailsWindow();
						wDw.runWindow();
					}
				});

				// TASKS
				addNewTaskButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						AddTaskWindow addTaskWindow = new AddTaskWindow();
						addTaskWindow.setVisible(true);

					}
				});

				editTaskButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						EditTaskWindow editTaskWindow = new EditTaskWindow();
						editTaskWindow.setVisible(true);
					}
				});

				deleteTaskButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						if (!projectIdToDelete.isEmpty()) {
							System.out.println("NIE PUSTY");
							int response = JOptionPane.showConfirmDialog(null,
									"Czy napewno usun¹æ projekt?",
									"Potwierdzenie", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.NO_OPTION) {
								System.out.println("No button clicked");
							} else if (response == JOptionPane.YES_OPTION) {
								System.out.println("Yes button clicked");
								dBo.deleteProjectFromDataBase(projectIdToDelete);
								System.out.println("Usuniêto u¿ytkownika: "
										+ emailOfUserToDelete);
							} else if (response == JOptionPane.CLOSED_OPTION) {
								System.out.println("JOptionPane closed");
							}

						} else {
							System.out.println("PUSTY");
						}
					}
				});

			}

			private void ustawSkorkeNaPoczatku() {
				final UIManager.LookAndFeelInfo[] plafInfos = UIManager
						.getInstalledLookAndFeels();
				String[] plafNames = new String[plafInfos.length];
				for (int ii = 0; ii < plafInfos.length; ii++) {
					plafNames[ii] = plafInfos[ii].getName();
				}
				try {
					UIManager.setLookAndFeel(plafInfos[1].getClassName());
					SwingUtilities.updateComponentTreeUI(frame);
					frame.pack();
					frame.setMinimumSize(frame.getSize());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		SwingUtilities.invokeLater(r);
	}

	private void loadImageFromFile() {

		String fileName = null;

		JFileChooser chooser = new JFileChooser();
		int status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (file == null) {
				return;
			}
			fileName = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(fileName);
		}

		try {
			bi = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon ii = new ImageIcon(bi);
		JLabel imageLabel = new JLabel(ii);
		imagePanel.add(imageLabel, null);

		frame.pack();

	}

	// pracownicy
	private void createTableWithWorkers() {

		ListSelectionModel selectionModel = workersTable.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new WorkersRowListener(
				EmployeeWindow.this));

		java.util.List<Employee> employee = new ArrayList<>();

		try {
			employee = dBo.getAllUsersFromDb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PRACOWNICY: " + employee.toString());

		for (int i = 0; i < employee.size(); i++) {

			String firstName = employee.get(i).getFirstName();
			String lastName = employee.get(i).getLastName();
			String position = employee.get(i).getPosition();
			String section = employee.get(i).getSection();
			String email = employee.get(i).getEmail();
			String phoneNumber = employee.get(i).getPhoneNumber();

			Object[] data = { firstName, lastName, position, section, email,
					phoneNumber };

			workersModel.addRow(data);
		}

	}

	// projekty
	private void createTableWithProjects() {

		ListSelectionModel selectionModel = projectsTable.getSelectionModel();

		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ProjectsRowListener(
				EmployeeWindow.this));

		java.util.List<Project> projects = new ArrayList<>();

		try {
			projects = dBo.getAllProjectsFromDb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PROJECTS: " + projects.toString());

		for (int i = 0; i < projects.size(); i++) {

			int projectId = projects.get(i).getProjectId();
			String projectName = projects.get(i).getProjectName();
			String projectDescription = projects.get(i).getProjectDescription();
			String projectStartTimeDate = projects.get(i).getStartTimeDate();
			String projectEndTimeDate = projects.get(i).getEndTimeDate();

			Object[] data = { projectId, projectName, projectDescription,
					projectStartTimeDate, projectEndTimeDate };

			projectsModel.addRow(data);
		}

	}

	// lista zadan
	private void createTableWithTasks() {

		ListSelectionModel selectionModel = tasksTable.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new TasksRowListener(
				EmployeeWindow.this));

		java.util.List<Task> tasks = new ArrayList<>();

		try {
			tasks = dBo.getAllTasksFromDb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("TASKS: " + tasks.toString());

		for (int i = 0; i < tasks.size(); i++) {

			int taskId = tasks.get(i).getTaskId();
			String taskName = tasks.get(i).getTaskName();
			String taskDescription = tasks.get(i).getTaskDescription();
			String taskStartTimeDate = tasks.get(i).getStartTimeDate();
			String taskEndTimeDate = tasks.get(i).getEndTimeDate();

			Object[] data = { taskId, taskName, taskDescription,
					taskStartTimeDate, taskEndTimeDate };

			tasksModel.addRow(data);
		}

	}

	class WorkersRowListener implements ListSelectionListener {
		EmployeeWindow readRow;
		JTable table;

		public WorkersRowListener(EmployeeWindow rar) {
			readRow = rar;
			table = readRow.workersTable;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel model = table.getSelectionModel();
				int lead = model.getLeadSelectionIndex();
				displayRowValues(lead);
			}
		}

		private void displayRowValues(int rowIndex) {
			System.out.println("DISPLAY ROW VALUES: " + table.getColumnCount());
			int columns = table.getColumnCount();
			String s = "";

			for (int col = 0; col < columns; col++) {

				String value = table.getValueAt(rowIndex, col).toString();
				if (value.contains("@")) {
					s += value;
				}
			}
			emailOfUserToDelete = s;
		}
	}

	class ProjectsRowListener implements ListSelectionListener {
		EmployeeWindow readRow;
		JTable table;

		public ProjectsRowListener(EmployeeWindow rar) {
			readRow = rar;
			table = readRow.projectsTable;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel model = table.getSelectionModel();
				int lead = model.getLeadSelectionIndex();
				displayRowValues(lead);
			}
		}

		private void displayRowValues(int rowIndex) {
			System.out.println("DISPLAY ROW VALUES: " + table.getColumnCount());

			String id = table.getValueAt(rowIndex, 0).toString();
			System.out.println("Picked Project ID: " + id);
			projectIdToDelete = id;
		}
	}

	class TasksRowListener implements ListSelectionListener {
		EmployeeWindow readRow;
		JTable table;

		public TasksRowListener(EmployeeWindow rar) {
			readRow = rar;
			table = readRow.tasksTable;
		}

		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel model = table.getSelectionModel();
				int lead = model.getLeadSelectionIndex();
				displayRowValues(lead);
			}
		}

		private void displayRowValues(int rowIndex) {
			System.out.println("DISPLAY ROW VALUES: " + table.getColumnCount());
			String id = table.getValueAt(rowIndex, 0).toString();
			System.out.println("Picked Task ID: " + id);
			taskIdToDelete = id;
		}
	}
}