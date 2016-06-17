package com.zzk;

import javax.swing.JLabel;
import com.swtdesigner.SwingResourceManager;

/**
 * @author 张振坤
 *
 */
public class Crop extends JLabel {

	public Crop() {
		super();
	}
	public void setIcon(String picture){
		setIcon(SwingResourceManager.getIcon(Crop.class, picture));//设置组件要显示的图标，用于显示作物的状态
	}

}
