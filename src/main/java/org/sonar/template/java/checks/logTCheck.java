package org.sonar.template.java.checks;

import java.util.List;

import org.apache.log4j.Logger;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TryStatementTree;

import com.google.common.collect.ImmutableList;


@Rule(
		  key = "logTCheck",
		  name = "Exception without throws and without printing it in the log should be avoid",
		  description = "check if the method caught an exception and doesn't throws and doesn't print it in the log.",
		  priority = Priority.CRITICAL,
		  tags = {"bug"})
//public class logTCheck extends BaseTreeVisitor implements JavaFileScanner{
public class logTCheck extends	IssuableSubscriptionVisitor {
	
	
	Logger log = Logger.getLogger(logTCheck.class);

	@Override
	public List<Kind> nodesToVisit() {
//		 TODO Auto-generated method stub
		return ImmutableList.of(Kind.METHOD);
	}
	
	@Override
	public void visitNode(Tree tree){
//		ExpressionTree exp = (ExpressionTree) tree;
//		log.info(exp.kind());

//		VariableTree var = (VariableTree) tree;
//		log.info(var.simpleName());
		
//		MethodInvocationTree  method_invoc = (MethodInvocationTree) tree;
//		log.info(method_invoc.symbol());
		
		MethodTree method = (MethodTree) tree;
		if(method.throwsClauses().size()==0){
			log.info("this method does not have a throw clause");
			BlockTree bt = method.block();
			for(StatementTree st:bt.body()){
				if(st.is(Kind.TRY_STATEMENT)){
					TryStatementTree tst = (TryStatementTree) st;
					for(CatchTree ct:tst.catches()){
						for(StatementTree state:ct.block().body()){
							ExpressionStatementTree ex = (ExpressionStatementTree)state;
							MethodInvocationTree mit = (MethodInvocationTree) ex.expression();
							if(mit.arguments().size()!=2){
								log.error(method.simpleName());
							    reportIssue(method.simpleName(), "you didn't print the exception in the log");
							}
						}
					}
				}
			}
		};
//		
		
		/*check if there is a method only print log*/
//		log.info(method.modifiers().get(0).kind());
//		BlockTree bt = method.block();
//		for(StatementTree stree:bt.body()){
//			ExpressionStatementTree est = (ExpressionStatementTree)stree;
//			
//		}
		
		/*a very simple example of how to develope the rules*/
//		MethodTree method = (MethodTree) tree;
//		if (method.parameters().size() == 1) { 
//			MethodSymbol symbol = method.symbol();
//			Type firstParameterType = symbol.parameterTypes().get(0);
//		    Type returnType = symbol.returnType().type();
//		    if (returnType.is(firstParameterType.fullyQualifiedName())) {
//		    	log.info(method.simpleName());
//		    	reportIssue(method.simpleName(), "Never do that!");
//		    }
//		}
//		
//		
	}
//	private JavaFileScannerContext context;
////
//	@Override
//	  public void scanFile(JavaFileScannerContext context) {
//	    this.context = context;
//
//	    // The call to the scan method on the root of the tree triggers the visit of the AST by this visitor
//	    scan(context.getTree());
//
//	    // For debugging purpose, you can print out the entire AST of the analyzed file
////	    log.info(context.getTree());
//
//	  }
//	
//	  @Override
//	  public void visitIdentifier(IdentifierTree tree) {
//		log.info(tree.symbol().type());
//	    scan(tree.annotations());
//	  }
//	
//	  @Override
//	  public void visitExpressionStatement(ExpressionStatementTree tree) {
//		  log.info(tree.semicolonToken());
//	    scan(tree.expression());
//	  }
//	
//	  @Override
//	  public void visitBlock(BlockTree tree) {
//		  log.info(tree.body().get(0));
//	    scan(tree.body());
//	  }


}
