package data_tier;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {

	Properties configFile;

	public Config() {

		configFile = new java.util.Properties();
		try {

			InputStream is = new FileInputStream("config/main.properties");
			configFile.load(is);
			// configFile.list(System.out);

		} catch(Exception e) {
			DataLogger.log("Config file won't load!\n");
			DataLogger.errorLog(e);
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

}
