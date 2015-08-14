package pl.pdf.to.txt.converter;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JTextArea;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFToTxtConverter {

	public void convertPdfIntoTextFile(String fileName, JTextArea textArea) {

		PDFParser parser = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		PDFTextStripper pdfStripper;

		String parsedText;
		File file = new File(fileName);

		try {

			parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
			System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
			textArea.append(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
			textArea.setLineWrap(true);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
	}
}
