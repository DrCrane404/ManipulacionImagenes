package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import java.awt.Color;

public class HistogramPanel {

    public static void mostrar(double[] rojo, double[] verde, double[] azul) {

        XYSeries serieRojo = new XYSeries("Rojo");
        XYSeries serieVerde = new XYSeries("Verde");
        XYSeries serieAzul = new XYSeries("Azul");

        for (int i = 0; i < 256; i++) {
            serieRojo.add(i, rojo[i]);
            serieVerde.add(i, verde[i]);
            serieAzul.add(i, azul[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serieRojo);
        dataset.addSeries(serieVerde);
        dataset.addSeries(serieAzul);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Histograma RGB",
                "Nivel de intensidad",
                "Frecuencia",
                dataset,
                PlotOrientation.VERTICAL,
                true,   // leyenda
                true,   // tooltips
                false   // urls
        );

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(768, 400));

        JFrame ventana = new JFrame("Histograma RGB");
        ventana.add(panel);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
    }
}