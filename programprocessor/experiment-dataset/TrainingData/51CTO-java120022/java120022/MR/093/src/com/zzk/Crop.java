package com.zzk;

import javax.swing.JLabel;
import com.swtdesigner.SwingResourceManager;

/**
 * @author ������
 *
 */
public class Crop extends JLabel {

	public Crop() {
		super();
	}
	public void setIcon(String picture){
		setIcon(SwingResourceManager.getIcon(Crop.class, picture));//�������Ҫ��ʾ��ͼ�꣬������ʾ�����״̬
	}

}
