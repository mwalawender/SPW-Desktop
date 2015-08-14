package pl.ftp.operations;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class IlePlikow {

	static int ile =0;
	public int liczIlePlikow(){
		
		ile = 0 ;
		for(int i =1 ; i < 100 ;i++){
		File f = new File("OgloszenieNr"+i+".txt");
		if(f.exists()) { ile++; }
		}
		return ile;
		}
	
	
	
}
