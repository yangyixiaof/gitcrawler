package cn.yyx.contentassist.codeutils;

public class aaaaImportantMustTodo {
	
	// Solved. some rule adds more conditions, must be handled. such superFieldAccess not exists in previous version.
	
	// Solved. needs to handle java varargs: Object... as Object[].
	
	// Solved. DH@then and DH@else are not handled.
	
	// Solved. finallyStatement : 'DH@finally' are not handled.
	
	// Solved. tryStatement : 'DH@try' are not handled.
	
	// Solved. chainFieldAccess | commonFieldRef | thisFieldAccess | referedFieldAccess are not handled.
	
	// Solved. addPrefixExpression | subPrefixExpression changed two places.
	
	// Solved. normalLiteralStatement | negativeLiteralStatement | positiveLiteralStatement are not handled.
	
	// Solved. lambdaExpressionStatement has changed to two situations.
	
	// Solved.	methodInvocationStatement add three new sub instance.
	
	// Solved. methodReferenceStatement add a new sub instance.
	
	// Solved. argTypeList adds lastArgType, due to only last of method declaration is handled, the modifications of ScopeOffsetHandler do not need to be considered.
	
	// TODO List<SingleVariableDeclaration> params = node.parameters();
	//		for (SingleVariableDeclaration svd : params)
	//		{
	//		}
	// Such type infer must be handled as svd should be SingleVariableDeclaration. This needs to be tested.
	
	// Solved. all refered_ expression should handle different statement.
	
	// Solved. very important!!!!!! pre-exist. to set mostfar and nextstart are in choas. some mostfar in some situation doesn't set. nextstart start from Ps Pr or real data? what if there is some error I mean just directly to the head.
	
	// Rejected, not include java8 MethodReference A::b. what is the exactly infer form, this needs to be checked.
	
	// TODO Interface of ReferedExpression HandleInferredField and HandleInferredMethodReference are not handled. Mainly MethodReference and FieldAccess.
	
	// Solved. if while and do-while enhanced-for needs to be like for.
	
	// TODO multiple while situation in good java file needs to be tested.
	
	// TODO The termination condition in pre try predict must be the same as code synthesis, whcih means HandleOverSignal returns true, the termination condition must also return true.
	
	// Solved. Complex type should be resolved one level by level. This is resolved by level. Especially, ParameterizedType as List<String> can not be resolved but List can.
	
	// Solved. the handle of @HO can only be in the very end. remember to handle @HO at very end. No need to do that, array start and array end signal has solved the problem.
	// be sure this happens: A@$C0?0=@HO IxE@1+@HO. Not this: A@$C0?0=@HO IxE@1+2 IxE@@pre+2.
	// Such as : A@$C0?0=@HO m() ma(@pre).
	
	// Solved. what is exactly the field offset? Now just the declaration position.
	
	// Solved what is behind enumConstantDeclarationStatement, can that cause stopping? If not, change that. This is very important. Add 'DH@E,' as the end signal.
	
	// method arg split should use DH@@, instead of DH@, to distinguish with common , Oh, this should be ignored.
	
	// Solved. Question: int a[][][] = new int[][][]; | int a[][][] = {......};the [][][] behind new should be considered. This needs the whole effort. But it may be included in ArrayType. ArrayClassCreation just handles it.
	// Solved. reminder to add '{' and '}' to the begin and end of type declaration. There is no need to do that.
	
	// Solved. if has hint, must handled directly add the modified to result. This is handled in the new mechanism.
	
	// Solved. int[] when inferring specification, [] should be eliminated, this must be considered.
	
	// Solved. int must be translated to Integer. boolean float double char are the same. This must be done in program-processor phase. Not done in program-processor phase but handled in the plugin.
	
	// Solved. anonymousBegin need to be added to sequence. No problem.
	
	// Solved. method declaration should add return type.
	
	// Solved. a[a[]] need to be tested.
	
	// Solved. cstack must not set the structure info to null, the whole judge schema should be changed. Now changed to all-search.
	
	// Solved. field declare not handled.
}