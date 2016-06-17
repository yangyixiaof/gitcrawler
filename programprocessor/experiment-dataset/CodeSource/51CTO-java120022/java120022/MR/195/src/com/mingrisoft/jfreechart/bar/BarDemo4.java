/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.bar;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarDemo4 extends ApplicationFrame {

	public BarDemo4(String title) {
		super(title);
	}

	/**
	 * ����һ�����ݼ�
	 * 
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {

		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		keyedValues.addValue("1��", 310);
		keyedValues.addValue("2��", 489);
		keyedValues.addValue("3��", 512);
		keyedValues.addValue("4��", 589);
		keyedValues.addValue("5��", 359);
		keyedValues.addValue("6��", 402);
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"java book", keyedValues);
		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = getCategoryDataset();
		JFreeChart chart = ChartFactory.createBarChart("2010���ϰ���������", // ͼ�����
				"month", // x���ǩ
				"sales", // y���ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				false, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);

		return chart;
	}
	
	/**
	 * �޸�����
	 * @param chart
	 */
	public void updateFont(JFreeChart chart){

		// ����
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20));
		
		// ͼ��
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		
		CategoryAxis axis = categoryPlot.getDomainAxis();
		//X������
		axis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		
		ValueAxis valueAxis = categoryPlot.getRangeAxis();
		// y������
		valueAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));

	}

	/**
	 * ����ͼ��
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		//�޸�����
		updateFont(chart);
		// ��JFreeChart��屣���ڴ�����
		setContentPane(new ChartPanel(chart));

	}
	
	

	public static void main(String[] args) {
		BarDemo4 barDemo = new BarDemo4("����ͼʵ��");
		barDemo.createPlot();
		barDemo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(barDemo);
		// ���ÿ�����ʾ
		barDemo.setVisible(true);

	}
}
