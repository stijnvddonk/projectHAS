package data_tier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataLogger {

	// static String sessionName = Company.sessionString();
	private static final String filename = logName() + ".txt";

	public static String timeStamp() {
		LocalDateTime ldt = LocalDateTime.now().plusDays(1);
		DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss", Locale.ENGLISH);
		String formatter = formmat1.format(ldt);
		return formatter;
	}

	public static String logName() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = "Log ";
		return name + timeStamp;
	}

	public static void systemLog(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/system/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg + "\n");
				out.close();
			}

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void deviceLog(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/device/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg + "\n");
				out.close();
			}
		} catch (Exception e) {
			DataLogger.systemLog("error");
		}
	}

	public static void userLog(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/user/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg + "\n");
				out.close();
			}
		} catch (Exception e) {
			DataLogger.systemLog("error");
		}
	}

	public static void apiLog(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/api/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg + "\n");
				out.close();
			}

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static void serverLog(String msg) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
		String name = filename;
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			file = new File("log/server/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + msg + "\n");
				out.close();
			}

		} catch (Exception e) {
			System.out.print(e);
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
			file = new File("log/error/" + name);
			file.createNewFile();
			if (file.exists()) {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.write(timeStamp + ": " + error + "\n");
				out.close();
			}
		} catch (Exception e) {
			DataLogger.systemLog("error");
		}
	}
}
