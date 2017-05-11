package com.mingrisoft.jfreechart.ring;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * 基本环形图
 * @author baiweiming
 *
 */
public class RingDeom1 extends ApplicationFrame {

	public RingDeom1(String title) {
		super(title);
	}

	/**
	 * 创建一个数据集
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("java book", 1689);
		dataset.setValue("vc book", 810);
		dataset.setValue("vb book", 490);
		return dataset;
	}

	/**
	 * 生成JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createRingChart(
				"2010.1-6 sales volume", // 图表标题
				dataset, // 数据集
				true, // 是否显示图例
				false, // 是否生成工具
				false // 是否生成URL链接
				);

		return chart;
	}

	/**
	 * 设置图表
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		// 把JFreeChart面板保存在窗体里
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		RingDeom1 demo = new RingDeom1("环形图");
		demo.createPlot();
		demo.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(demo);
		// 设置可以显示
		demo.setVisible(true);

	}

}
