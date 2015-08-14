package pl.softwaredeveloper.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

import db.connection.AdministratorDBOperations;

import pl.project.EmployeeProject;
import pl.project.EmployeeTask;

/**
 * A short example of a nested layout that can change PLAF at runtime. The
 * TitledBorder of each JPanel shows the layouts explicitly set.
 * 
 * @author Andrew Thompson
 * @version 2011-04-12
 */
public class SoftwareWorkerDetailsWindow {

	JTable table1, table2;
	private AdministratorDBOperations dBOperations;
	DefaultTableModel modelProject, modelTasks;
	String col[] = { "ID", "Nazwa", "Opis", "Data start", "Data end" };

	public void runWindow() {
		Runnable r = new Runnable() {

			public void run() {
				final JFrame frame = new JFrame("Projekty i zadania");

				final JPanel gui = new JPanel(new BorderLayout(5, 5));

				dBOperations = new AdministratorDBOperations();
				modelProject = new DefaultTableModel(col, 0);
				modelTasks = new DefaultTableModel(col, 0);

				table1 = new JTable(modelProject);
				table2 = new JTable(modelTasks);

				createTableWithProjects();
				createTableWithTasks();

				try {
					// 1.6+
					table1.setAutoCreateRowSorter(true);
					table2.setAutoCreateRowSorter(true);
				} catch (Exception continuewithNoSort) {
				}
				JScrollPane tableScroll = new JScrollPane(table1);
				tableScroll.setBorder(new TitledBorder("Projekty"));
				Dimension tablePreferred = tableScroll.getPreferredSize();
				tableScroll.setPreferredSize(new Dimension(
						tablePreferred.width, tablePreferred.height / 2));

				JScrollPane tableScroll2 = new JScrollPane(table2);
				tableScroll2.setPreferredSize(new Dimension(
						tablePreferred.width, tablePreferred.height / 2));

				tableScroll2.setBorder(new TitledBorder("Zadania"));

				JSplitPane splitPane = new JSplitPane(
						JSplitPane.VERTICAL_SPLIT, tableScroll, tableScroll2);

				splitPane.setOneTouchExpandable(true);
				splitPane.setResizeWeight(0.5);
				gui.add(splitPane, BorderLayout.CENTER);

				frame.setContentPane(gui);

				frame.pack();

				frame.setLocationRelativeTo(null);
				try {
					// 1.6+
					frame.setLocationByPlatform(true);
					frame.setMinimumSize(frame.getSize());
				} catch (Throwable ignoreAndContinue) {
				}

				frame.setVisible(true);
			}
		};
		SwingUtilities.invokeLater(r);
	}

	// pracownicy
	private void createTableWithProjects() {

		ListSelectionModel selectionModel = table1.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		java.util.List<EmployeeProject> employeeProjects = new ArrayList<>();

		try {
			employeeProjects = dBOperations
					.getProjectsForSpecifiedWorker(SoftwareDeveloperWindow.emailOfUserToDelete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PRACOWNICY: " + employeeProjects.toString());

		for (int i = 0; i < employeeProjects.size(); i++) {

			String projectID = employeeProjects.get(i).getProjectID();
			String projectName = employeeProjects.get(i).getProjectName();
			String projectDesc = employeeProjects.get(i).getProjectDesc();
			String projectStartD = employeeProjects.get(i).getProjectStartD();
			String projectEndD = employeeProjects.get(i).getProjectEndD();

			Object[] data = { projectID, projectName, projectDesc,
					projectStartD, projectEndD };

			modelProject.addRow(data);

			System.out.println(i + "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		}
	}

	// pracownicy
	private void createTableWithTasks() {

		ListSelectionModel selectionModel = table1.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		java.util.List<EmployeeTask> employeeTasks = new ArrayList<>();

		try {
			employeeTasks = dBOperations
					.getTasksForSpecifiedWorker(SoftwareDeveloperWindow.emailOfUserToDelete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("PRACOWNICY: " + employeeTasks.toString());

		for (int i = 0; i < employeeTasks.size(); i++) {

			String taskID = employeeTasks.get(i).getTaskID();
			String taskName = employeeTasks.get(i).getTaskName();
			String taskDesc = employeeTasks.get(i).getTaskDesc();
			String taskStartD = employeeTasks.get(i).getTaskStartD();
			String taskEndD = employeeTasks.get(i).getTaskEndD();

			Object[] data = { taskID, taskName, taskDesc, taskStartD, taskEndD };

			modelTasks.addRow(data);

			System.out.println(i + "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		}
	}
}
