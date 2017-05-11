/**
 * @jdk版本:1.6
 * @编码时间:2010-7-20
 */
package com.mingrisoft.jfreechart.stackedBar;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * 渲染分组堆积条形图
 * @author baiweiming
 *
 */
public class StackedBarDemo4 extends ApplicationFrame {

	public StackedBarDemo4(String title) {
		super(title);
	}

	/**
	 * 创建数据集
	 * 
	 * @return
	 */
	private CategoryDataset getCategoryDataset() {

		// 行关键字
		final String series1 = "JAVA图书(含光盘)";
		final String series2 = "VC图书(含光盘)";
		final String series3 = "VB图书(含光盘)";
		final String series11 = "JAVA图书(无光盘)";
		final String series21 = "VC图书(无光盘)";
		final String series31 = "VB图书(无光盘)";
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
		dataset.addValue(300, series11, category1);
		dataset.addValue(469, series11, category2);
		dataset.addValue(502, series11, category3);
		dataset.addValue(569, series11, category4);
		dataset.addValue(369, series11, category5);
		dataset.addValue(412, series11, category6);
		

		dataset.addValue(501, series2, category1);
		dataset.addValue(200, series2, category2);
		dataset.addValue(308, series2, category3);
		dataset.addValue(580, series2, category4);
		dataset.addValue(418, series2, category5);
		dataset.addValue(315, series2, category6);
		dataset.addValue(511, series21, category1);
		dataset.addValue(210, series21, category2);
		dataset.addValue(318, series21, category3);
		dataset.addValue(560, series21, category4);
		dataset.addValue(408, series21, category5);
		dataset.addValue(305, series21, category6);

		dataset.addValue(480, series3, category1);
		dataset.addValue(381, series3, category2);
		dataset.addValue(264, series3, category3);
		dataset.addValue(185, series3, category4);
		dataset.addValue(209, series3, category5);
		dataset.addValue(302, series3, category6);
		dataset.addValue(470, series31, category1);
		dataset.addValue(371, series31, category2);
		dataset.addValue(254, series31, category3);
		dataset.addValue(165, series31, category4);
		dataset.addValue(219, series31, category5);
		dataset.addValue(312, series31, category6);
		return dataset;
	}

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		CategoryDataset dataset = getCategoryDataset();
		JFreeChart chart = ChartFactory.createStackedBarChart("2010年上半年销售量", // 图表标题
				"月份", // x轴标签
				"销售量（单位：本）", // y轴标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例
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
		// 分类图表
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		//添加X轴子标签
        SubCategoryAxis domainAxis = new SubCategoryAxis("月份");
        domainAxis.addSubCategory("含光盘");
        domainAxis.addSubCategory("无光盘");
        categoryPlot.setDomainAxis(domainAxis);
        
        //分组堆积条形图渲染
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        //分为两组
        map.mapKeyToGroup("JAVA图书(含光盘)", "G1");
        map.mapKeyToGroup("VC图书(含光盘)", "G1");
        map.mapKeyToGroup("VB图书(含光盘)", "G1");

        map.mapKeyToGroup("JAVA图书(无光盘)", "G2");
        map.mapKeyToGroup("VC图书(无光盘)", "G2");
        map.mapKeyToGroup("VB图书(无光盘)", "G2");
        renderer.setSeriesToGroupMap(map); 
        categoryPlot.setRenderer(renderer);

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
		setContentPane(new ChartPanel(chart));
	}

	public static void main(String[] args) {
		StackedBarDemo4 demo = new StackedBarDemo4("堆积条形图");
		demo.createPlot();
		demo.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(demo);
		// 设置可以显示
		demo.setVisible(true);
	}
}
