package pl.ftp.operations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Search {

	
	public int ilePlk = 0 ;
	
	public void szukanie(){
		
		
		
		
		
		FrameMain.textINFO.setText("Szukam");
		String nazwa = "zamowienie";

		// **** ZAPISYWANIE Do PLIKU KLUCZY
		Order zapisz = new Order();

		// dodajDoListy(nazwa);
		Order list = new Order();
		Main sprawdzMain = new Main();
		IlePlikow ile = new IlePlikow();

		ilePlk = ile.liczIlePlikow();
		System.out.println("Z LICZENIA " + ilePlk);
		System.out
				.println("LICZBA OGLOSZEN " + AdDownload.ilePlikow);
		sprawdzMain.sprawdz(ilePlk, list.dodajDoListy(nazwa));// ***********

		System.out.println("000000000000000000000000");
		System.out.println("Ilosc wystapien: " + Main.max);
		System.out.println("ID pliku: " + Main.file_max);
		System.out.println("000000000000000000000000");

		BufferedReader plikDoWyswietlenia = null;
		try {
			plikDoWyswietlenia = new BufferedReader(new FileReader(
					"OgloszenieNr" + Main.file_max + ".txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			
			FrameMain.textArea.read(plikDoWyswietlenia, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	
	
	
}
