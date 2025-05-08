package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjects {

	Properties prop=new Properties();
	
	public Properties getObjectRepo() throws Exception {
		
		InputStream f=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\java\\objects\\objects.properties"));
		prop.load(f);
		return prop;
	}
	
}
