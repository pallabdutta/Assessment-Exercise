package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigRead {

	Properties prop;
	
	public ConfigRead() throws Exception {
		
		FileInputStream fis = new FileInputStream("./testdata/config.properties");
		
		prop = new Properties();
		
		prop.load(fis);	
		
	}
	
	public String get_ServiceNow_App() {
		return prop.getProperty("appURL_ServiceNow");
	}
		
	public String getBrowser() {
		return prop.getProperty("browser");
	}
	
	public String getUsername() {
		return prop.getProperty("username");
	}
	
	public String getPassword() {
		return prop.getProperty("password");
	}
	
	
}
