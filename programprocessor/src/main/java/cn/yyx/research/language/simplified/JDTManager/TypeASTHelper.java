package cn.yyx.research.language.simplified.JDTManager;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeASTHelper {
	
	@SuppressWarnings("unchecked")
	public static boolean IsEmptyTypeDeclaration(AbstractTypeDeclaration clazzNode)
	{
		boolean isempty = true;
		List<BodyDeclaration> bs = clazzNode.bodyDeclarations();
		Iterator<BodyDeclaration> itr = bs.iterator();
		while (itr.hasNext())
		{
			BodyDeclaration bd = itr.next();
			if (bd instanceof AnnotationTypeMemberDeclaration || bd instanceof EnumConstantDeclaration || bd instanceof FieldDeclaration || bd instanceof Initializer || bd instanceof MethodDeclaration)
			{
				isempty = false;
				break;
			}
			if (bd instanceof AnnotationTypeDeclaration || bd instanceof EnumDeclaration || bd instanceof TypeDeclaration)
			{
				boolean resempty = IsEmptyTypeDeclaration((AbstractTypeDeclaration)bd);
				if (!resempty)
				{
					break;
				}
	 			
			}
		}
		return isempty;
	}
	
}