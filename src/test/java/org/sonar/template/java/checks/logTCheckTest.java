package org.sonar.template.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

public class logTCheckTest{
	@Test
	public void test() {
	  JavaCheckVerifier.verify("src/test/files/logTCheckFile.java", new logTCheck());
	}
	
//	@Test
//	public void test2(){
//	  JavaCheckVerifier.verify("src/test/files/logTCheck2.java", new logTCheck());
//	}
}
