package io.sarl.demos.boids;

import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.Schedules;
import io.sarl.demos.boids.BoidsSimulationLauncher;
import io.sarl.demos.boids.Charts;
import io.sarl.demos.boids.Contact;
import io.sarl.demos.boids.killChart;
import io.sarl.demos.boids.resetData;
import io.sarl.demos.boids.updateChart;
import io.sarl.demos.boids.updateContactChart;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author benjamin
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class Chart extends Agent {
  private Charts chart1 = new Charts("1--Contact > 0.167s");
  
  private Charts chart2 = new Charts("1--Contact > 10s");
  
  private Charts chart3 = new Charts("1--Contact > 15s");
  
  private ArrayList<Charts> listGraph = new ArrayList<Charts>();
  
  private boolean graph1 = true;
  
  private boolean graph2 = true;
  
  private boolean graph3 = true;
  
  private boolean graph4 = true;
  
  private AtomicBoolean lockGraph = new AtomicBoolean();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName("Chart Agent");
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info("The agent was started.");
    this.lockGraph.set(false);
  }
  
  private void $behaviorUnit$updateContactChart$1(final updateContactChart occurrence) {
    float value1 = 0;
    float value2 = 0;
    float value3 = 0;
    Collection<ConcurrentHashMap<UUID, Contact>> _values = occurrence.map.values();
    for (final ConcurrentHashMap<UUID, Contact> element : _values) {
      Collection<Contact> _values_1 = element.values();
      for (final Contact element2 : _values_1) {
        long _time = element2.getTime();
        if ((_time >= BoidsSimulationLauncher.TIME_CONTACT_3)) {
          value1++;
          value2++;
          value3++;
        } else {
          long _time_1 = element2.getTime();
          if ((_time_1 >= BoidsSimulationLauncher.TIME_CONTACT_2)) {
            value1++;
            value2++;
          } else {
            long _time_2 = element2.getTime();
            if ((_time_2 >= BoidsSimulationLauncher.TIME_CONTACT_1)) {
              value1++;
            }
          }
        }
      }
    }
    if (((BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_4) && this.graph4)) {
      this.graph4 = false;
      this.listGraph.add(this.chart1);
      this.listGraph.add(this.chart2);
      this.listGraph.add(this.chart3);
      Double _valueOf = Double.valueOf(BoidsSimulationLauncher.currentTime);
      Charts _charts = new Charts("5--Contact > 0.167s", Double.valueOf((((_valueOf) == null ? 0 : (_valueOf).doubleValue()) / 1000)), Double.valueOf(0.0));
      this.chart1 = _charts;
      Double _valueOf_1 = Double.valueOf(BoidsSimulationLauncher.currentTime);
      Charts _charts_1 = new Charts("5--Contact > 10s", Double.valueOf((((_valueOf_1) == null ? 0 : (_valueOf_1).doubleValue()) / 1000)), Double.valueOf(0.0));
      this.chart2 = _charts_1;
      Double _valueOf_2 = Double.valueOf(BoidsSimulationLauncher.currentTime);
      Charts _charts_2 = new Charts("5--Contact > 15s", Double.valueOf((((_valueOf_2) == null ? 0 : (_valueOf_2).doubleValue()) / 1000)), Double.valueOf(0.0));
      this.chart3 = _charts_2;
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new resetData());
      this.lockGraph.set(true);
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
      final Procedure1<Agent> _function = (Agent it) -> {
        this.lockGraph.set(false);
      };
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.in(500, _function);
    } else {
      if (((BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_3) && this.graph3)) {
        this.graph3 = false;
        this.listGraph.add(this.chart1);
        this.listGraph.add(this.chart2);
        this.listGraph.add(this.chart3);
        Double _valueOf_3 = Double.valueOf(BoidsSimulationLauncher.currentTime);
        Charts _charts_3 = new Charts("4--Contact > 0.167s", Double.valueOf((((_valueOf_3) == null ? 0 : (_valueOf_3).doubleValue()) / 1000)), 
          Double.valueOf(0.0));
        this.chart1 = _charts_3;
        Double _valueOf_4 = Double.valueOf(BoidsSimulationLauncher.currentTime);
        Charts _charts_4 = new Charts("4--Contact > 10s", Double.valueOf((((_valueOf_4) == null ? 0 : (_valueOf_4).doubleValue()) / 1000)), Double.valueOf(0.0));
        this.chart2 = _charts_4;
        Double _valueOf_5 = Double.valueOf(BoidsSimulationLauncher.currentTime);
        Charts _charts_5 = new Charts("4--Contact > 15s", Double.valueOf((((_valueOf_5) == null ? 0 : (_valueOf_5).doubleValue()) / 1000)), Double.valueOf(0.0));
        this.chart3 = _charts_5;
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(new resetData());
        this.lockGraph.set(true);
        Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
        final Procedure1<Agent> _function_1 = (Agent it) -> {
          this.lockGraph.set(false);
        };
        _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_1.in(500, _function_1);
      } else {
        if (((BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_2) && this.graph2)) {
          this.graph2 = false;
          this.listGraph.add(this.chart1);
          this.listGraph.add(this.chart2);
          this.listGraph.add(this.chart3);
          Double _valueOf_6 = Double.valueOf(BoidsSimulationLauncher.currentTime);
          Charts _charts_6 = new Charts("3--Contact > 0.167s", 
            Double.valueOf((((_valueOf_6) == null ? 0 : (_valueOf_6).doubleValue()) / 1000)), Double.valueOf(0.0));
          this.chart1 = _charts_6;
          Double _valueOf_7 = Double.valueOf(BoidsSimulationLauncher.currentTime);
          Charts _charts_7 = new Charts("3--Contact > 10s", Double.valueOf((((_valueOf_7) == null ? 0 : (_valueOf_7).doubleValue()) / 1000)), Double.valueOf(0.0));
          this.chart2 = _charts_7;
          Double _valueOf_8 = Double.valueOf(BoidsSimulationLauncher.currentTime);
          Charts _charts_8 = new Charts("3--Contact > 15s", Double.valueOf((((_valueOf_8) == null ? 0 : (_valueOf_8).doubleValue()) / 1000)), Double.valueOf(0.0));
          this.chart3 = _charts_8;
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(new resetData());
          this.lockGraph.set(true);
          Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
          final Procedure1<Agent> _function_2 = (Agent it) -> {
            this.lockGraph.set(false);
          };
          _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_2.in(500, _function_2);
        } else {
          if (((BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_1) && this.graph1)) {
            this.graph1 = false;
            this.listGraph.add(this.chart1);
            this.listGraph.add(this.chart2);
            this.listGraph.add(this.chart3);
            Double _valueOf_9 = Double.valueOf(BoidsSimulationLauncher.currentTime);
            Charts _charts_9 = new Charts("2--Contact > 0.167s", 
              Double.valueOf((((_valueOf_9) == null ? 0 : (_valueOf_9).doubleValue()) / 1000)), Double.valueOf(0.0));
            this.chart1 = _charts_9;
            Double _valueOf_10 = Double.valueOf(BoidsSimulationLauncher.currentTime);
            Charts _charts_10 = new Charts("2--Contact > 10s", Double.valueOf((((_valueOf_10) == null ? 0 : (_valueOf_10).doubleValue()) / 1000)), Double.valueOf(0.0));
            this.chart2 = _charts_10;
            Double _valueOf_11 = Double.valueOf(BoidsSimulationLauncher.currentTime);
            Charts _charts_11 = new Charts("2--Contact > 15s", Double.valueOf((((_valueOf_11) == null ? 0 : (_valueOf_11).doubleValue()) / 1000)), Double.valueOf(0.0));
            this.chart3 = _charts_11;
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
            _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_3.emit(new resetData());
            this.lockGraph.set(true);
            Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_3 = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
            final Procedure1<Agent> _function_3 = (Agent it) -> {
              this.lockGraph.set(false);
            };
            _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_3.in(500, _function_3);
          } else {
            boolean _get = this.lockGraph.get();
            if ((!_get)) {
              this.chart1.updateValues(Float.valueOf((BoidsSimulationLauncher.currentTime / 1000)), Float.valueOf((value1 / 120)));
              this.chart2.updateValues(Float.valueOf((BoidsSimulationLauncher.currentTime / 1000)), Float.valueOf((value2 / 120)));
              this.chart3.updateValues(Float.valueOf((BoidsSimulationLauncher.currentTime / 1000)), Float.valueOf((value3 / 120)));
            }
          }
        }
      }
    }
  }
  
  private void $behaviorUnit$updateChart$2(final updateChart occurrence) {
  }
  
  private void $behaviorUnit$killChart$3(final killChart occurrence) {
    this.listGraph.add(this.chart1);
    this.listGraph.add(this.chart2);
    this.listGraph.add(this.chart3);
    Charts.drawStaticChart(this.listGraph);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.error("CHART DEAD");
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @Extension
  @ImportedCapacityFeature(Schedules.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_SCHEDULES;
  
  @SyntheticMember
  @Pure
  private Schedules $CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class);
    }
    return $castSkill(Schedules.class, this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$killChart(final killChart occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$killChart$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$updateChart(final updateChart occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$updateChart$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$updateContactChart(final updateContactChart occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$updateContactChart$1(occurrence));
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
    Chart other = (Chart) obj;
    if (other.graph1 != this.graph1)
      return false;
    if (other.graph2 != this.graph2)
      return false;
    if (other.graph3 != this.graph3)
      return false;
    if (other.graph4 != this.graph4)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Boolean.hashCode(this.graph1);
    result = prime * result + Boolean.hashCode(this.graph2);
    result = prime * result + Boolean.hashCode(this.graph3);
    result = prime * result + Boolean.hashCode(this.graph4);
    return result;
  }
  
  @SyntheticMember
  public Chart(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Chart(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Chart(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
