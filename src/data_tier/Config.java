package data_tier;

import java.util.Properties;

public class Config {

	Properties configFile;

	public Config() {
		
		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/main.cfg"));
		} catch(Exception eta) {
			System.out.print("Config file won't load!\n");
			eta.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}

}
