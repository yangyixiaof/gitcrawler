/**
 * @jdk版本:1.6
 * @编码时间:2010-7-20
 */
package com.mingrisoft.jfreechart.pie;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.TableOrder;

public class PieDemo11 extends ApplicationFrame {

	public PieDemo11(final String title) {
		super(title);

	}

	/**
	 * 创建数据集
	 * 
	 * @return
	 */
	private CategoryDataset createDataset() {
		 double[][] data = new double[][] {
				{ 620, 410, 300 },
				{ 300, 390, 500 } };
		 CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"Dept",//行名称 
				"Month",//列名称
				data);
		return dataset;
	}

	/**
	 * 获取数据集，生成JFreeChart，
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createMultiplePieChart(
				"4-6 month sales ranking ", // 饼图标题
				dataset, // 数据集
				TableOrder.BY_ROW, // 排序方式
				true, true, false);
		return chart;
	}

	/**
	 * 创建饼图
	 */
	public void createPiePlot() {
		JFreeChart chart = getJFreeChart();
		setContentPane(new ChartPanel(chart));
	}

	public static void main(final String[] args) {

		final PieDemo11 demo = new PieDemo11("饼图实例");
		demo.createPiePlot();
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
