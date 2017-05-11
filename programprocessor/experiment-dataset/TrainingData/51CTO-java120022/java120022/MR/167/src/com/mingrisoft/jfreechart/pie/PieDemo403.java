/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.pie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

public class PieDemo403 extends ApplicationFrame {

	public PieDemo403(String title) {
		super(title);
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
		dataset.setValue("Java������ȫ��ѧ�ֲ�(1DVD)", 400);
		dataset.setValue("Java��������ģ���ȫ", 750);
		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createPieChart("2010.8�·���������", dataset,
				true, true, false);
		setPiePoltFont(chart);
		return chart;
	}

	/**
	 * ���ñ�ͼʹ�õ�����
	 * 
	 * @param chart
	 */
	protected void setPiePoltFont(JFreeChart chart) {

		// ͼ��(��ͼ)
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("����", Font.PLAIN, 14));
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}"));

		// ����
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));

		// ͼ��
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
		PiePlot piePlot = (PiePlot) chart.getPlot();
		
		//���ñ�ͼ�߿�ʴ�
		piePlot.setSectionOutlineStroke("JAVA�����ŵ���ͨ����2�棩",new BasicStroke(3f));
		piePlot.setSectionOutlineStroke("��ƵѧJAVA", new BasicStroke(3f));
		piePlot.setSectionOutlineStroke("JAVAȫ���ٲ鱦��", new BasicStroke(3f));
		piePlot.setSectionOutlineStroke("Java������ȫ��ѧ�ֲ�(1DVD)", new BasicStroke(3f));
		piePlot.setSectionOutlineStroke("Java��������ģ���ȫ", new BasicStroke(3f));

		// ��JFreeChart��屣���ڴ�����
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		PieDemo403 pieChartDemo1 = new PieDemo403("��ͼʵ��");
		pieChartDemo1.createPiePlot();
		pieChartDemo1.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(pieChartDemo1);
		pieChartDemo1.setVisible(true);

	}
}
