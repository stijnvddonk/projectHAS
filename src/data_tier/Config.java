package data_tier;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

	Properties configFile;

	public Config() {

		configFile = new java.util.Properties();
		try {
//			InputStream is = new FileInputStream("main.properties");
//			configFile.load(is);
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("resource/main.properties");
            configFile.load(is);

		} catch(Exception e) {
			DataLogger.systemLog("Config file won't load!\n");
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

}
