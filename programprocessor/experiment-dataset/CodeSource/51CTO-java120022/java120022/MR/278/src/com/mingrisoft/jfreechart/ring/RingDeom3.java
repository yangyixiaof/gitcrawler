package com.mingrisoft.jfreechart.ring;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * 设置环形深度
 * @author baiweiming
 *
 */
public class RingDeom3 extends ApplicationFrame {

	public RingDeom3(String title) {
		super(title);
	}

	/**
	 * 创建一个数据集
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("JAVA从入门到精通（第2版）", 500);
		dataset.setValue("视频学JAVA", 800);
		dataset.setValue("JAVA全能速查宝典", 1000);
		dataset.setValue("Java范例完全自学手册(1DVD)", 400);
		dataset.setValue("Java开发典型模块大全", 750);
		return dataset;
	}

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createRingChart("2010上半年销售记录", // 图表标题
				dataset, // 数据集
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

		// 图表
		RingPlot ringPlot = (RingPlot) chart.getPlot();
		ringPlot.setLabelFont(new Font("宋体", Font.PLAIN, 14));
		ringPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}"));

		// 标题
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("宋体", Font.PLAIN, 14));

	}

	/**
	 * 设置图表
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		// 设置字体
		RingPlot ringPlot = (RingPlot) chart.getPlot();
		//设置环形深度
		ringPlot.setSectionDepth(0.7);
		updateFont(chart);
		// 把JFreeChart面板保存在窗体里
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		RingDeom3 demo = new RingDeom3("环形图");
		demo.createPlot();
		demo.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(demo);
		// 设置可以显示
		demo.setVisible(true);

	}

}
