package pl.admin.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdministratorPicture extends JPanel {

	private static final long serialVersionUID = 1L;

	public AdministratorPicture() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);

	}

	private void showDialogWithNotFilledFieldsWarn() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Prosze wypelnic wszystkie pola!");
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
