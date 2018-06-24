package proba.java.order;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

public class Main {
	
	public static void main(String[] args) throws IOException, SQLException {
		double startTime = System.nanoTime();
		
		Properties Config = new Properties();
		InputStream input = null;
		Functions function = new Functions();
		function.conf(input, Config);
		FileWriter fw = new FileWriter(Config.getProperty("ResponeFileNameLocal"),true);
		BufferedWriter writer = new BufferedWriter (fw);
		String[] asd = new String[12];			//ebben lesz az aktualisan beolvasott sor
		
		String[] resp = {"LineNumber", "Status", "Message"};			//valasz az aktualis sorra
		function.append(resp, writer);											//rogton a fejlecet bele is irjuk a fajlba
		

		
		//db connect, conn
		Connection conn =function.Connect(Config);
		
			BufferedReader reader = null;
			{
				try {
				    File file = new File(Config.getProperty("InputFileName"));
				    reader = new BufferedReader(new FileReader(file));
				    System.out.println("Reading input file");
				    String line;
				    int ln = 0;
				    System.out.println("Validating input lines");
					while ((line = reader.readLine()) != null) {
						if (ln != 0) {
							asd = line.split(";");
					    	function.validate(asd, resp);
					    	if (resp[2].trim().isEmpty()) {
					    		function.update(conn, asd, resp);
					    	}
					    	function.append(resp, writer);
						}
						ln++;
				    }
				} finally {
					reader.close();
				}
		    }
		
		//db connect lebont
		System.out.println("Response file created");
		conn.close();
		
		writer.close();
		function.ftp(Config);
		
		double endTime   = System.nanoTime();
		double totalTime = endTime - startTime;
		System.out.println("Finished in " + totalTime/1000000000 + " s");
			
		}
		

	

}
