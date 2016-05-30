package cn.yyx.research.language.JDTHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;

import cn.yyx.research.language.Utility.CorpusContentPair;
import cn.yyx.research.language.simplified.JDTHelper.SimplifiedCodeGenerateASTVisitor;
import cn.yyx.research.language.simplified.JDTManager.ContentsAndWords;
import cn.yyx.research.language.simplified.JDTManager.TypeASTHelper;

public class ClassLogicDetailCorpus {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<CorpusContentPair> GenerateClassDetailCorpus(CompilationUnit compilationUnit)
	{
		List<AbstractTypeDeclaration> typeDeclarations = compilationUnit.types();
		Map<String, ContentsAndWords> allcodemap = new TreeMap<String, ContentsAndWords>();
		for (AbstractTypeDeclaration object : typeDeclarations) {
			AbstractTypeDeclaration clazzNode = (AbstractTypeDeclaration) object;
			if (TypeASTHelper.IsEmptyTypeDeclaration(clazzNode))
			{
				continue;
			}
			SimplifiedCodeGenerateASTVisitor fmastv = new SimplifiedCodeGenerateASTVisitor();
			clazzNode.accept(fmastv);
			Map<String, ContentsAndWords> codemap = fmastv.GetGeneratedCode();
			Set<String> keys = codemap.keySet();
			Iterator<String> itr = keys.iterator();
			while (itr.hasNext())
			{
				String corpus = itr.next();
				if (allcodemap.get(corpus) == null)
				{
					allcodemap.put(corpus, new ContentsAndWords("", 0));
				}
				ContentsAndWords value = codemap.get(corpus);
				ContentsAndWords alc = allcodemap.get(corpus);
				allcodemap.put(corpus, new ContentsAndWords(alc.getContent() + value.getContent(), alc.getWords() + value.getWords()));
			}
			codemap = null;
		}
		ArrayList<CorpusContentPair> result = new ArrayList<CorpusContentPair>();
		Set<String> keys = allcodemap.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext())
		{
			String corpus = itr.next();
			ContentsAndWords content = allcodemap.get(corpus);
			result.add(new CorpusContentPair(corpus, content.getContent(), content.getWords()));
		}
		
		// clear variables
		allcodemap = null;
		return result;
	}
	
}