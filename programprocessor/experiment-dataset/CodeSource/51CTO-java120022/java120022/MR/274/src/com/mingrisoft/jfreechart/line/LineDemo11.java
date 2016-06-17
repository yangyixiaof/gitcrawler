/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.line;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * XY����ͼ
 * @author baiweiming
 * 
 */
public class LineDemo11 extends ApplicationFrame {

	public LineDemo11(String title) {
		super(title);
	}

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	private IntervalXYDataset getDataset() {

		final XYSeries series1 = new XYSeries("JAVAͼ��");
		final XYSeries series2 = new XYSeries("VCͼ��");
		final XYSeries series3 = new XYSeries("VBͼ��");

		series1.add(501, 3);
		series1.add(200, 2);
		series1.add(308, 2);
		series1.add(580, 4);
		series1.add(418, 2);
		series1.add(315, 1);

		series2.add(480, 2);
		series2.add(381, 3);
		series2.add(264, 1);
		series2.add(185, 2);
		series2.add(209, 2);
		series2.add(302, 2);

		series3.add(310, 2);
		series3.add(489, 2);
		series3.add(512, 3);
		series3.add(589, 4);
		series3.add(359, 2);
		series3.add(402, 2);

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		IntervalXYDataset dataset = getDataset();
		JFreeChart chart = ChartFactory.createXYLineChart("2010���ϰ���ͼ�������", // ͼ�����
				"���ҳ��", // x���ǩ
				"��Ա����", // y���ǩ
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
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));
		// ͼ��
		XYPlot plot = chart.getXYPlot();
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		// X������
		domainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// X���ǩ����
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 14));

		ValueAxis valueAxis = plot.getRangeAxis();
		// y������
		valueAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// y���ǩ����
		valueAxis.setLabelFont(new Font("����", Font.PLAIN, 14));
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
		LineDemo11 demo = new LineDemo11("����ͼ");
		demo.createPlot();
		demo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(demo);
		// ���ÿ�����ʾ
		demo.setVisible(true);
	}
}