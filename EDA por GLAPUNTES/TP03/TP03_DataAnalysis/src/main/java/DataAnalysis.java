
import java.io.FileReader;  
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class DataAnalysis {
	
	public static void main(String[] args) throws IOException {
	
		// opcion 1 
//		String fileName= "/co_1980_alabama.csv";
//		InputStream is = DataAnalysis.class.getClass().getResourceAsStream(fileName);
			 
		    	
		    	
		// opcion 2 
		  String fileName= "/co_1980_alabama.csv"; InputStream is =
		  DataAnalysis.class.getResourceAsStream(fileName);

		    	
		    	
		// opcion 3 
		/*
		 * String fileName= "co_1980_alabama.csv"; InputStream is =
		 * DataAnalysis.class.getClassLoader().getResourceAsStream(fileName);
		 */		
		
	    Reader in = new InputStreamReader(is);
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
			      .withFirstRecordAsHeader()
			      .parse(in);
	    
	     for (CSVRecord record : records) {
			String value = record.get("daily_max_8_hour_co_concentration");
			System.out.printf("%s, %s%n", value, record.toString());
	     }

	     in.close();

	}
}