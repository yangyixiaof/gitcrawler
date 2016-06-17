/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.combinedCategory;

import java.awt.Font;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * ��������ͼ������ͼ
 * @author baiweiming
 * 
 */
public class CombinedCategoryDemo1 extends ApplicationFrame {

	public CombinedCategoryDemo1(String title) {
		super(title);
	}

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {

		// �йؼ���
		final String series1 = "JAVAͼ��";
		final String series2 = "VCͼ��";
		final String series3 = "VBͼ��";
		// �йؼ���
		final String category1 = "1��";
		final String category2 = "2��";
		final String category3 = "3��";
		final String category4 = "4��";
		final String category5 = "5��";
		final String category6 = "6��";

		// �����������ݼ�
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(310, series1, category1);
		dataset.addValue(489, series1, category2);
		dataset.addValue(512, series1, category3);
		dataset.addValue(589, series1, category4);
		dataset.addValue(359, series1, category5);
		dataset.addValue(402, series1, category6);

		dataset.addValue(501, series2, category1);
		dataset.addValue(200, series2, category2);
		dataset.addValue(308, series2, category3);
		dataset.addValue(580, series2, category4);
		dataset.addValue(418, series2, category5);
		dataset.addValue(315, series2, category6);

		dataset.addValue(480, series3, category1);
		dataset.addValue(381, series3, category2);
		dataset.addValue(264, series3, category3);
		dataset.addValue(185, series3, category4);
		dataset.addValue(209, series3, category5);
		dataset.addValue(302, series3, category6);

		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = getCategoryDataset();
		//��������ͼ��Ⱦ
		LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
		//��������ͼ��Ⱦ
		BarRenderer renderer2 = new BarRenderer();
		//����X��
		CategoryAxis domainAxis = new CategoryAxis("�·�");
		//����Y��
		NumberAxis rangeAxis = new NumberAxis("����������λ������");
		//����ͼ��
		CategoryPlot plot1 = new CategoryPlot(dataset, domainAxis, rangeAxis,
				renderer1);
		CategoryPlot plot2 = new CategoryPlot(dataset, domainAxis, rangeAxis,
				renderer2);
		//�������Ϸ���ͼ��
		final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(
				domainAxis);
		plot.add(plot1);
		plot.add(plot2);
		JFreeChart chart = new JFreeChart("2010���ϰ���������", plot);
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
		setContentPane(new ChartPanel(chart));
	}

	public static void main(String[] args) {
		CombinedCategoryDemo1 demo = new CombinedCategoryDemo1("���Ϸ���ͼ");
		demo.createPlot();
		demo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(demo);
		// ���ÿ�����ʾ
		demo.setVisible(true);
	}
}