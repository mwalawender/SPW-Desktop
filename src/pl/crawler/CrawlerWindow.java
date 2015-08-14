package pl.crawler;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import db.connection.AdministratorDBOperations;

public class CrawlerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public boolean isRun = false;

	JTextField tfProjectName;

	static JTextArea taProjectDescription;

	AdministratorDBOperations dBOperations;
	static CrawlerWindow frame;
	String mProjectName, mProjectDescription, mProjectStartTimeDate,
			mProjectEndTimeDate;

	private static final String URL = "http://www.mit.edu";
	public static CrawlerDBOperations db = new CrawlerDBOperations();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CrawlerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CrawlerWindow() {
		setTitle("Crawler");

		setBounds(100, 100, 568, 512);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblImie = new JLabel("Adres:");
		lblImie.setBounds(10, 25, 52, 14);
		contentPane.add(lblImie);

		tfProjectName = new JTextField();
		tfProjectName.setBounds(92, 17, 171, 31);
		contentPane.add(tfProjectName);
		tfProjectName.setColumns(10);
		tfProjectName.setText(URL);

		taProjectDescription = new JTextArea();
		taProjectDescription.setBounds(92, 59, 366, 340);
		taProjectDescription.setLineWrap(true);
		// contentPane.add(taProjectDescription);

		JScrollPane scroll = new JScrollPane(taProjectDescription,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(92, 59, 366, 340);
		contentPane.add(scroll);

		JButton btnProject = new JButton("Szukaj");
		btnProject.setBounds(220, 410, 96, 42);
		contentPane.add(btnProject);

		JLabel lblNewLabel = new JLabel("Wynik: ");
		lblNewLabel.setBounds(10, 59, 46, 14);
		contentPane.add(lblNewLabel);

		btnProject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					runCrawler(tfProjectName.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void runCrawler(String mURL) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
		processPage(URL);
	};

	public void processPage(String URL) throws SQLException, IOException {
		// check if the given URL is already in database
		String sql = "select * from record where URL = '" + URL + "'";
		ResultSet rs = db.runSql(sql);
		if (rs.next()) {

		} else {
			// store the URL to database to avoid parsing again
			sql = "INSERT INTO  `crawler`.`record` " + "(`URL`) VALUES "
					+ "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();

			// get useful information
			Document doc = Jsoup.connect("http://www.mit.edu/").get();

			if (doc.text().contains("research")) {
				System.out.println(URL + " jejeje");
				taProjectDescription.append(URL + "\n");
			}

			// get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				if (link.attr("href").contains("mit.edu"))
					processPage(link.attr("abs:href"));
			}
		}
	}
}
