import org.apache.log4j.Logger;
import org.sonar.template.java.checks.myException;

public class logTCheckFile {
	private static Logger logger = Logger.getLogger(logTCheckFile.class);
	public void loggingWithID(String nonsense) throws myException{
		logger.error("errorID:20160801 this is an error");
		return;
	}

	public void loggingWithoutID(String nonsens){
		try{
			logger.error("this is an error");
		}catch(NullPointerException e){
			logger.error("what",e);
		}
		return;
	}
	
	public void specific(){// Noncompliant {you didn't print the exception in the log}
		logger.error("only the logger");
		try{
			logger.error("this is an error");
		}catch(NullPointerException e){
			logger.error("without an exception");
		}
		return;
	}


}
