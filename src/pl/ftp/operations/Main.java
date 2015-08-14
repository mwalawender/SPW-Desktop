package pl.ftp.operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.swing.text.rtf.RTFEditorKit;

public class Main {
	
	static int ileRazy = 0; // LICZNIK

	static int ileRazyPlik = 0;
	static int numer = 0;
	static int licznik = 0;
	
	static int max=0;
	static int file_max=0;

	// *******LICZNIK ILE RAZY W PLIKU

		public void infoLiczbaDopasowan(int ile, int liczbaPliku) {

			System.out.println("w pliku nr: " + liczbaPliku + " jest " + ile
					+ " dopasowan*********************************");

		}

		// *******KONIEC
	
	
		
		// ****FUNKCJA_LICZNIK
		public int licznik(int numerPliku) {
			// ileRazy = 0;
			ileRazy++;

			return ileRazy;
		}

	public void sprawdz(int ilePlikow, ArrayList<String> kolekcja) {

		

		for (int i = 0; i < ilePlikow; i++) {// FOR NR 1
			ileRazyPlik = ileRazy;
			infoLiczbaDopasowan(ileRazyPlik, (i));
			
			if(ileRazyPlik > max){
				max=ileRazyPlik;
				file_max = i;
			}
			
			ileRazy = 0;
			numer++;
			for (int j = 0; j < kolekcja.size(); j++) {
				try {

					

					BufferedReader bf = new BufferedReader(new FileReader(
							"OgloszenieNr" + numer + ".txt"));

					
					int linecount = 0;

					String line;

					
					System.out.println("Przeszukiwanie " + kolekcja.get(j)
							+ " pliku o nazwie: \"OgloszenieNr" + numer + "\"");

					// ******************************************************************

					Pattern p = Pattern.compile(
							kolekcja.get(j),
							Pattern.CASE_INSENSITIVE);
					
					while ((line = bf.readLine()) != null) {
						linecount++;

						Matcher m = p.matcher(line);
					
						while (m.find()) {

							System.out.println("Slowo \"" + kolekcja.get(j)
									+ " \"znalezione na pozycji " + m.start()
									+ " w lini " + linecount);

							licznik(numer);

							System.out.println(ileRazy);

						}
					}

					// ******************************************************************
					bf.close();
				} catch (IOException e) {
					System.out.println("IO Error Occurred: " + e.toString());
				}
			}// KONIEC FORA NR 2
		}// koniec FORA NR 1
	}

	}