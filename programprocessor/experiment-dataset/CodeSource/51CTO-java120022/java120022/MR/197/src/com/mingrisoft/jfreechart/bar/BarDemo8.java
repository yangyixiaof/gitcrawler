/**
 * @jdk版本:1.6
 * @编码时间:2010-7-20
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

public class BarDemo8 extends ApplicationFrame {

	public BarDemo8(String title) {
		super(title);
	}

	/**
	 * 创建一个数据集
	 * 
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {

		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		keyedValues.addValue("1月", 310);
		keyedValues.addValue("2月", 489);
		keyedValues.addValue("3月", 512);
		keyedValues.addValue("4月", 589);
		keyedValues.addValue("5月", 359);
		keyedValues.addValue("6月", 402);
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"java book", keyedValues);
		return dataset;
	}

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = getCategoryDataset();
		JFreeChart chart = ChartFactory.createBarChart("2010年上半年销售量", // 图表标题
				"月份", // x轴标签
				"销售量（单位：本）", // y轴标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				false, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);

		return chart;
	}

	/**
	 * 修改字体
	 * 
	 * @param chart
	 */
	public void updateFont(JFreeChart chart) {

		// 标题
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));

		// 图表
		CategoryPlot categoryPlot = chart.getCategoryPlot();

		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		// X轴字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14));
		// X轴标签字体
		categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 14));

		ValueAxis valueAxis = categoryPlot.getRangeAxis();
		// y轴字体
		valueAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14));
		// y轴标签字体
		valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 14));

	}
	
	/**
	 * 更新图表显示
	 * @param chart
	 */
	private void updatePlot(JFreeChart chart){
		// 图表
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		//y轴（数值轴）
		ValueAxis valueAxis = categoryPlot.getRangeAxis();
		//y轴是否显示
		valueAxis.setVisible(false);
	}

	/**
	 * 设置图表
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		// 修改字体
		updateFont(chart);
		//修改图表
		updatePlot(chart);
		// 把JFreeChart面板保存在窗体里
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		BarDemo8 barDemo = new BarDemo8("柱形图实例");
		barDemo.createPlot();
		barDemo.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(barDemo);
		// 设置可以显示
		barDemo.setVisible(true);

	}
}
