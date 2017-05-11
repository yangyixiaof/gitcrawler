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
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class PieDemo12 extends ApplicationFrame {

	public PieDemo12(final String title) {
		super(title);

	}

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	private CategoryDataset createDataset() {
		 double[][] data = new double[][] {
				{ 620, 410, 300 },
				{ 300, 390, 500 } };
		 CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"����",//������ 
				"�·�",//������
				data);
		return dataset;
	}

	/**
	 * ��ȡ���ݼ�������JFreeChart��
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createMultiplePieChart(
				"4-6����������", // ��ͼ����
				dataset, // ���ݼ�
				TableOrder.BY_ROW, // ����ʽ
				true, true, false);
		return chart;
	}

	/**
	 * ������ͼ
	 */
	public void createPiePlot() {
		JFreeChart chart = getJFreeChart();
		// �������
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));

		// ͼ��
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));
		
		
		MultiplePiePlot multiplePiePlot = (MultiplePiePlot) chart.getPlot();
		JFreeChart jFreeChart = multiplePiePlot.getPieChart();

		// ͼ���ǩ
		PiePlot piePlot = (PiePlot) jFreeChart.getPlot();
		piePlot.setLabelFont(new Font("����", Font.PLAIN, 14));
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}"));

		// ͼ�����
		TextTitle textTitle2 = jFreeChart.getTitle();
		textTitle2.setFont(new Font("����", Font.BOLD, 20));
		
		
		setContentPane(new ChartPanel(chart));
	}

	public static void main(final String[] args) {

		final PieDemo12 demo = new PieDemo12("��ͼʵ��");
		demo.createPiePlot();
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
