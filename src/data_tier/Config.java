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
			configFile.list(System.out);

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
