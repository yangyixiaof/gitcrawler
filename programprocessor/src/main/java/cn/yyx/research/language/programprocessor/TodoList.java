package cn.yyx.research.language.programprocessor;

public class TodoList {
	// TODO 1. how to distinguish anti-pair and similar-pair.
	
	// TODO allow connected fields or methods such as: a.b().c.d.g() is allowed now.
	// TODO allow multiple same operations such as: ... + ... + ... + ... is allowed now.
	// TODO declaration and assignment should be separated such as int[] a = new int[100] should be as int[] \skip c0/0=new int[@NUB].
	// TODO assignment should endure a bit longer left operand and right operand.
	// TODO I should extract some big analysis type such as Assignment | MethodInvocation | ArithmaticOperation.
	// TODO how can n-gram possibility transfer to next?
	
	// TODO important : test the case : int a[] = new int[100].
	
	// TODO class declaration ReferenceLibrary.ClassDeclare is not checked.
	// TODO label declaration ReferenceLibrary.LabelDeclare is not checked.
	// TODO different situation of for must be tested.
	
	// Declaration Code should be more compact. The handles of content holder are not right. Solved.
	// Some GetDataOffset doesn't call DataNewlyUsed. Some should but some should not. Solved.
	// Some level offset is not right. Solved.
	// when multiple ContentHolder, how to solve it? Only to append recursively.
	// the field access should be more compact. Solved. Just so so.
	// need to change allnum in JCScope to map. Solved.
	// instanceof and cast have peoblems. Solved.
	
	// InfixExpression should be improved, case AlgThread. Solved.
	// Field access should be more compact. Solved. Just so so. 
	// comphole should be removed at maximum cancellation. Solved.
	// a mechanism which append some extra type hint is needed.
}