package pl.pdf.to.txt.converter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConverterPicture extends JPanel {

	private static final long serialVersionUID = 1L;
	String fileName = null;
	JTextArea textArea;

	public ConverterPicture() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);

		JButton btnWczytajPlik = new JButton("Wczytaj Plik");
		btnWczytajPlik.setBounds(20, 32, 144, 37);
		add(btnWczytajPlik);

		JButton btnConvert = new JButton("PDF -> TXT");
		btnConvert.setBounds(20, 80, 144, 37);
		add(btnConvert);

		textArea = new JTextArea();
		textArea.setBounds(174, 32, 414, 426);
		add(textArea);
		
		JButton btnTxtPdf = new JButton("TXT -> PDF");
		btnTxtPdf.setBounds(20, 128, 144, 37);
		add(btnTxtPdf);

		btnWczytajPlik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// wczytanie pliku do konwersjiii

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

			}

		});

		btnConvert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// konwersja pliku
				PDFToTxtConverter pdfConverter = new PDFToTxtConverter();
				pdfConverter.convertPdfIntoTextFile(fileName, textArea);

			}
		});

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
}
