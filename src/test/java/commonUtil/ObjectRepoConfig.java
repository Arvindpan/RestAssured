package commonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ObjectRepoConfig {
	public static Properties property;
	private static String configpath="C:\\Users\\nutri\\RestAssured\\RestAPI\\src\\test\\java\\ObjectRepo.properties";
	public static void initializeObjectRepofile() {
		property= new Properties();
			try {
				FileInputStream istm = new FileInputStream(configpath);
				property.load(istm);
				System.out.println("this is testing");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
}
