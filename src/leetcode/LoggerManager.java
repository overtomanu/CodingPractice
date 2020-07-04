package leetcode;
import java.io.IOException;
//import java.util.logging.ConsoleHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerManager
{
	static private ConsoleHandler   consoleHandler;
	static private FileHandler		fileTxt;
	static private SimpleFormatter	formatterTxt;

	static private FileHandler		fileHTML;
	static private Formatter		formatterHTML;
	static public Level				logLevel = Level.ALL;			

	static
	{
		try
		{
			fileTxt = new FileHandler("Logging.txt");
			fileHTML = new FileHandler("Logging.html");
			consoleHandler = new ConsoleHandler();
		}
		catch (SecurityException e)
		{
			System.out.println("Unable to set fileHandler for logger\n" + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("Unable to set fileHandler for logger\n" + e.getMessage());
		}
	}

	static public Logger getLogger(String name)
	{
		// Create Logger
		Logger logger = Logger.getLogger(name);
		logger.setLevel(logLevel);
		logger.setUseParentHandlers(false);
		
		// Create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		fileTxt.setLevel(logger.getLevel());
		//logger.addHandler(fileTxt);

		fileHTML.setLevel(logger.getLevel());
		logger.addHandler(fileHTML);
		logger.addHandler(fileTxt);
		logger.addHandler(consoleHandler);
		return logger;
	}

	public static Level getJavaLogLevelObject(String level)
	{
		 
		if(level.equalsIgnoreCase("INFO"))
			return Level.INFO;
		else if(level.equalsIgnoreCase("ALL"))
			return Level.ALL;
		else if(level.equalsIgnoreCase("FINE"))
			return Level.FINE;
		else if(level.equalsIgnoreCase("FINEST"))
			return Level.FINEST;
		else if(level.equalsIgnoreCase("OFF"))
			return Level.OFF;
		else if(level.equalsIgnoreCase("SEVERE"))
			return Level.SEVERE;
		else if(level.equalsIgnoreCase("WARNING"))
			return Level.WARNING;
		else
			return Level.INFO;
	}

	public static void setLogLevel(Level logLevel)
	{
		LoggerManager.logLevel = logLevel;
	}
}