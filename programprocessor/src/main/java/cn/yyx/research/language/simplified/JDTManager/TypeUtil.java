package cn.yyx.research.language.simplified.JDTManager;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.WildcardType;

import cn.yyx.research.language.JDTManager.GCodeMetaInfo;
import cn.yyx.research.language.Utility.StringUtil;

public class TypeUtil {
	
	public static String TypeCode(Type node, boolean simplified) {
		if (node == null) {
			System.err.println("Null Type? What the fuck!");
			new Exception("Null Type").printStackTrace();
			System.exit(1);
		}
		if (node instanceof PrimitiveType) {
			return node.toString();
		}
		// System.out.println("RawTypeCode:"+node);
		String type = RawTypeCode(node, null, simplified);
		// System.out.println("TypeCode:"+type);
		/*String typecode = GetClassOffset(type);
		if (typecode == null) {
			typecode = type;
		}*/
		return type; // typecode
	}

	@SuppressWarnings("unchecked")
	public static String RawTypeCode(Type node, String parameterized, boolean simplified) {
		
		// System.out.println("Type:" + node);
		// System.out.println("TypeClass:" + node.getClass());
		
		String result = "";
		if (node instanceof PrimitiveType) {
			String code = ((PrimitiveType) node).toString().trim();
			int widx = code.lastIndexOf(' ');
			result = code.substring(widx + 1);
		}
		if (node instanceof SimpleType) {
			result = GetFirstElementName(((SimpleType) node).getName());
			// GetStrictedName(((SimpleType) node).getName(),
			// StrictedTypeLength)
		}
		if (node instanceof QualifiedType) {
			QualifiedType qn = (QualifiedType) node;
			String qnname = qn.getName().toString();
			if (parameterized != null) {
				qnname += parameterized;
			}
			result = qnname + "." + RawTypeCode(qn.getQualifier(), null, simplified);
		}
		if (node instanceof NameQualifiedType) {
			NameQualifiedType nt = (NameQualifiedType) node;
			result = nt.getName().toString() + "." + GetFirstElementName(((NameQualifiedType) node).getQualifier());
			// + GetStrictedName(((NameQualifiedType) node).getQualifier(),
			// StrictedTypeLength - 1);
		}
		if (node instanceof WildcardType) {
			WildcardType wt = (WildcardType) node;
			if (wt.getBound() == null) {
				return "?";
			}
			result = "?" + GCodeMetaInfo.WhiteSpaceReplacer + (wt.isUpperBound() ? "extends" : "super")
					+ GCodeMetaInfo.WhiteSpaceReplacer + RawTypeCode(wt.getBound(), null, simplified);
		}
		if (node instanceof ArrayType) {
			ArrayType at = (ArrayType) node;
			int dimens = at.dimensions().size();
			String dimenstr = StringUtil.GenerateDuplicates("[]", dimens);
			/*
			 * for (int i = 0; i < dimens; i++) { dimenstr += "[]"; }
			 */
			result = RawTypeCode(at.getElementType(), null, simplified) + dimenstr;
		}
		if (node instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) node;
			String param = "<";
			List<Type> tas = pt.typeArguments();
			Iterator<Type> itr = tas.iterator();
			while (itr.hasNext()) {
				Type tt = itr.next();
				param += RawTypeCode(tt, null, simplified);
				if (itr.hasNext()) {
					param += ",";
				}
			}
			param += ">";
			result = RawTypeCode(pt.getType(), param, simplified);
		}
		if (node instanceof UnionType) {
			UnionType ut = (UnionType) node;
			List<Type> types = ut.types();
			Iterator<Type> itr = types.iterator();
			boolean first = true;
			while (itr.hasNext()) {
				Type t = itr.next();
				String tstr = RawTypeCode(t, null, simplified);
				if (first) {
					result = tstr;
					first = false;
				} else {
					result = result + "|" + tstr;
				}
			}
		}
		if (node instanceof IntersectionType) {
			IntersectionType ut = (IntersectionType) node;
			List<Type> types = ut.types();
			Iterator<Type> itr = types.iterator();
			boolean first = true;
			while (itr.hasNext()) {
				Type t = itr.next();
				String tstr = RawTypeCode(t, null, simplified);
				if (first) {
					result = tstr;
					first = false;
				} else {
					result = result + "&" + tstr;
				}
			}
		}
		if (!simplified && parameterized != null)
		{
			result += parameterized;
		}
		return result;
		
		// System.err.println("Uncognized Type node.");
		// System.exit(1);
		// return null;
	}
	
	private static String GetFirstElementName(Name name) {
		if (name instanceof QualifiedName) {
			return ((QualifiedName) name).getName().toString();
		}
		if (name instanceof SimpleName) {
			return name.toString();
		}
		System.err.println("Name is not QualifiedName or SimpleName, what is that?");
		System.exit(1);
		return null;
	}
	
}