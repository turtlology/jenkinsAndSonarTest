import org.apache.log4j.Logger;
import org.sonar.template.java.checks.logTCheck;

public class methodToLog {
	public void onlyLog(String message){
		Logger log = Logger.getLogger(methodToLog.class); // Noncompliant{{you shouldn't write a method only to print the log}}
		String print = "only contains a log";
		print+="here is the message"+message;
		log.info(print);
	}
	
	public void Nologs(String nonsense){
		int i = 1+1;
	}
	
}
