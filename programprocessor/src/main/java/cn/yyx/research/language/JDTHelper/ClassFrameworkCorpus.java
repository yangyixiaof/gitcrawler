package cn.yyx.research.language.JDTHelper;

public class ClassFrameworkCorpus {

	/*@SuppressWarnings("unchecked")
	public static CorpusContentPair GenerateClassFrameworkCorpus(CompilationUnit compilationUnit) {
		String Corpus = "BigClassFrameWork";
		StringBuilder Content = new StringBuilder("");
		List<TypeDeclaration> typeDeclarations = compilationUnit.types();
		for (Object object : typeDeclarations) {
			TypeDeclaration clazzNode = (TypeDeclaration) object;
			Content.append(clazzNode.isInterface() ? ("interface ") : ("class ") + CamelCaseSplitter.SplitWordF(clazzNode.getName().toString().trim(), "c#"));
			FieldDeclaration[] fields = clazzNode.getFields();

			for (FieldDeclaration fieldDeclaration : fields) {
				Type fieldType = fieldDeclaration.getType();
				Content.append(" f#" + fieldType);
			}
			
			MethodDeclaration[] methods = clazzNode.getMethods();
			for (MethodDeclaration method : methods) {
				Content.append(" " + CamelCaseSplitter.SplitWordF(method.getName().toString(), "m#"));
			}
			
			Content.append(".");
			
			for (FieldDeclaration fieldDeclaration : fields) {
				Content.append(" ft#"+fieldDeclaration.getType());
				List<VariableDeclarationFragment> fs = fieldDeclaration.fragments();
				for (VariableDeclarationFragment vdf : fs)
				{
					Content.append(" ff#"+vdf);
				}
				Content.append(".");
			}
			
			for (MethodDeclaration method : methods) {
				Content.append(" mr#"+method.getReturnType2()+" "+CamelCaseSplitter.SplitWordF(method.getName().toString(), "mn#"));
				List<SingleVariableDeclaration> params = method.parameters();
				for (SingleVariableDeclaration pa : params)
				{
					Content.append(" mp#"+pa.getType());
				}
				Content.append(".");
			}
		}
		String Contentstr = Content.toString();
		return new CorpusContentPair(Corpus, Contentstr);
	}*/
	
}
