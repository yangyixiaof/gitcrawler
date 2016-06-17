/**
 * @jdk�汾:1.6
 * @����ʱ��:2010-7-20
 */
package com.mingrisoft.jfreechart.timeSeries;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.SerialDate;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * ����ʱ���᷶Χ
 * 
 * @author baiweiming
 * 
 */
public class TimeSeriesDemo9 extends ApplicationFrame {

	public TimeSeriesDemo9(String title) {
		super(title);
	}

	/**
	 * �������ݼ�
	 * 
	 * @return
	 */
	private XYDataset getDataset() {

		final TimeSeries s1 = new TimeSeries("JAVAͼ��");
		s1.add(new Month(1, 2010), 480);
		s1.add(new Month(2, 2010), 381);
		s1.add(new Month(3, 2010), 264);
		s1.add(new Month(4, 2010), 185);
		s1.add(new Month(5, 2010), 209);
		s1.add(new Month(6, 2010), 302);

		final TimeSeries s2 = new TimeSeries("VCͼ��");
		s2.add(new Month(1, 2010), 315);
		s2.add(new Month(2, 2010), 418);
		s2.add(new Month(3, 2010), 580);
		s2.add(new Month(4, 2010), 308);
		s2.add(new Month(5, 2010), 200);
		s2.add(new Month(6, 2010), 501);

		final TimeSeries s3 = new TimeSeries("VBͼ��");
		s3.add(new Month(1, 2010), 310);
		s3.add(new Month(2, 2010), 489);
		s3.add(new Month(3, 2010), 512);
		s3.add(new Month(4, 2010), 589);
		s3.add(new Month(5, 2010), 359);
		s3.add(new Month(6, 2010), 402);

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		dataset.addSeries(s3);

		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		XYDataset dataset = getDataset();
		JFreeChart chart = ChartFactory.createTimeSeriesChart("2010���ϰ���������", // ͼ�����
				"�·�", // x���ǩ
				"����������λ������", // y���ǩ
				dataset, // ���ݼ�
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
		XYPlot xyPlot = chart.getXYPlot();

		ValueAxis domainyAxis = xyPlot.getDomainAxis();
		// X������
		domainyAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// X���ǩ����
		domainyAxis.setLabelFont(new Font("����", Font.PLAIN, 14));

		ValueAxis rangeAxis = xyPlot.getRangeAxis();
		// y������
		rangeAxis.setTickLabelFont(new Font("����", Font.PLAIN, 14));
		// y���ǩ����
		rangeAxis.setLabelFont(new Font("����", Font.PLAIN, 14));
	}

	/**
	 * ����ͼ����ʾ
	 * 
	 * @param chart
	 */
	private void updatePlot(JFreeChart chart) {
		Day endDay = new Day(1,6,2010);
        SerialDate serialDate = SerialDate.addMonths(-5, endDay.getSerialDate());
		Day beginDay = new Day(serialDate.toDate());
		DateAxis axis = (DateAxis) chart.getXYPlot().getDomainAxis();
		//����X�Ὺʼ����λ��
		axis.setRange(beginDay.getStart(), endDay.getEnd());
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
		//����ͼ��
		updatePlot(chart);
		setContentPane(new ChartPanel(chart));
	}

	public static void main(String[] args) {
		TimeSeriesDemo9 demo = new TimeSeriesDemo9("ʱ��ͼ");
		demo.createPlot();
		demo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(demo);
		// ���ÿ�����ʾ
		demo.setVisible(true);
	}
}
