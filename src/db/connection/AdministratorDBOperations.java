package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.project.Employee;
import pl.project.EmployeeProject;
import pl.project.EmployeeTask;
import pl.project.Project;
import pl.project.Task;

/**
 * 
 * @author Michaœ do zrobienia: edytowanie pracownika usuwanie jest w zasadzie
 *         zrobione -> klikniecie na odpiwedni wiersz w tabeli i usuniecie go
 *         pracownik layout itp itd dorzucic inzynierke moja i szubiego,
 *         crawler, reszta gdzies byla dalej w opisie... dzisiaj edity do
 *         zrobienia + szczegoly kazdego projektu...
 * 
 *         zapis text -> PDF
 */

public class AdministratorDBOperations {

	private List<Employee> employeeList = new ArrayList<Employee>();

	private static final String className = "com.mysql.jdbc.Driver";
	private static final String dataBaseAdress = "jdbc:mysql://localhost:3306/test";
	private static final String dataBaseLogin = "root";
	private static final String dataBasePassword = "";
	private static final String ADMIN = "Administrator";
	private static final String WORKER = "Pracownik";
	private static final String P_MANAGER = "PM";
	private static final String SOFTWARE_DEVELOPER = "Programista";

	public static void main(String[] args) throws Exception {

	}

	public static Connection getConnection() throws Exception {

		Class.forName(className);
		Connection conn = DriverManager.getConnection(dataBaseAdress,
				dataBaseLogin, dataBasePassword);
		return conn;
	}

	public int validateUser(String mEmail, String mPassword) throws Exception {

		employeeList = getAllUsersFromDb();
		System.out.println("Szukam pod: " + mEmail + " " + mPassword);
		int whichWindowToLoad = -1, i = 0;
		boolean vaildationPassed = false;

		System.out.println(whichWindowToLoad);

		while (!vaildationPassed) {

			System.out.println(i);

			if (employeeList.get(i).getEmail().equals(mEmail)) {
				System.out.println("USER JEST!");
				if (employeeList.get(i).getPassword().equals(mPassword)) {
					System.out.println("HASLO TEZ SIE ZGADZA!");
					String position = employeeList.get(i).getPosition();
					whichWindowToLoad = matchPositionToRightNumber(position);
					vaildationPassed = true;
				}

			} else {
				System.out.println("DUPA DEBOWA");
				whichWindowToLoad = 0;
			}
			i++;
		}
		return whichWindowToLoad;
	}

	public void deleteWorkerFromDataBase(String mWorkerEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM pracownicy WHERE Email = ?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mWorkerEmail);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteProjectFromDataBase(String mProjectId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM projekty WHERE ID_PROJEKTU = ?";
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mProjectId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteTaskFromDataBase(String mTaskId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM zadania WHERE ID_TASKU = ?";
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mTaskId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getSingleUserWithSpecifiedEmailFromDataBase(
			String mPickedEmail, JTextField tfWorkerFirstName,
			JTextField tfWorkerLastName, JTextField tfWorkerPosition,
			JTextField tfWorkerSection, JTextField tfWorkerPhoneNumber,
			JTextField tfWorkerEmail, JTextField tfWorkerPassword) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet mResultSet = null;

		Employee resultEmployee;

		String query = "select `ID_PRACOWNIKA`, `Imie`, `Nazwisko`, `Stanowisko`, `Dzial`, `Numer telefonu`, `Email`, `Haslo` from pracownicy "
				+ "where Email = ?";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mPickedEmail);

			mResultSet = pstmt.executeQuery();

			while (mResultSet.next()) {
				resultEmployee = new Employee(mResultSet.getInt(1),
						mResultSet.getString(2), mResultSet.getString(3),
						mResultSet.getString(4), mResultSet.getString(5),
						mResultSet.getString(6), mResultSet.getString(7),
						mResultSet.getString(8));

				System.out.println("nana user " + resultEmployee.toString());

				tfWorkerFirstName.setText(mResultSet.getString(2));
				tfWorkerLastName.setText(mResultSet.getString(3));
				tfWorkerPosition.setText(mResultSet.getString(4));
				tfWorkerSection.setText(mResultSet.getString(5));
				tfWorkerPhoneNumber.setText(mResultSet.getString(6));
				tfWorkerEmail.setText(mResultSet.getString(7));
				tfWorkerPassword.setText(mResultSet.getString(8));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getSingleProjectWithSpecifiedIdNumberFromDataBase(
			String pickedId, JTextField tfProjectName,
			JTextArea tfProjectDescription, JTextField tfProjectStartTimeDate,
			JTextField tfProjectEndTimeDate) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet mResultSet = null;

		Project resultProject;

		String query = "select `ID_PROJEKTU`, `Nazwa`, `Opis`, `Data_rozpoczecia`, `Data_zakonczenia` from projekty "
				+ "where ID_PROJEKTU = ?";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pickedId);

			mResultSet = pstmt.executeQuery();

			while (mResultSet.next()) {
				resultProject = new Project(mResultSet.getInt(1),
						mResultSet.getString(2), mResultSet.getString(3),
						mResultSet.getString(4), mResultSet.getString(5));

				System.out.println("nana projekt " + resultProject.toString());

				tfProjectName.setText(mResultSet.getString(2));
				tfProjectDescription.setText(mResultSet.getString(3));
				tfProjectStartTimeDate.setText(mResultSet.getString(4));
				tfProjectEndTimeDate.setText(mResultSet.getString(5));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<EmployeeProject> getProjectsForSpecifiedWorker(String id) {

		System.out.println("WYBRANO USERA DO PROJEKTOW: " + id);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet mResultSet = null;

		List<EmployeeProject> employeeProjectList = new ArrayList<EmployeeProject>();

		String query = "SELECT projekty.ID_PROJEKTU, projekty.Nazwa, projekty.Opis, projekty.Data_rozpoczecia, projekty.Data_zakonczenia, pracownicy.Email, pracownicy.Imie, pracownicy.Nazwisko FROM projekty, pracownicy,pracownicy_projekty_mapping WHERE pracownicy_projekty_mapping.ID_PROJEKTU = projekty.ID_PROJEKTU AND pracownicy_projekty_mapping.ID_Pracownika = pracownicy.ID_Pracownika ORDER BY projekty.Nazwa, pracownicy.Email ";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			mResultSet = pstmt.executeQuery();
			System.out.println("Projekt, Email:");
			while (mResultSet.next()) {

				System.out.println(mResultSet.getString(6));

				if (!id.isEmpty() && id.equals(mResultSet.getString(6))) {

					System.out.println("CYCE");
					employeeProjectList.add(new EmployeeProject(mResultSet
							.getString(1), mResultSet.getString(2), mResultSet
							.getString(3), mResultSet.getString(4), mResultSet
							.getString(5), mResultSet.getString(6), mResultSet
							.getString(7), mResultSet.getString(8)));

					String projectID = mResultSet.getString(1);
					String projectName = mResultSet.getString(2);
					String projectDesc = mResultSet.getString(3);
					String projectStartD = mResultSet.getString(4);
					String projectEndD = mResultSet.getString(5);

					String workerEmail = mResultSet.getString(6);
					String workerName = mResultSet.getString(7);
					String workerSurname = mResultSet.getString(8);

					System.out.println(projectName + " " + projectID + " "
							+ projectDesc + " " + projectStartD + " "
							+ projectEndD + " " + workerEmail + " "
							+ workerName + " " + workerSurname);
				} else {
					System.out.println("Nie wybrano nic");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Prosze wybrac usera do PROJEKTOW!");
		}

		System.out.println("sruuuuuuuuuuuuu" + employeeProjectList.toString());
		return employeeProjectList;

	}

	public List<EmployeeTask> getTasksForSpecifiedWorker(String id) {

		System.out.println("WYBRANO USERA DO TASKOW: " + id);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet mResultSet = null;

		List<EmployeeTask> employeeTaskList = new ArrayList<EmployeeTask>();

		String query = "SELECT zadania.ID_TASKU, zadania.Nazwa_tasku, zadania.Opis, zadania.Data_rozpoczecia, zadania.Data_zakonczenia, pracownicy.Email, pracownicy.Imie, pracownicy.Nazwisko FROM zadania, pracownicy,pracownicy_zadania_mapping WHERE pracownicy_zadania_mapping.ID_TASKU = zadania.ID_TASKU AND pracownicy_zadania_mapping.ID_Pracownika = pracownicy.ID_Pracownika ORDER BY zadania.Nazwa_tasku, pracownicy.Email ";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			mResultSet = pstmt.executeQuery();
			System.out.println("Zadanie, Email:");
			while (mResultSet.next()) {

				System.out.println(mResultSet.getString(6));

				if (!id.isEmpty() && id.equals(mResultSet.getString(6))) {

					System.out.println("CYCE");
					employeeTaskList.add(new EmployeeTask(mResultSet
							.getString(1), mResultSet.getString(2), mResultSet
							.getString(3), mResultSet.getString(4), mResultSet
							.getString(5), mResultSet.getString(6), mResultSet
							.getString(7), mResultSet.getString(8)));

					String taskID = mResultSet.getString(1);
					String taskName = mResultSet.getString(2);
					String taskDesc = mResultSet.getString(3);
					String taskStartD = mResultSet.getString(4);
					String taskEndD = mResultSet.getString(5);

					String workerEmail = mResultSet.getString(6);
					String workerName = mResultSet.getString(7);
					String workerSurname = mResultSet.getString(8);

					System.out.println(taskID + " " + taskName + " " + taskDesc
							+ " " + taskStartD + " " + taskEndD + " "
							+ workerEmail + " " + workerName + " "
							+ workerSurname);
				} else {
					System.out.println("Nie wybrano nic");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Prosze wybrac usera DO TASKOW !");
		}

		System.out.println("sruuuuuuuuuuuuu" + employeeTaskList.toString());
		return employeeTaskList;

	}

	public void getSingleTaskWithSpecifiedIdNumberFromDataBase(String pickedId,
			JTextField tfTaskName, JTextArea tfTaskDescription,
			JTextField tfTaskStartTimeDate, JTextField tfTaskEndTimeDate) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet mResultSet = null;

		Task resultTask;

		String query = "select `ID_TASKU`,`Nazwa_tasku`, `Opis`, `Data_rozpoczecia`, `Data_zakonczenia` from zadania "
				+ "where ID_TASKU = ?";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pickedId);

			mResultSet = pstmt.executeQuery();

			while (mResultSet.next()) {
				resultTask = new Task(mResultSet.getInt(1),
						mResultSet.getString(2), mResultSet.getString(3),
						mResultSet.getString(4), mResultSet.getString(5));

				System.out.println("nana " + resultTask.toString());

				tfTaskName.setText(mResultSet.getString(2));
				tfTaskDescription.setText(mResultSet.getString(3));
				tfTaskStartTimeDate.setText(mResultSet.getString(4));
				tfTaskEndTimeDate.setText(mResultSet.getString(5));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateTaskInDataBase(int taskId, JTextField tfTaskName,
			JTextArea tfTaskDescription, JTextField tfTaskStartTimeDate,
			JTextField tfTaskEndTimeDate) {

		System.out.println("Aktualizuje task o ID: " + taskId);

		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "UPDATE zadania "
				+ "SET `Nazwa_tasku` = ?, `Opis` = ?,`Data_rozpoczecia` = ?, `Data_zakonczenia` = ?"
				+ "WHERE ID_TASKU = ?";

		System.out.println("apdejt" + tfTaskName.getText() + " "
				+ tfTaskDescription.getText() + " "
				+ tfTaskStartTimeDate.getText() + " "
				+ tfTaskEndTimeDate.getText());

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tfTaskName.getText());
			pstmt.setString(2, tfTaskDescription.getText());
			pstmt.setString(3, tfTaskStartTimeDate.getText());
			pstmt.setString(4, tfTaskEndTimeDate.getText());
			pstmt.setInt(5, taskId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void updateProjectInDataBase(int projectId,
			JTextField tfProjectName, JTextArea tfProjectDescription,
			JTextField tfProjectStartTimeDate, JTextField tfProjectEndTimeDate) {

		System.out.println("Aktualizuje projekt o ID: " + projectId);

		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "UPDATE projekty "
				+ "SET `Nazwa` = ?, `Opis` = ?,`Data_rozpoczecia` = ?, `Data_zakonczenia` = ?"
				+ "WHERE ID_PROJEKTU = ?";

		System.out.println("apdejt" + tfProjectName.getText() + " "
				+ tfProjectDescription.getText() + " "
				+ tfProjectStartTimeDate.getText() + " "
				+ tfProjectEndTimeDate.getText());

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, tfProjectName.getText());
			pstmt.setString(2, tfProjectDescription.getText());
			pstmt.setString(3, tfProjectStartTimeDate.getText());
			pstmt.setString(4, tfProjectEndTimeDate.getText());
			pstmt.setInt(5, projectId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void updateUserInDataBase(String mWorkerEmail,
			JTextField tfWorkerFirstName, JTextField tfWorkerLastName,
			JTextField tfWorkerPosition, JTextField tfWorkerSection,
			JTextField tfWorkerPhoneNumber, JTextField tfWorkerEmail,
			JTextField tfWorkerPassword) {

		System.out.println("Aktualizuje usera o emailu: " + mWorkerEmail);

		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "UPDATE pracownicy "
				+ "SET `Imie` = ?, `Nazwisko` = ?, `Stanowisko` = ?, `Dzial` = ?, `Numer telefonu` = ?, `Email` = ?, `Haslo` = ?"
				+ "WHERE Email = ?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, tfWorkerFirstName.getText());
			pstmt.setString(2, tfWorkerLastName.getText());
			pstmt.setString(3, tfWorkerPosition.getText());
			pstmt.setString(4, tfWorkerSection.getText());
			pstmt.setString(5, tfWorkerPhoneNumber.getText());
			pstmt.setString(6, tfWorkerEmail.getText());
			pstmt.setString(7, tfWorkerPassword.getText());
			pstmt.setString(8, mWorkerEmail);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void insertNewWorkerIntoDataBase(String mWorkerFirstName,
			String mWorkerLastName, String mWorkerPosition,
			String mWorkerSection, String mWorkerPhoneNumber,
			String mWorkerEmail, String mWorkerPassword) throws Exception {

		System.out.println("Dodaje usera do bazy danych!");

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO pracownicy ( `Imie`, `Nazwisko`, `Stanowisko`, `Dzial`, `Numer telefonu`, `Email`, `Haslo`) VALUES (?, ?, ?, ?, ?, ?, ?);";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mWorkerFirstName);
			pstmt.setString(2, mWorkerLastName);
			pstmt.setString(3, mWorkerPosition);
			pstmt.setString(4, mWorkerSection);
			pstmt.setString(5, mWorkerPhoneNumber);
			pstmt.setString(6, mWorkerEmail);
			pstmt.setString(7, mWorkerPassword);
			pstmt.executeUpdate();
			System.out.println("Wsadzono u¿ytkownika do bazy danych!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	public void insertNewProjectIntoDataBase(String mProjectName,
			String mProjectDescription, String mProjectStartTime,
			String mProjectEndTime) throws Exception {

		System.out.println("Dodaje projekt do bazy danych!");

		java.util.Date utilDateStart = new SimpleDateFormat("yyyy-MM-dd")
				.parse(mProjectStartTime);
		java.sql.Date sqlDateStart = new java.sql.Date(utilDateStart.getTime());

		java.util.Date utilDateEnd = new SimpleDateFormat("yyyy-MM-dd")
				.parse(mProjectEndTime);
		java.sql.Date sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO projekty ( `Nazwa`, `Opis`, `Data_rozpoczecia`, `Data_zakonczenia`) VALUES (?, ?, ?, ?);";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mProjectName);
			pstmt.setString(2, mProjectDescription);
			pstmt.setDate(3, sqlDateStart);
			pstmt.setDate(4, sqlDateEnd);
			pstmt.executeUpdate();
			System.out.println("Wsadzono projekt do bazy danych!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}
	}

	public void insertNewTaskIntoDataBase(String mTaskName,
			String mTaskDescription, String mTaskStartTime, String mTaskEndTime)
			throws Exception {

		System.out.println("Dodaje task do bazy danych!");

		java.util.Date utilDateStart = new SimpleDateFormat("yyyy-MM-dd")
				.parse(mTaskStartTime);
		java.sql.Date sqlDateStart = new java.sql.Date(utilDateStart.getTime());

		java.util.Date utilDateEnd = new SimpleDateFormat("yyyy-MM-dd")
				.parse(mTaskEndTime);
		java.sql.Date sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO zadania ( `Nazwa_tasku`, `Opis`, `Data_rozpoczecia`, `Data_zakonczenia`) VALUES (?, ?, ?, ?);";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mTaskName);
			pstmt.setString(2, mTaskDescription);
			pstmt.setDate(3, sqlDateStart);
			pstmt.setDate(4, sqlDateEnd);
			pstmt.executeUpdate();
			System.out.println("Wsadzono task do bazy danych!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.close();
		}

	}

	private static int matchPositionToRightNumber(String mPosition) {
		int positionNumber = -1;
		System.out.println(mPosition + " pos");
		if (mPosition.equals(ADMIN)) {
			positionNumber = 1;
			System.out.println("admin");
		} else if (mPosition.equals(WORKER)) {
			positionNumber = 2;
			System.out.println("worker");
		} else if (mPosition.equals(SOFTWARE_DEVELOPER)) {
			positionNumber = 3;
			System.out.println("programista");
		} else if (mPosition.equals(P_MANAGER)) {
			positionNumber = 4;
			System.out.println("project manager");
		} else {
			System.out.println("def");
			positionNumber = 0;
		}
		return positionNumber;
	}

	public List<Employee> getAllUsersFromDb() throws Exception {

		List<Employee> employeeList = new ArrayList<Employee>();

		String query = "select * from pracownicy";
		Connection mConnection = getConnection();

		PreparedStatement mPreparedStatement = mConnection
				.prepareStatement(query);

		ResultSet mResultSet = mPreparedStatement.executeQuery();

		while (mResultSet.next()) {

			employeeList.add(new Employee(mResultSet.getInt(1), mResultSet
					.getString(2), mResultSet.getString(3), mResultSet
					.getString(4), mResultSet.getString(5), mResultSet
					.getString(6), mResultSet.getString(7), mResultSet
					.getString(8)));

			System.out.println(mResultSet.getString(1) + " "
					+ mResultSet.getString(2) + " " + mResultSet.getString(3)
					+ " " + mResultSet.getString(4) + " "
					+ mResultSet.getString(5) + " " + mResultSet.getString(6)
					+ " " + mResultSet.getString(6) + " "
					+ mResultSet.getString(7) + " " + mResultSet.getString(8));
		}
		return employeeList;

	}

	// get all projects from db
	public List<Project> getAllProjectsFromDb() throws Exception {

		List<Project> projectList = new ArrayList<Project>();

		String query = "select * from projekty";
		Connection mConnection = getConnection();

		PreparedStatement mPreparedStatement = mConnection
				.prepareStatement(query);

		ResultSet mResultSet = mPreparedStatement.executeQuery();

		while (mResultSet.next()) {

			projectList.add(new Project(mResultSet.getInt(1), mResultSet
					.getString(2), mResultSet.getString(3), mResultSet
					.getString(4), mResultSet.getString(5)));

			System.out.println(mResultSet.getInt(1) + " "
					+ mResultSet.getString(2) + " " + mResultSet.getString(3)
					+ " " + mResultSet.getString(4) + " "
					+ mResultSet.getString(5));

		}
		return projectList;
	}

	// pobierz wszystkie taski z bazy danych
	public List<Task> getAllTasksFromDb() throws Exception {

		List<Task> taskList = new ArrayList<Task>();

		String query = "select * from zadania";
		Connection mConnection = getConnection();

		PreparedStatement mPreparedStatement = mConnection
				.prepareStatement(query);

		ResultSet mResultSet = mPreparedStatement.executeQuery();

		while (mResultSet.next()) {

			taskList.add(new Task(mResultSet.getInt(1),
					mResultSet.getString(2), mResultSet.getString(3),
					mResultSet.getString(4), mResultSet.getString(5)));

			System.out.println(mResultSet.getInt(1) + " "
					+ mResultSet.getString(2) + " " + mResultSet.getString(3)
					+ " " + mResultSet.getString(4) + " "
					+ mResultSet.getString(5));

		}
		return taskList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
