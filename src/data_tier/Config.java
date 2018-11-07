package data_tier;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	Properties configFile;

	public Config() {

		configFile = new java.util.Properties();
		try {
			InputStream inputStream = Config.class.getResourceAsStream("/resource/main.properties");
			configFile.load(inputStream);

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
