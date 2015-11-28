package cn.yyx.research.language.JDTHelper;

import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.StringLiteral;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;

public class ForwardMethodCodeGenerateASTVisitor extends MyCodeGenerateASTVisitor{

	public ForwardMethodCodeGenerateASTVisitor(MyPreProcessASTVisitor mppast) {
		super(mppast);
	}
	
	@Override
	public boolean visit(StringLiteral node) {
		// System.out.println("StringLiteral:"+node);
		AppendOtherCode(GCodeMetaInfo.StringCorpus, node.toString());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(NumberLiteral node) {
		// System.out.println("NumberLiteral:"+node);
		AppendOtherCode(GCodeMetaInfo.NumberCorpus, node.toString());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(CharacterLiteral node) {
		// System.out.println("CharacterLiteral:"+node);
		AppendOtherCode(GCodeMetaInfo.StringCorpus, node.toString());
		return super.visit(node);
	}
	
}