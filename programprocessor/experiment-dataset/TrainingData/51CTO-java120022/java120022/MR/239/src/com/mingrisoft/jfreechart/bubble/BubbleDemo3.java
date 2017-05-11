/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.bubble;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *	����ͼY���ǩ 
 * @author baiweiming
 */
public class BubbleDemo3 extends ApplicationFrame {

	public BubbleDemo3(String title) {
		super(title);
	}

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	private XYZDataset getXYZDataset() {

		// ϵ���ؼ���
		final String series1 = "JAVAͼ��";
		final String series2 = "VCͼ��";
		final String series3 = "VBͼ��";
		
		// �����������ݼ�
		DefaultXYZDataset dataset = new DefaultXYZDataset();
		double data1 [] [] =    new double[][] {{600}, {10}, {600/(10*10)}};
		double data2 [] [] =    new double[][] {{650}, {10}, {650/(10*10)}};
		double data3 [] [] =    new double[][] {{800}, {16}, {800/(16*10)}};

		dataset.addSeries(series1, data1);
		dataset.addSeries(series2, data2);
		dataset.addSeries(series3, data3);

		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		XYZDataset dataset = getXYZDataset();
		JFreeChart chart = ChartFactory.createBubbleChart("2010���ϰ���������", // ͼ�����
				"ҳ��", // x���ǩ
				"����", // y���ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��
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
		//ͼʾ
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));
		
		XYPlot plot = chart.getXYPlot();
		//X������
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 14));
		//Y������
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("����", Font.PLAIN, 14));
		
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
		BubbleDemo3 demo = new BubbleDemo3("����ͼ");
		demo.createPlot();
		demo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(demo);
		// ���ÿ�����ʾ
		demo.setVisible(true);
	}
}
