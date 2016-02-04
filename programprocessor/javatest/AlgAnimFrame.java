import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class AlgAnimFrame extends Frame {
	private AlgThread alg = null;
	private CheckboxMenuItem[] dataChoice = null;
	private Menu dataMenu = null;
	
	public AlgAnimFrame(AlgAnimApp parentApp, URL sourceURL) {
		for (int i = 0; i < alg.dataSets.length; i++) {
			dataChoice[i] = new CheckboxMenuItem(alg.dataSets[i]);
			dataMenu.add(dataChoice[i]);
		}
	}
}