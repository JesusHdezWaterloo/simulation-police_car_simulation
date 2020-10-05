package Chart;

import PoliceCarsSimulation.PoliceCarsSimulation_UI;
import java.util.LinkedList;
import javax.swing.JFrame;
import org.jfree.data.xy.XYSeries;
import sim.util.media.chart.TimeSeriesChartGenerator;

/**
 * Class that describe the response time chart.
 *
 * @author Margaret Sánchez Martínez
 */
public class ResponseTimeChart_Class {

    /**
     * The chart.
     */
    private TimeSeriesChartGenerator responseTimeChart;

    /**
     * Frame where the chart live.
     */
    private JFrame chartFrame;

    /**
     * Average delay covered by each patrol.
     */
    private XYSeries avgDelay;

    /**
     * Total delay covered by each patrol.
     */
    private XYSeries totalDelay;

    /**
     * All the total times values, the average is calculate and the X value is
     * represented by it's position.
     */
    private final LinkedList<Double> info = new LinkedList<>();

    /**
     * Create the chart empty.
     *
     * @param sim where the chart will be created.
     */
    public ResponseTimeChart_Class(PoliceCarsSimulation_UI sim) {
        responseTimeChart = new TimeSeriesChartGenerator();
        responseTimeChart.setTitle("Tiempo de Respuesta (pasos)");
        responseTimeChart.setYAxisLabel("Tiempo de respuesta");
        responseTimeChart.setXAxisLabel("Demandas satisfechas");
        chartFrame = responseTimeChart.createFrame(sim);
        chartFrame.pack();
        totalDelay = new XYSeries("Tiempo de demoras");
        avgDelay = new XYSeries("Promedio de demoras");
        responseTimeChart.addSeries(totalDelay, null);
        responseTimeChart.addSeries(avgDelay, null);
    }

    /**
     * Add the especific info to the chart.
     *
     * @param delay to add.
     */
    public void addInfo(double delay) {
        info.add(delay);

        double avg = 0;
        for (Double d : info) {
            avg += d;
        }
        avg /= info.size();
        avgDelay.add(info.size(), avg, true);

        totalDelay.add(info.size(), delay, true);
    }

    public TimeSeriesChartGenerator getTrafficChart() {
        return responseTimeChart;
    }

    public XYSeries getAvgDelay() {
        return avgDelay;
    }

    public JFrame getChartFrame() {
        return chartFrame;
    }

    public XYSeries getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(XYSeries totalDelay) {
        this.totalDelay = totalDelay;
    }

    public void setTrafficChart(TimeSeriesChartGenerator trafficChart) {
        this.responseTimeChart = trafficChart;
    }

    public void setAvgDelay(XYSeries avgDelay) {
        this.avgDelay = avgDelay;
    }

    public void setChartFrame(JFrame chartFrame) {
        this.chartFrame = chartFrame;
    }

}
