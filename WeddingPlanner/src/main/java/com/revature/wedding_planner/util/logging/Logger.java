package com.revature.wedding_planner.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Logger {

	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RESET = "\u001B[0m";
	private static Date date;

	private static Logger logger;
	private final boolean printToConsole;

	private Logger(boolean printToConsole) {
		this.printToConsole = printToConsole;
	}

	public static Logger getLogger(boolean printToConsole) {
		// Lazy singleton
		if (logger == null) {
			logger = new Logger(printToConsole);
		}

		return logger;
	}

	public void log(String message) {
		try (Writer logWriter = new FileWriter("src/main/resources/wedding-planner.log", true);) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			
			date = new Date();
			logWriter.write(formatter.format(date) + " " + message + "\n");

			if (printToConsole) {
				System.out.println(ANSI_GREEN + message + ANSI_RESET);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
