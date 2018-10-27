package data_tier;

import java.util.Properties;

public class Config {

	Properties configFile;

	public Config(String location) {

		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().
			getResourceAsStream(location));
		} catch(Exception eta) {
			eta.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

}
