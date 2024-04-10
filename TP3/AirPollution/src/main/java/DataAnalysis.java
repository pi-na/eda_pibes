import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;




public class DataAnalysis {
    public static void main(String[] args) throws IOException {

	    // leemos el archivo
    	
    	/*
       	// opcion 1:  probar con  / o sin barra
	    URL resource = DataAnalysis.class.getClassLoader().getResource("co_1980_alabama.csv");
	    Reader in = new FileReader(resource.getFile());
    	*/
    	
    	
    	/*
    	// opcion 2:  probar con  / o sin barra
        URL resource= DataAnalysis.class.getResource("/co_1980_alabama.csv");
   	    Reader in = new FileReader(resource.getFile());
    	*/
    	
    	/*
    	// opcion 3: probar con / o sin barra
    	String fileName= "/co_1980_alabama.csv";
    	InputStream is = DataAnalysis.class.getClass().getResourceAsStream(fileName);
    	Reader in = new InputStreamReader(is);
    	*/
    	
    	/*
  		// opcion 4 
 		String fileName= "/co_1980_alabama.csv"; 
 		InputStream is = DataAnalysis.class.getResourceAsStream(fileName );
 		Reader in = new InputStreamReader(is); 	
    	 */
    	
    	
    	// opcion 5 
   		String fileName= "co_1980_alabama.csv"; 
   		InputStream is = DataAnalysis.class.getClassLoader().getResourceAsStream(fileName);
   		Reader in = new InputStreamReader(is); 			
    	
    	
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
	      .withFirstRecordAsHeader()
	      .parse(in);
	    
	    // imprimimos los registros con todos sus valores
	    for (CSVRecord record : records) {
	        String value = record.get("daily_max_8_hour_co_concentration");
	         System.out.println(String.format("%s, %s", value, record.toString()));
	    
	    }

	    in.close();
   
    }

  
}
