package cn.yyx.research.language.programprocessor;

public class TodoList {
	// TODO 1. how to distinguish anti-pair and similar-pair.
	
	// Solved. for every prediction, the handle must hold update the variable and update the variable order after the prediction block is over. To do this, a queue is needed.
	// I mean for (...) especially.
	
	// not only data, same question for type in above problem. for now type has no such problem.
	
	// scope data offset should consider back to the initial order when entering anonymous class ... ...
	// This need is not necessary because there must be only final use in anonymous class.
	
	// what is ' ? solved due to ' '.
	// what is ') ? solved due to ' ').
	// temporarily solved: some doesn't have hint.
	// temporarily solved: ZTSERVERS.Constants+,@HO some holes are behind endOfStatement.
	// MI@EntityItem(new,worldObj,.5,.5,.5,$C0?0) what is the problem ? To tell the truth, there is no problem.
			
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
	
	
	// solved: AnnotationTypeMemberDeclaration is not handled.
	// AnnotationTypeMemberDeclaration:
		//   [ Javadoc ] { ExtendedModifier }
		 //      Type Identifier ( ) [ default Expression ] ;
}