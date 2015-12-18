package cn.yyx.research.language.Utility;

public class TTest extends ParentTest{
	
	public AlgAnimFrame frame;
	
	public AlgThread(AlgAnimFrame frame) {
		this.frame = frame;
		this.drawingPanel = frame.getDrawingPanel();
		if (frame != null && frame.getAlg() != null && 
				frame.getAlg().drawingPanel != null) {
		}
		frame.getAlg().a--;
	}
	
	public DrawingPanel drawingPanel;
	
}