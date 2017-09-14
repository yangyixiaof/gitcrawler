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
 * ���뻷��ͼ
 * @author baiweiming
 *
 */
public class RingDeom4 extends ApplicationFrame {

	public RingDeom4(String title) {
		super(title);
	}

	/**
	 * ����һ�����ݼ�
	 * 
	 * @return
	 */
	private PieDataset getPieDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("JAVA�����ŵ���ͨ����2�棩", 500);
		dataset.setValue("��ƵѧJAVA", 800);
		dataset.setValue("JAVAȫ���ٲ鱦��", 1000);
		dataset.setValue("Java������ȫ��ѧ�ֲ�(1DVD)", 400);
		dataset.setValue("Java��������ģ���ȫ", 750);
		return dataset;
	}

	/**
	 * ����JFreeChart
	 * 
	 * @return
	 */
	private JFreeChart getJFreeChart() {
		PieDataset dataset = getPieDataset();
		JFreeChart chart = ChartFactory.createRingChart("2010�ϰ������ۼ�¼", // ͼ������
				dataset, // ���ݼ�
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
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

		// ͼ��
		RingPlot ringPlot = (RingPlot) chart.getPlot();
		ringPlot.setLabelFont(new Font("����", Font.PLAIN, 14));
		ringPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}"));

		// ����
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20));
		//ͼʾ
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("����", Font.PLAIN, 14));

	}

	/**
	 * ����ͼ����ʾ
	 * 
	 * @param chart
	 */
	private void updatePlot(JFreeChart chart) {
		// ͼ��
		RingPlot plot = (RingPlot) chart.getPlot();
		plot.setExplodePercent("JAVAȫ���ٲ鱦��", 0.1);
	}

	/**
	 * ����ͼ��
	 * 
	 * @param chart
	 */
	public void createPlot() {
		JFreeChart chart = getJFreeChart();
		// ��������
		updateFont(chart);
		//����ͼ��
		updatePlot(chart);
		// ��JFreeChart��屣���ڴ�����
		setContentPane(new ChartPanel(chart));

	}

	public static void main(String[] args) {
		RingDeom4 demo = new RingDeom4("����ͼ");
		demo.createPlot();
		demo.pack();
		// �Ѵ�����ʾ����ʾ������
		RefineryUtilities.centerFrameOnScreen(demo);
		// ���ÿ�����ʾ
		demo.setVisible(true);
	}

}