
import java.io.IOException;
import java.util.Properties;

public class Utils {
	private static Properties properties = new Properties();

	static 
	{
		String fileName;
		fileName= "config.txt";
		 
	    try 
	    {
			 // opcion 1
//			 properties.load(Utils.class.getClass().getResourceAsStream(fileName) );
			  // opcion 2 fileName= "/config.txt";
//			  properties.load(Utils.class.getResourceAsStream(fileName) );
			 // opcion 3
			 properties.load(Utils.class.getClassLoader().getResourceAsStream(fileName));

	    }
	    catch (IOException e) {
	    	 System.out.println(e);
	    }
	}
	
	public static String getDir()
	{
		return properties.getProperty("dir");
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(Utils.getDir());
	
	}

}