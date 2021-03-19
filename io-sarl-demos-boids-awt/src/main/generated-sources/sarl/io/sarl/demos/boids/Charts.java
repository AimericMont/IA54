package io.sarl.demos.boids;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JFrame;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 * Panel for charts and data handling.
 * 
 * @author AD
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Charts {
  private XYChart chart = null;
  
  private ConcurrentLinkedQueue<Double> infected;
  
  private ConcurrentLinkedQueue<Double> time;
  
  private XChartPanel<XYChart> chartPanel = null;
  
  private String serie = null;
  
  public Charts(final String s) {
    ConcurrentLinkedQueue<Double> _concurrentLinkedQueue = new ConcurrentLinkedQueue<Double>();
    this.time = _concurrentLinkedQueue;
    ConcurrentLinkedQueue<Double> _concurrentLinkedQueue_1 = new ConcurrentLinkedQueue<Double>();
    this.infected = _concurrentLinkedQueue_1;
    this.infected.add(Double.valueOf(0.0));
    this.time.add(Double.valueOf(0.0));
    this.serie = s;
  }
  
  public Charts(final String s, final Double time, final Double infected) {
    ConcurrentLinkedQueue<Double> _concurrentLinkedQueue = new ConcurrentLinkedQueue<Double>();
    this.time = _concurrentLinkedQueue;
    ConcurrentLinkedQueue<Double> _concurrentLinkedQueue_1 = new ConcurrentLinkedQueue<Double>();
    this.infected = _concurrentLinkedQueue_1;
    this.infected.add(infected);
    this.time.add(time);
    this.serie = s;
  }
  
  public Charts(final Charts c) {
    this.chart = c.chart;
    this.time = c.time;
    this.infected = c.infected;
    this.chartPanel = c.chartPanel;
    this.serie = c.serie;
  }
  
  @Pure
  public ConcurrentLinkedQueue<Double> getInfected() {
    return this.infected;
  }
  
  @Pure
  public ConcurrentLinkedQueue<Double> getTime() {
    return this.time;
  }
  
  @Pure
  public String getserie() {
    return this.serie;
  }
  
  public void updateValues(final Float time, final Float infected) {
    long _round = Math.round((((infected) == null ? 0 : (infected).floatValue()) * 100.0));
    double tmp = (_round / 100.0);
    this.infected.add(Double.valueOf(tmp));
    this.time.add((time == null ? null : Double.valueOf(time.doubleValue())));
  }
  
  public static JFrame drawStaticChart(final ArrayList<Charts> l) {
    JFrame _xblockexpression = null;
    {
      ArrayList<XYChart> charts = new ArrayList<XYChart>();
      int i = 1;
      for (final Charts list : l) {
        {
          String s = ("./Sample_Chart/" + Integer.valueOf(i));
          XYChart chart = new XYChartBuilder().xAxisTitle("Time").yAxisTitle("Contact").width(600).height(400).build();
          XYSeries series = chart.addSeries(list.serie, ((double[])Conversions.unwrapArray(list.getTime(), double.class)), ((double[])Conversions.unwrapArray(list.getInfected(), double.class)));
          series.setMarker(SeriesMarkers.NONE);
          charts.add(chart);
          i = (i + 1);
        }
      }
      _xblockexpression = new SwingWrapper<XYChart>(charts).displayChartMatrix();
    }
    return _xblockexpression;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Charts other = (Charts) obj;
    if (!Objects.equals(this.serie, other.serie))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.serie);
    return result;
  }
}
