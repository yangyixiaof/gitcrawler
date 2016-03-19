package cn.yyx.research.language.Utility;

public class WarningUtil {
	
	public static void DataNoRefKindCheckAndPrint(String data, String description)
	{
		if (Character.isLowerCase(data.charAt(0))==true)
		{
			MyLogger.Error("Warning data: " + data + " : " + description + " : here is JCScope, the type of data is not declared or assigned. The system will exit. This has be improved in the future to get better compatibility.");
		}
	}
	
}