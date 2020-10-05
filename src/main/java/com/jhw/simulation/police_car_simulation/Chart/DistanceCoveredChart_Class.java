package com.jhw.simulation.police_car_simulation.chart;

import com.jhw.simulation.police_car_simulation.main.PoliceCarsSimulation_UI;
import java.util.LinkedList;
import javax.swing.JFrame;
import org.jfree.data.xy.XYSeries;
import sim.util.media.chart.TimeSeriesChartGenerator;

/**
 * Class that describe the distance covered chart.
 *
 * @author Margaret Sánchez Martínez
 */
public class DistanceCoveredChart_Class {

    /**
     * The chart.
     */
    private TimeSeriesChartGenerator distanceCoveredChart;

    /**
     * Frame where the chart live.
     */
    private JFrame chartFrame;

    /**
     * Average distance covered by each patrol.
     */
    private XYSeries avgDistance;

    /**
     * Total distance covered by each aptrol.
     */
    private XYSeries totalDistance;

    /**
     * All the total distances values, the average is calculate and the X value
     * is represented by it's position.
     */
    private final LinkedList<Double> info = new LinkedList<>();

    /**
     * Create the chart empty.
     *
     * @param sim where the chart will be created.
     */
    public DistanceCoveredChart_Class(PoliceCarsSimulation_UI sim) {
        distanceCoveredChart = new TimeSeriesChartGenerator();
        distanceCoveredChart.setTitle("Distancia recorrida hasta la emergencia (metros)");
        distanceCoveredChart.setYAxisLabel("Distancia recorrida");
        distanceCoveredChart.setXAxisLabel("Demandas satisfechas");
        chartFrame = distanceCoveredChart.createFrame(sim);
        chartFrame.pack();
        totalDistance = new XYSeries("Distancia Total");
        avgDistance = new XYSeries("Distancia Promedio");
        distanceCoveredChart.addSeries(totalDistance, null);
        distanceCoveredChart.addSeries(avgDistance, null);
    }

    /**
     * Add the especific info to the chart.
     *
     * @param distance to add.
     */
    public void addInfo(double distance) {
        info.add(distance);

        double avg = 0;
        for (Double d : info) {
            avg += d;
        }
        avg /= info.size();
        avgDistance.add(info.size(), avg, true);

        totalDistance.add(info.size(), distance, true);
    }

    public TimeSeriesChartGenerator getDistanceCoveredChart() {
        return distanceCoveredChart;
    }

    public void setDistanceCoveredChart(TimeSeriesChartGenerator distanceCoveredChart) {
        this.distanceCoveredChart = distanceCoveredChart;
    }

    public JFrame getChartFrame() {
        return chartFrame;
    }

    public void setChartFrame(JFrame chartFrame) {
        this.chartFrame = chartFrame;
    }

    public XYSeries getAvgDistance() {
        return avgDistance;
    }

    public void setAvgDistance(XYSeries avgDistance) {
        this.avgDistance = avgDistance;
    }

    public XYSeries getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(XYSeries totalDistance) {
        this.totalDistance = totalDistance;
    }

}
