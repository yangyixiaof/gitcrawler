/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartDemo11 {

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
	 * ���ñ߿�
	 * 
	 * @param chart
	 */
	public void setOutline(JFreeChart chart) {

		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setBackgroundPaint(Color.white);
		piePlot.setOutlineVisible(false);
		
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
		ChartDemo11 pieChartDemo1 = new ChartDemo11();
		JFreeChart chart = pieChartDemo1.getJFreeChart();
		pieChartDemo1.setPiePoltFont(chart);
		pieChartDemo1.setOutline(chart);
		ChartFrame chartFrame = new ChartFrame("JFreeChart������", chart);
		chartFrame.pack();
		chartFrame.setVisible(true);

	}
}
