/**
 * @jdk版本:1.6
 * @编码时间:2010-7-20
 */
package com.mingrisoft.jfreechart.dualAxis;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * 双轴图X轴标签角度
 * @author baiweiming
 *
 */
public class DualAxisDemo6 extends ApplicationFrame {

	public DualAxisDemo6(String title) {
		super(title);
	}

	/**
	 * 创建一个数据集
	 * 
	 * @return
	 */
	private CategoryDataset getCategoryDataset1() {

		// 行关键字
		final String series1 = "JAVA图书";
		final String series2 = "VC图书";
		// 列关键字
		final String category1 = "1月";
		final String category2 = "2月";
		final String category3 = "3月";
		final String category4 = "4月";
		final String category5 = "5月";
		final String category6 = "6月";

		// 创建分类数据集
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

		return dataset;
	}
	
	private CategoryDataset getCategoryDataset2() {
		final String series3 = "VB图书";
		// 列关键字
		final String category1 = "1月";
		final String category2 = "2月";
		final String category3 = "3月";
		final String category4 = "4月";
		final String category5 = "5月";
		final String category6 = "6月";
		// 创建分类数据集
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(480, series3, category1);
		dataset.addValue(381, series3, category2);
		dataset.addValue(264, series3, category3);
		dataset.addValue(185, series3, category4);
		dataset.addValue(209, series3, category5);
		dataset.addValue(302, series3, category6);

		return dataset;
	}

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = getCategoryDataset1();
		JFreeChart chart = ChartFactory.createBarChart("2010年上半年销售量", // 图表标题
				"月份", // x轴标签
				"销售量（单位：本）", // y轴标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
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
	private void updateFont(JFreeChart chart) {


		// 标题
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("宋体", Font.PLAIN, 14));
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
	 * 
	 * @param chart
	 */
	private void updatePlot(JFreeChart chart) {
		// 图表
		CategoryPlot categoryPlot = chart.getCategoryPlot();

		categoryPlot.setDataset(1,getCategoryDataset2());
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();

        //设置线形图
        categoryPlot.setRenderer(1, renderer2);
        final ValueAxis axis2 = new NumberAxis("网购销售量");
		// y轴字体
        axis2.setTickLabelFont(new Font("宋体", Font.PLAIN, 14));
		// y轴标签字体
        axis2.setLabelFont(new Font("宋体", Font.PLAIN, 14));
        //设置最大值
        axis2.setUpperBound(200);
        categoryPlot.setRangeAxis(1, axis2);
        
		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		//双轴图X轴标签角度
		categoryAxis.setLabelAngle(Math.PI*0.3);


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
		// 修改图表
		updatePlot(chart);
		// 把JFreeChart面板保存在窗体里
		setContentPane(new ChartPanel(chart));
	}

	public static void main(String[] args) {
		DualAxisDemo6 demo = new DualAxisDemo6("双轴图");
		demo.createPlot();
		demo.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(demo);
		// 设置可以显示
		demo.setVisible(true);
	}
}
