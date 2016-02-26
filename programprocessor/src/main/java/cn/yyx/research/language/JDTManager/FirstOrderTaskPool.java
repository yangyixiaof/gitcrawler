package cn.yyx.research.language.JDTManager;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jdt.core.dom.ASTNode;

import cn.yyx.research.language.simplified.JDTManager.ConflictASTNodeHashCodeError;

public class FirstOrderTaskPool {

	Map<String, FirstOrderTask> pretasks = new TreeMap<String, FirstOrderTask>();
	Map<String, FirstOrderTask> posttasks = new TreeMap<String, FirstOrderTask>();

	public FirstOrderTaskPool() {
	}

	public void InfixNodeAddFirstOrderTask(FirstOrderTask task) {
		FirstOrderTask already = null;
		if (task.getPre() != null) {
			already = pretasks.put(GetASTId(task.getPre(), true), task);
			JudgeTaskError(already);
		}
		if (!task.isAfterprerun()) {
			already = posttasks.put(GetASTId(task.getPost(), false), task);
			JudgeTaskError(already);
		}
	}

	private void JudgeTaskError(FirstOrderTask already) {
		if (already != null) {
			System.err.println("Debugging Conflict, task pre cnt:" + already.getPre() + ";task pre hash:"
					+ (already.getPre() != null ? already.getPre().hashCode() : "no hash") + ";task post cnt:"
					+ already.getPost() + ";task post hash:"
					+ (already.getPost() != null ? already.getPost().hashCode() : "no hash"));
			throw new ConflictASTNodeHashCodeError("Not unique FirstOrderTask. The program will exit.");
			// System.exit(1);
		}
	}

	public void PreIsOver(ASTNode pre) {
		FirstOrderTask fot = pretasks.remove(GetASTId(pre, true));
		if (fot != null && fot.isAfterprerun()) {
			fot.run();
			if (!fot.isAfterprerun()) {
				posttasks.remove(GetASTId(fot.getPost(), false));
			}
		}
	}

	public void PostIsBegin(ASTNode post) {
		FirstOrderTask fot = posttasks.get(GetASTId(post, false));
		if (fot != null) {
			if (!fot.isBeforepostrun())
			{
				CheckPreTask(fot);
			}
			fot.run();
			posttasks.remove(GetASTId(post, false));
		}
	}

	private void CheckPreTask(FirstOrderTask fot) {
		// check begin.
		// check pre corresponding task is null. PreCondition: pre corresponding
		// task must be null.
		ASTNode pre = fot.getPre();
		FirstOrderTask pretask = pretasks.get(GetASTId(pre, true));
		if (pretask != null) {
			new Exception("Order is not what you think.").printStackTrace();
			System.err.println("error post node:" + fot.getPost());
			System.err.println("error pre node:" + pre);
			System.exit(1);
		}
		// check end.
	}

	private String GetASTId(ASTNode node, boolean ispre) {
		String prefix = ispre ? "pre#" : "post#";
		return prefix + node.hashCode();
	}

}