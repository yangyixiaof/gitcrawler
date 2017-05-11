/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.pie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.plot.PiePlot;

public class PieDemo9Listener implements ActionListener {

	private PiePlot plot;

	//��ͼ�ĽǶ�
	private int angle = 90;
	
	public PieDemo9Listener(PiePlot plot) {
		this.plot =plot;
	}
	
	/*
	 * ���ñ�ͼ�ĽǶȣ�Ȼ���1�������ͼ�ĽǶ���360�ȣ��ѽǶ�����Ϊ0
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed( ActionEvent event) {
		this.plot.setStartAngle(this.angle);
		this.angle = this.angle + 1;
		if (this.angle == 360) {
			this.angle = 0;
		}
	}

}
