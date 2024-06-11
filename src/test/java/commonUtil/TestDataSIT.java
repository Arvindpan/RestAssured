package commonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestDataSIT {
	public static Properties property;
	private static String configpath="C:\\Users\\nutri\\RestAssured\\RestAPI\\src\\test\\java\\commonUtil\\TestDataSIT.java";
	public static void initializePropertyfile()
	{
		property= new Properties();
			try {
				FileInputStream istm = new FileInputStream(configpath);
				property.load(istm);
				System.out.println("test is on 2");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
