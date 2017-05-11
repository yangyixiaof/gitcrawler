/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.Align;

import com.sun.imageio.plugins.common.ImageUtil;

public class ChartDemo9 {

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	public JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		// locale.getISO3Language()
		JFreeChart chart = ChartFactory.createPieChart("2010.8�·���������", dataset,
				true, true, false);

		return chart;
	}

	/**
	 * ���ñ�ͼʹ�õ�����
	 * 
	 * @param chart
	 */
	public void setPiePoltFont(JFreeChart chart) {

		// ͼ��(��ͼ)
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("����", Font.PLAIN, 14));
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));

		// ����
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));

		// ͼ��
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 12));

	}

	/**
	 * ���ñ�ͼ����ͼ
	 * 
	 * @param chart
	 */
	public void setBackgroundImage(JFreeChart chart) {

		Image image = null;
		try {
			image = ImageIO.read(new File("backgroundImage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setBackgroundImage(image);
		//���ñ���͸����
		piePlot.setBackgroundImageAlpha(1f);
	}

	/**
	 * ����һ����ͼ������ݼ�
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("JAVA�����ŵ���ͨ����2�棩", 500);
		dataset.setValue("��ƵѧJAVA", 800);
		dataset.setValue("JAVAȫ���ٲ鱦��", 1000);
		return dataset;
	}

	public static void main(String[] args) {
		ChartDemo9 demo = new ChartDemo9();
		JFreeChart chart = demo.getJFreeChart();
		demo.setPiePoltFont(chart);
		demo.setBackgroundImage(chart);
		ChartFrame chartFrame = new ChartFrame("JFreeChart������", chart);
		chartFrame.pack();
		chartFrame.setVisible(true);

	}
}
