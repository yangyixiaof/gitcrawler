package cn.yyx.research.language.JDTManager;

public class KindLibrary {
	
	public static KindHint GetManagerLevelHintForKind(String kind)
	{
		switch (kind) {
		case VVarObjPoolManager.ConstantPool:
		case VVarObjPoolManager.VarOrObjPool:
			return new KindHint(0, 1);
		case VHiddenClassPoolManager.ClassHiddenPool:
			return new KindHint(2, 2);
		case VLabelPoolManager.LabelPool:
			return new KindHint(3, 3);
		default:
			System.err.println("No such kind manager, the system will exit.");
			System.exit(1);
			break;
		}
		return null;
	}
	
	
	public static String GetCorrespondKindString(Integer kind) {
		switch (kind) {
		case 2^0:
			return VVarObjPoolManager.ConstantPool;
		case 2^1:
			return VVarObjPoolManager.VarOrObjPool;
		case 2^2:
			return VHiddenClassPoolManager.ClassHiddenPool;
		case 2^3:
			return VLabelPoolManager.LabelPool;
		default:
			System.err.println("Not only one kind, error happens, the system will exit.");
			System.exit(1);
			break;
		}
		return null;
	}

	public static Integer ExtractKindAccordingToHint(Integer kinds, KindHint hintkind) {
		int low = hintkind.getLow();
		int high = hintkind.getHigh();
		return ((((1<<(high+1))-1)&kinds)>>low)<<low;
	}
	
	public static VDataPoolManager ChooseManagerAccordingToKind(String kind, VHiddenClassPoolManager vhcpm, VLabelPoolManager vlpm, VVarObjPoolManager vvopm)
	{
		switch (kind) {
		case VVarObjPoolManager.ConstantPool:
		case VVarObjPoolManager.VarOrObjPool:
			return vvopm;
		case VHiddenClassPoolManager.ClassHiddenPool:
			return vhcpm;
		case VLabelPoolManager.LabelPool:
			return vlpm;
		default:
			System.err.println("No such kind manager, the system will exit.");
			System.exit(1);
			break;
		}
		return null;
	}
	
	public static int GetKindRepresentation(String kind)
	{
		switch (kind) {
		case VVarObjPoolManager.ConstantPool:
			return 2^0;
		case VVarObjPoolManager.VarOrObjPool:
			return 2^1;
		case VHiddenClassPoolManager.ClassHiddenPool:
			return 2^2;
		case VLabelPoolManager.LabelPool:
			return 2^3;
		default:
			System.err.println("No such kind manager, the system will exit.");
			System.exit(1);
			break;
		}
		return -1;
	}
	
}