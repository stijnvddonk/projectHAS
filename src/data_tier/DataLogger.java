package data_tier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import logic_tier.*;

public class DataLogger {

	//static String sessionName = User.sessionString();
	private static final String filename = logName() + ".txt";

	public static String logName() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = "Log ";
		return name + timeStamp;
	}

	public static void log(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/normal/" + name);
			file.createNewFile();
			if(file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg);
				out.close();
			}

		} catch (Exception e) {
			errorLog(e);
		}
	}

	public static void errorLog(Exception error) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/Error/" + name);
			file.createNewFile();
			if(file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + error);
				out.close();
			}
		} catch (Exception e) {
			DataLogger.log("error\n");
		}
	}
}
