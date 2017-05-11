/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.bar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryLineAnnotation;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarDemo37 extends ApplicationFrame {

	public BarDemo37(String title) {
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
				"JAVAͼ��", keyedValues);
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
				"�·�", // x���ǩ
				"����������λ������", // y���ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);
		return chart;
	}

	/**
	 * �޸�����
	 * 
	 * @param chart
	 */
	private void updateFont(JFreeChart chart) {

		// ����
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20));
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));
		// ͼ��
		CategoryPlot categoryPlot = chart.getCategoryPlot();

		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		// X������
		categoryAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// X���ǩ����
		categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 14));

		ValueAxis valueAxis = categoryPlot.getRangeAxis();
		// y������
		valueAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// y���ǩ����
		valueAxis.setLabelFont(new Font("����", Font.PLAIN, 14));

	}

	/**
	 * ����ͼ����ʾ
	 * 
	 * @param chart
	 */
	private void updatePlot(JFreeChart chart) {
		// ͼ��
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		categoryPlot.getDomainAxisEdge();
		//��������ע��
		CategoryLineAnnotation annotation = new CategoryLineAnnotation("1��",200,"2��",300,Color.blue,new BasicStroke());
		CategoryLineAnnotation annotation1 = new CategoryLineAnnotation("2��",300,"3��",100,Color.blue,new BasicStroke());
		CategoryLineAnnotation annotation2 = new CategoryLineAnnotation("3��",100,"4��",400,Color.blue,new BasicStroke());
		CategoryLineAnnotation annotation3 = new CategoryLineAnnotation("4��",400,"5��",300,Color.blue,new BasicStroke());
		CategoryLineAnnotation annotation4 = new CategoryLineAnnotation("5��",300,"6��",350,Color.blue,new BasicStroke());
		categoryPlot.addAnnotation(annotation);
		categoryPlot.addAnnotation(annotation1);
		categoryPlot.addAnnotation(annotation2);
		categoryPlot.addAnnotation(annotation3);
		categoryPlot.addAnnotation(annotation4);
	}

	/**
	 * ����ͼ��
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		// �޸�����
		updateFont(chart);
		// �޸�ͼ��
		updatePlot(chart);
		// ��JFreeChart��屣���ڴ�����
		setContentPane(new ChartPanel(chart));
	}

	public static void main(String[] args) {
		BarDemo37 barDemo = new BarDemo37("����ͼʵ��");
		barDemo.createPlot();
		barDemo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(barDemo);
		// ���ÿ�����ʾ
		barDemo.setVisible(true);

	}
}
