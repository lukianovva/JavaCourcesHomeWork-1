package ru.liga.currencyForecast.forecast.presentation.views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRate;
import ru.liga.currencyForecast.forecast.domain.entities.ExchangeRatesList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ChartRatesView {
    private final ExchangeRatesList exchangeRatesList;
    private final JFreeChart chart;

    public ChartRatesView(ExchangeRatesList exchangeRatesList) {
        this.exchangeRatesList = exchangeRatesList;

        XYDataset dataset = createDataset();
        this.chart = createChart(dataset);
    }

    public void save(String filename) throws IOException {
        ChartUtils.saveChartAsPNG(new File(filename), chart, 450, 400);
    }

    private XYDataset createDataset() {
        var series1 = new XYSeries("2014");
        var series2 = new XYSeries("2016");

        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.y");

        int dayNumber = 0;
        for (ExchangeRate rate : exchangeRatesList.rates()) {
            dayNumber++;
            series1.add(dayNumber, (double) rate.nominalRate());
            series2.add(dayNumber, (double) rate.nominalRate()+1);
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Average salary per age",
                "Age",
                "Salary (€)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Average Salary per Age",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
}
