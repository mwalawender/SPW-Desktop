package pl.ftp.operations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class AdDownload {

	static int ilePlikow;



	public int download() throws IOException {

		
		int fileNumber = 1;
		boolean loop = true;
		// DOBRE
		while (loop) {

			String adress = "http://spw.cba.pl/OgloszenieNr" + fileNumber
					+ ".txt";
	
			loop = exists(adress);

			if (!loop) {
				break;
			} else {

				URL website = new URL(
						adress);
				ReadableByteChannel rbc = Channels.newChannel(website
						.openStream());
				FileOutputStream fos = new FileOutputStream("OgloszenieNr" + fileNumber
						+ ".txt");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				
				fileNumber++;
			}

		}
		// *********
		ilePlikow = fileNumber -1 ;
		System.out.println(ilePlikow);
		return fileNumber-1;
	}
	
	private boolean exists(String URLName){
	    try {
	      HttpURLConnection.setFollowRedirects(false);
	      
	      HttpURLConnection con =
	         (HttpURLConnection) new URL(URLName).openConnection();
	      con.setRequestMethod("HEAD");
	      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	  }

}
