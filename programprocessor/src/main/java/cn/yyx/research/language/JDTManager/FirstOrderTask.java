package cn.yyx.research.language.JDTManager;

import org.eclipse.jdt.core.dom.ASTNode;

public abstract class FirstOrderTask implements Runnable {
	
	ASTNode pre = null;
	ASTNode post = null;
	ASTNode infixnode = null;
	boolean afterprerun = false;
	boolean beforepostrun = false;
	private Object additionalinfo = null;
	
	public FirstOrderTask(ASTNode pre, ASTNode post, ASTNode infixnode, boolean afterprerun, boolean beforepostrun) {
		this.setPre(pre);
		this.setPost(post);
		this.setInfixnode(infixnode);
		this.setAfterprerun(afterprerun);
		this.setBeforepostrun(beforepostrun);
	}
	
	public FirstOrderTask(ASTNode pre, ASTNode post, ASTNode infixnode, boolean afterprerun, boolean beforepostrun, Object additioninfo) {
		this.setPre(pre);
		this.setPost(post);
		this.setInfixnode(infixnode);
		this.setAfterprerun(afterprerun);
		this.setBeforepostrun(beforepostrun);
		this.setAdditionalinfo(additioninfo);
	}
	
	public ASTNode getInfixnode() {
		return infixnode;
	}
	
	public void setInfixnode(ASTNode infixnode) {
		this.infixnode = infixnode;
	}
	
	public ASTNode getPost() {
		return post;
	}
	
	public void setPost(ASTNode post) {
		this.post = post;
	}
	
	public ASTNode getPre() {
		return pre;
	}
	
	public void setPre(ASTNode pre) {
		this.pre = pre;
	}
	
	public boolean isAfterprerun() {
		return afterprerun;
	}
	
	public void setAfterprerun(boolean afterprerun) {
		this.afterprerun = afterprerun;
	}

	public boolean isBeforepostrun() {
		return beforepostrun;
	}

	public void setBeforepostrun(boolean afterpostrun) {
		this.beforepostrun = afterpostrun;
	}

	public Object getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(Object additionalinfo) {
		this.additionalinfo = additionalinfo;
	}
	
}