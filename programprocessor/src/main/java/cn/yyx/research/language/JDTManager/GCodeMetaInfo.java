package cn.yyx.research.language.JDTManager;

public class GCodeMetaInfo {
	
	public static String ArrayInitial = "@ARI";
	public static String ArrayCreation = "@ARC";
	
	public static String StringHolder = "@STR";
	public static String NumberHolder = "@NUB";
	
	public static String NoStatement = "nu#";
	public static String CommonEnd = "#";
	
	public static int OutofScopeVarOrObject = -10;
	public static String OutofScopeDesc = "$FDC#";
	//public static String OutofScopeDesc = "$INF#";
	
	//for field only which means data only.
	public static int IsField = -9;
	public static String IsFieldDesc = "$FREF#";
	
	// corpus name
	public static String NumberCorpus = "BigNumberDetail";
	public static String StringCorpus = "BigStringDetail";
	public static String LogicCorpus = "BigClassDetail";
}
