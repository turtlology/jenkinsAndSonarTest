package org.sonar.template.java.checks;

import java.util.List;

import org.apache.log4j.Logger;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

import com.google.common.collect.ImmutableList;
@Rule(
		  key = "methodUsingToLog",
		  name = "Method with the only function to log should be avoid",
		  description = "test if a method have less than 10 lines and the only function is to print the log",
		  priority = Priority.CRITICAL,
		  tags = {"bug"})
public class methodUsingToLog extends	IssuableSubscriptionVisitor{
	Logger log = Logger.getLogger(methodUsingToLog.class);
	boolean needToScan = false;

	public List<Kind> nodesToVisit() {
//		 TODO Auto-generated method stub
		return ImmutableList.of(Kind.METHOD,Kind.METHOD_INVOCATION);
	}
	
	@Override
	public void visitNode(Tree tree){
		if(tree.is(Kind.METHOD)){
			MethodTree method = (MethodTree) tree;
			if(method.block().body().size()<10){
				needToScan=true;
			}
		}else{
			MethodInvocationTree mit = (MethodInvocationTree) tree;
			if(needToScan){
				if(mit.parent().is(Kind.VARIABLE)){
					VariableTree vt = (VariableTree)mit.parent();
					if(vt.type().toString().equals("Logger")){
						reportIssue(mit, "you shouldn't write a method only to print the log");
					}
				}
			}
		}
	}
}
