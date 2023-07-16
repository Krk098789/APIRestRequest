package org.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Baseclass {
	public static String getproperties(String key) throws IOException 
	{
		FileInputStream file = new FileInputStream("src\\test\\resources\\PropertyFile\\Data.properties");
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(key);
		

	}

}
