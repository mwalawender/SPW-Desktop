package pl.examples;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReadingARow {
	JTable table;
	JLabel label;

	public ReadingARow() {
		String[] headers = { "column 1", "column 2", "column 3", "column 4" };
		int rows = 18;
		int cols = 4;
		String[][] data = new String[rows][cols];
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				data[row][col] = "item " + (row * cols + col + 1);
		table = new JTable(data, headers);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new RowListener(this));
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createTitledBorder("selected row values"));
		Dimension d = label.getPreferredSize();
		d.height = 45;
		label.setPreferredSize(d);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new JScrollPane(table));
		f.getContentPane().add(label, "South");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new ReadingARow();
	}
}

class RowListener implements ListSelectionListener {
	ReadingARow readRow;
	JTable table;

	public RowListener(ReadingARow rar) {
		readRow = rar;
		table = readRow.table;
	}

	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			ListSelectionModel model = table.getSelectionModel();
			int lead = model.getLeadSelectionIndex();
			displayRowValues(lead);
		}
	}

	private void displayRowValues(int rowIndex) {
		int columns = table.getColumnCount();
		String s = "";
		for (int col = 0; col < columns; col++) {
			Object o = table.getValueAt(rowIndex, col);
			s += o.toString();
			if (col < columns - 1)
				s += ", ";
		}
		readRow.label.setText(s);
	}
}