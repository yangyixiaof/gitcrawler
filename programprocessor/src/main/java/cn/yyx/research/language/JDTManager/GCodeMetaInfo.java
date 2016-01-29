package cn.yyx.research.language.JDTManager;

public class GCodeMetaInfo {
	
	public static final String AnonymousClassHintStatement = "@HT";
	public static final String ClassDeclarationHint = "@CD";
	public static final String MethodDeclarationHint = "@MD";
	public static final String LabelDeclarationHint = "@LD";
	public static final String VariableDeclarationHint = "@VD";
	
	public static final String DescriptionHint = "@DH";
	
	public static final String LeftParenthese = "@L";
	public static final String RightParenthese = "@R";
	
	public static final String VariableDeclarationHolder = "@VH";
	
	// another display of ';'
	public static final String EndOfAStatement = "@ED";
	// another display of ',' ')'
	public static final String EndOfAPartialStatement = "@PD";
	
	public static final String ArrayAccess = "@AC";
	// another display of ']'
	public static final String ArrayDeclarationIndexExpressionEnd = "@AD";
	
	public static final String ArrayInitial = "@ARI";
	
	public static final String ArrayCreation = "@ARC";
	
	public static final String StringHolder = "@STR";
	public static final String NumberHolder = "@NUB";
	public static final String CharHolder = "@CHR";
	
	// public static String NoStatement = "nu#";
	public static final String NullLiteral = "@NUL";
	public static final String CommonSplitter = "#";
	
	public static final String NoDeclaredType = "@NT";
	
	public static final String CodeHole = "@HO";
	public static final String PreExist = "@PE";
	
	// The white space in code is replaced with '#'.
	public static final String WhiteSpaceReplacer = "#";
	
	public static final String OffsetSpiliter = "?";
	
	public static final String ContentHolder = "<!%CH!>";
	
	public static final String DataRefIndicator = "$";
	
	public static final String HackedNoType = "<$NT$>";
	
	// public static int OutofScopeVarOrObject = -10;
	// public static String FirstDeclaredData = "F";
	// public static String OutofScopeDesc = "$INF";
	
	//for field only which means data only.
	//public static int IsField = -9;
	//public static String IsFieldDesc = "$FREF#";
	
	// corpus name
	public static final String EnumCorpus = "BigEnumDetail";
	public static final String NumberCorpus = "BigNumberDetail";
	public static final String StringCorpus = "BigStringDetail";
	public static final String CharCorpus = "BigCharDetail";
	public static final String LogicCorpus = "BigClassDetail";
	public static final String AnonymousLogicCorpus = "BigAnonymousClassDetail";
}
