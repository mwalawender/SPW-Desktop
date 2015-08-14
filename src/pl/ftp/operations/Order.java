package pl.ftp.operations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;

public class Order {


	//*****CZYSZCZENIE PLIKU TEKSTOWEGO Z ZAMÓWIENIEM
	public void ClearTxtOrder(String name) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(name + ".txt");
		writer.print("");
		writer.close();
	}
	////*****CZYSZCZENIE PLIKU TEKSTOWEGO Z ZAMÓWIENIEM    KONIEC
	
	//*****ZAPISYWANIE TEKSTU
	public void zapisywanieTekstu(JTextField poleTekstowe, String nazwaPliku) {

		
		try {
			

			File file = new File(nazwaPliku + ".txt");

			
			if (!file.exists()) {
				file.createNewFile();
			}

			
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			PrintWriter bufferWritter = new PrintWriter(fileWritter);
			bufferWritter.println(poleTekstowe.getText());
			bufferWritter.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//*****ZAPISYWANIE TEKSTU KONIEC
	
	//*****ZAPISYWANIE TEKSTU 2
		public void zapisywanieTekstu2(String poleTekstowe, String nazwaPliku) {

			
			try {
				

				File file = new File(nazwaPliku + ".txt");

				
				if (!file.exists()) {
					file.createNewFile();
				}

				
				FileWriter fileWritter = new FileWriter(file.getName(), true);
				PrintWriter bufferWritter = new PrintWriter(fileWritter);
				bufferWritter.println(poleTekstowe);
				bufferWritter.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		//*****ZAPISYWANIE TEKSTU 2 KONIEC
	//**** DODAWANIE LISTY Z PLIKU TXT "ZAMÓWIENIE"
	public ArrayList<String> dodajDoListy(String nazwaPliku) {

		String[] arr = null;
		ArrayList<String> items = new ArrayList<String>();

		try {
			FileInputStream fstream_school = new FileInputStream(nazwaPliku
					+ ".txt");
			DataInputStream data_input = new DataInputStream(fstream_school);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					data_input));
			String str_line;

			while ((str_line = buffer.readLine()) != null) {
				str_line = str_line.trim();
				if ((str_line.length() != 0)) {
					items.add(str_line);
				}
			}

			arr = (String[]) items
					.toArray(new String[items.size()]);
		} catch (IOException e) {
			System.out.println("NIE DODANO catch");
			
		} finally {
			System.out.println("finally");
			System.out.println(Arrays.asList(items));
			
		}

		return items;

	}
	//**** DODAWANIE LISTY Z PLIKU TXT "ZAMÓWIENIE" KONIEC
}
