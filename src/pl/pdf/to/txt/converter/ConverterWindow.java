package pl.pdf.to.txt.converter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.login.LoginWindow;
import pl.login.Picture;
import java.awt.BorderLayout;

public class ConverterWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterWindow frame = new ConverterWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ConverterWindow() {
		// TODO Auto-generated constructor stub
		setTitle("PDF Converter");

		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		ConverterPicture picture = new ConverterPicture();
		getContentPane().add(picture, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

}
