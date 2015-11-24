package cn.yyx.research.language.JDTHelper;

import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.Utility.CorpusContentPair;

public class ClassLogicDetailCorpus {
	
	@SuppressWarnings("unchecked")
	public static CorpusContentPair GenerateClassDetailCorpus(CompilationUnit compilationUnit)
	{
		StringBuilder Content = new StringBuilder("");
		List<TypeDeclaration> typeDeclarations = compilationUnit.types();
		for (Object object : typeDeclarations) {
			TypeDeclaration clazzNode = (TypeDeclaration) object;
			
			//testing
			//System.out.println("ClassName:"+clazzNode.getName());
			
			/*MethodDeclaration[] methods = clazzNode.getMethods();
			for (MethodDeclaration method : methods) {
				Block mbody = method.getBody();
				StringBuilder onemethod = new StringBuilder("");
				mbody.accept(new ForwardMethodASTVisitor(onemethod));
				Content.append(onemethod);
			}*/
			ForwardMethodASTVisitor fmastv = new ForwardMethodASTVisitor();
			clazzNode.accept(fmastv);
			Content.append(fmastv.GetGeneratedCode());
		}
		String Contentstr = Content.toString().trim();
		//testing
		//System.out.println(Contentstr);
		return new CorpusContentPair(GCodeMetaInfo.LogicCorpus, Contentstr);
	}
	
}