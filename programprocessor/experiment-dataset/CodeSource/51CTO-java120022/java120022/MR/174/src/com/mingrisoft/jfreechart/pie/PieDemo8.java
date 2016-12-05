/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.pie;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieDemo8 extends ApplicationFrame {

	public PieDemo8(String title) {
		super(title);
	}

	/**
	 * ����һ����ͼ�������ݼ� ���������ӵ����ݼ���
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("JAVA�����ŵ���ͨ����2�棩", 500);
		dataset.setValue("��ƵѧJAVA", 800);
		dataset.setValue("JAVAȫ���ٲ鱦��", 1000);
		dataset.setValue("Java������ȫ��ѧ�ֲ�(1DVD)", 400);
		dataset.setValue("Java��������ģ���ȫ", 750);
		return dataset;
	}

	/**
	 * ��ȡ���ݼ�������JFreeChart��
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createPieChart3D("2010.8�·���������",
				dataset, true, true, false);
		// ���ñ�ͼʹ�õ�����
		setPiePoltFont(chart);
		return chart;
	}

	/**
	 * ���ñ�ͼʹ�õ�����
	 * 
	 * @param chart
	 */
	protected void setPiePoltFont(JFreeChart chart) {

		// �����ǩ�������ʾ
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("����", Font.PLAIN, 14));
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}"));

		// ��������
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));

		// ͼ������
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));

	}

	/**
	 * ����Pie
	 * 
	 * @param chart
	 */
	public void createPiePlot() {
		JFreeChart chart = getJFreeChart();
		PiePlot3D plot  = (PiePlot3D)chart.getPlot();
		//����3D��ͼZ
		plot.setDepthFactor(0.3f);
		//plot.setDarkerSides(true);
		// ��JFreeChart���󱣴浽�����
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		PieDemo8 pieChartDemo1 = new PieDemo8("��ͼʵ��");
		// ����ͼ��
		pieChartDemo1.createPiePlot();
		// ����ͼ��
		pieChartDemo1.pack();
		// �Ѵ�����ʾ����ʾ����
		RefineryUtilities.centerFrameOnScreen(pieChartDemo1);
		// ������ʾͼ��״̬
		pieChartDemo1.setVisible(true);

	}
}