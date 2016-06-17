/**
 * @jdk版本:1.6
 * @编码时间:2010-7-20
 */
package com.mingrisoft.jfreechart;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartDemo5 {

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	public JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		// locale.getISO3Language()
		JFreeChart chart = ChartFactory.createPieChart("2010.8月份销售排行", dataset,
				true, true, false);

		return chart;
	}

	/**
	 * 设置饼图使用的字体
	 * 
	 * @param chart
	 */
	public void setPiePoltFont(JFreeChart chart) {

		// 图表(饼图)
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("宋体", Font.PLAIN, 14));

		// 标题
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));

		// 图例
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("宋体", Font.PLAIN, 12));

	}

	/**
	 * 设置饼图标签
	 * 
	 * @param chart
	 */
	public void setPiePoltNum(JFreeChart chart) {

		// 图表(饼图)
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
	}

	/**
	 * 创建一个饼图表的数据集
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("JAVA从入门到精通（第2版）", 500);
		dataset.setValue("视频学JAVA", 800);
		dataset.setValue("JAVA全能速查宝典", 1000);
		return dataset;
	}

	public static void main(String[] args) {
		ChartDemo5 demo = new ChartDemo5();
		JFreeChart chart = demo.getJFreeChart();
		demo.setPiePoltFont(chart);
		demo.setPiePoltNum(chart);
		ChartFrame chartFrame = new ChartFrame("JFreeChart的例子", chart);
		chartFrame.pack();
		chartFrame.setVisible(true);

	}
}
