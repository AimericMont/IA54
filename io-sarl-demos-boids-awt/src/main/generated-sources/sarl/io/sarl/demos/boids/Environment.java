/**
 * $Id$
 * 
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 * 
 * Copyright (C) 2014-2020 the original authors or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sarl.demos.boids;

import com.google.common.base.Objects;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.ParticipantLeft;
import io.sarl.core.Schedules;
import io.sarl.demos.boids.Action;
import io.sarl.demos.boids.BoidsSimulation;
import io.sarl.demos.boids.BoidsSimulationLauncher;
import io.sarl.demos.boids.Contact;
import io.sarl.demos.boids.Die;
import io.sarl.demos.boids.GuiRepaint;
import io.sarl.demos.boids.PerceivedBoidBody;
import io.sarl.demos.boids.Perception;
import io.sarl.demos.boids.Settings;
import io.sarl.demos.boids.Start;
import io.sarl.demos.boids.counterChoice;
import io.sarl.demos.boids.killChart;
import io.sarl.demos.boids.resetData;
import io.sarl.demos.boids.updateContactChart;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * The environmental agent in charge of collecting boids influences and computing the new state of the virtual world
 * @author Nicolas Gaud
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class Environment extends Agent {
  @Accessors
  private int width;
  
  @Accessors
  private int height;
  
  @Accessors
  private ConcurrentHashMap<UUID, PerceivedBoidBody> boids;
  
  @Accessors
  private ConcurrentSkipListSet<UUID> influences;
  
  @Accessors
  private ConcurrentHashMap<UUID, ConcurrentHashMap<UUID, Contact>> mapOfMap;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName("Environment");
    int _size = ((List<Object>)Conversions.doWrapArray(occurrence.parameters)).size();
    if ((_size > 1)) {
      Object _get = occurrence.parameters[0];
      if ((_get instanceof Integer)) {
        Object _get_1 = occurrence.parameters[0];
        this.height = ((((Integer) _get_1)) == null ? 0 : (((Integer) _get_1)).intValue());
      }
      Object _get_2 = occurrence.parameters[1];
      if ((_get_2 instanceof Integer)) {
        Object _get_3 = occurrence.parameters[1];
        this.width = ((((Integer) _get_3)) == null ? 0 : (((Integer) _get_3)).intValue());
      }
      this.boids = null;
      ConcurrentSkipListSet<UUID> _concurrentSkipListSet = new ConcurrentSkipListSet<UUID>();
      this.influences = _concurrentSkipListSet;
      ConcurrentHashMap<UUID, ConcurrentHashMap<UUID, Contact>> _concurrentHashMap = new ConcurrentHashMap<UUID, ConcurrentHashMap<UUID, Contact>>();
      this.mapOfMap = _concurrentHashMap;
      int tempo = 400;
      if ((BoidsSimulationLauncher.scenario == 1)) {
        tempo = 300;
      } else {
        if ((BoidsSimulationLauncher.scenario == 3)) {
          tempo = 700;
        }
      }
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
      final Procedure1<Agent> _function = (Agent it) -> {
        BoidsSimulationLauncher.lock.set(true);
        BoidsSimulationLauncher.lock2.set(true);
        BoidsSimulationLauncher.lock3.set(true);
        BoidsSimulationLauncher.lock4.set(true);
      };
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.every(tempo, _function);
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
      final Procedure1<Agent> _function_1 = (Agent it) -> {
        BoidsSimulationLauncher.lock5.set(true);
      };
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_1.every(300, _function_1);
      Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
      final Procedure1<Agent> _function_2 = (Agent it) -> {
        BoidsSimulationLauncher.exitLock.set(true);
      };
      _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER_2.every(75, _function_2);
    }
  }
  
  private void $behaviorUnit$Start$1(final Start occurrence) {
    this.boids = occurrence.perceivedAgentBody;
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new GuiRepaint(this.boids));
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(new Perception(this.boids));
  }
  
  private void $behaviorUnit$Action$2(final Action occurrence) {
    synchronized (this.boids) {
      synchronized (this.influences) {
        synchronized (this.mapOfMap) {
          UUID id = occurrence.getSource().getUUID();
          boolean _containsKey = this.boids.containsKey(id);
          if (_containsKey) {
            this.influences.add(id);
            this.applyForce(occurrence.influence, this.boids.get(id), occurrence.destx, occurrence.desty, 
              occurrence.stp, occurrence.group, occurrence.booleanSeven);
          }
          boolean _containsKey_1 = this.mapOfMap.containsKey(id);
          if (_containsKey_1) {
            this.mapOfMap.replace(id, occurrence.map);
          } else {
            this.mapOfMap.put(id, occurrence.map);
          }
          int _size = this.influences.size();
          int _size_1 = this.boids.size();
          if ((_size == _size_1)) {
            Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
            final Procedure1<Agent> _function = (Agent it) -> {
              if ((BoidsSimulationLauncher.scenario == 3)) {
                this.enter(this.boids, 0, 1, BoidsSimulationLauncher.lock);
                this.enter(this.boids, 0, 2, BoidsSimulationLauncher.lock2);
                this.enter(this.boids, 0, 3, BoidsSimulationLauncher.lock3);
                this.enter(this.boids, 0, 4, BoidsSimulationLauncher.lock4);
              } else {
                if ((BoidsSimulationLauncher.scenario == 2)) {
                  this.enter(this.boids, 0, 2, BoidsSimulationLauncher.lock);
                  this.enter(this.boids, 0, 3, BoidsSimulationLauncher.lock2);
                } else {
                  this.enter(this.boids, 0, 3, BoidsSimulationLauncher.lock);
                }
              }
              this.enter(this.boids, 7, 0, BoidsSimulationLauncher.lock5);
              this.exit(this.boids, 15);
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              class $SerializableClosureProxy implements Scope<Address> {
                
                private final UUID $_id_1;
                
                public $SerializableClosureProxy(final UUID $_id_1) {
                  this.$_id_1 = $_id_1;
                }
                
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  return Objects.equal(_uUID, $_id_1);
                }
              }
              final Scope<Address> _function_1 = new Scope<Address>() {
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  return Objects.equal(_uUID, BoidsSimulation.id);
                }
                private Object writeReplace() throws ObjectStreamException {
                  return new SerializableProxy($SerializableClosureProxy.class, BoidsSimulation.id);
                }
              };
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(new GuiRepaint(this.boids), _function_1);
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(new Perception(this.boids));
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(new updateContactChart(this.mapOfMap));
              if (Settings.isLogActivated) {
                Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
                _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("New Simulation step.");
              }
            };
            _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.in(Settings.pause, _function);
            this.influences.clear();
          }
        }
      }
    }
  }
  
  private void $behaviorUnit$resetData$3(final resetData occurrence) {
    synchronized (this.mapOfMap) {
      this.mapOfMap.clear();
    }
  }
  
  private void $behaviorUnit$Die$4(final Die occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$ParticipantLeft$5(final ParticipantLeft occurrence) {
    boolean _isEmpty = this.boids.isEmpty();
    if (_isEmpty) {
      killChart ev = new killChart();
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.error("EMIT");
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev);
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
    }
  }
  
  protected void applyForce(final Vector2d force, final PerceivedBoidBody b, final int xdest, final int ydest, final int stp, final int sg, final boolean booleanSeven) {
    Vector2d acceleration = b.getAcceleration();
    Vector2d vitesse = b.getVitesse();
    Vector2d position = b.getPosition();
    double _x = force.getX();
    double _x_1 = b.getPosition().getX();
    force.setX((_x - _x_1));
    double _y = force.getY();
    double _y_1 = b.getPosition().getY();
    force.setY((_y - _y_1));
    double _length = force.getLength();
    if ((_length > b.getGroup().maxForce)) {
      force.setLength(b.getGroup().maxForce);
    }
    acceleration.set(force);
    vitesse.operator_add(acceleration);
    double _length_1 = vitesse.getLength();
    if ((_length_1 > b.getGroup().maxSpeed)) {
      vitesse.setLength(b.getGroup().maxSpeed);
    }
    position.operator_add(force);
    if ((stp == 0)) {
      double _x_2 = position.getX();
      if ((_x_2 < xdest)) {
        position.setX(xdest);
      }
      double _y_2 = position.getY();
      if ((_y_2 > (ydest + 4))) {
        position.setY((ydest + 4));
      }
      double _y_3 = position.getY();
      if ((_y_3 < (ydest - 4))) {
        position.setY((ydest - 4));
      }
    } else {
      if ((stp == 7)) {
        double _x_3 = position.getX();
        if ((_x_3 > xdest)) {
          position.setX(xdest);
        }
        double _y_4 = position.getY();
        if ((_y_4 > (ydest + 20))) {
          position.setY((ydest + 20));
        }
        if (((position.getY() < (ydest - 20)) && booleanSeven)) {
          position.setY((ydest - 20));
        }
      } else {
        if ((stp == 15)) {
          double _x_4 = position.getX();
          if ((_x_4 < (xdest - 10))) {
            position.setX(xdest);
          }
          double _y_5 = position.getY();
          if ((_y_5 < ydest)) {
            position.setY(ydest);
          }
        }
      }
    }
    PerceivedBoidBody bb = this.boids.get(b.getOwner());
    bb.setStep(stp);
    bb.setAcceleration(acceleration);
    bb.setVitesse(vitesse);
    bb.setPosition(position);
    bb.setXdest(xdest);
    bb.setYdest(ydest);
    bb.setSeatGroup(sg);
  }
  
  protected void enter(final ConcurrentHashMap<UUID, PerceivedBoidBody> otherBoids, final int step, final int guichet, final AtomicBoolean lock) {
    UUID tmpUUID = null;
    double smallestDist = 100.0;
    Collection<PerceivedBoidBody> _values = otherBoids.values();
    for (final PerceivedBoidBody otherBoid : _values) {
      if (((otherBoid.getStep() == step) && (otherBoid.getGuichet() == guichet))) {
        double _x = otherBoid.getPosition().getX();
        int _xdest = otherBoid.getXdest();
        double _pow = Math.pow((_x - _xdest), 2);
        double _y = otherBoid.getPosition().getY();
        int _ydest = otherBoid.getYdest();
        double _pow_1 = Math.pow((_y - _ydest), 2);
        double tmp = Math.sqrt((_pow + _pow_1));
        if (((otherBoid != null) && (tmp < smallestDist))) {
          smallestDist = tmp;
          tmpUUID = otherBoid.getOwner();
        }
      } else {
        if (((otherBoid.getStep() == step) && (guichet == 0))) {
          double _x_1 = otherBoid.getPosition().getX();
          int _xdest_1 = otherBoid.getXdest();
          double _pow_2 = Math.pow((_x_1 - _xdest_1), 2);
          double _y_1 = otherBoid.getPosition().getY();
          int _ydest_1 = otherBoid.getYdest();
          double _pow_3 = Math.pow((_y_1 - _ydest_1), 2);
          double tmp_1 = Math.sqrt((_pow_2 + _pow_3));
          if (((otherBoid != null) && (tmp_1 < smallestDist))) {
            smallestDist = tmp_1;
            tmpUUID = otherBoid.getOwner();
          }
        }
      }
    }
    if ((smallestDist < 4.0)) {
      final UUID id = tmpUUID;
      boolean _get = lock.get();
      if (_get) {
        lock.set(false);
        counterChoice ev = new counterChoice();
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        class $SerializableClosureProxy implements Scope<Address> {
          
          private final UUID id;
          
          public $SerializableClosureProxy(final UUID id) {
            this.id = id;
          }
          
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            return Objects.equal(_uUID, id);
          }
        }
        final Scope<Address> _function = new Scope<Address>() {
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            return Objects.equal(_uUID, id);
          }
          private Object writeReplace() throws ObjectStreamException {
            return new SerializableProxy($SerializableClosureProxy.class, id);
          }
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
      }
    }
  }
  
  protected void exit(final ConcurrentHashMap<UUID, PerceivedBoidBody> otherBoids, final int step) {
    UUID tmpUUID = null;
    double smallestDist = 100.0;
    Collection<PerceivedBoidBody> _values = otherBoids.values();
    for (final PerceivedBoidBody otherBoid : _values) {
      if (((otherBoid != null) && (otherBoid.getStep() >= step))) {
        double _x = otherBoid.getPosition().getX();
        double _pow = Math.pow((_x + 300), 2);
        double _y = otherBoid.getPosition().getY();
        double _pow_1 = Math.pow((_y + 300), 2);
        double tmp = Math.sqrt((_pow + _pow_1));
        if ((tmp < smallestDist)) {
          smallestDist = tmp;
          tmpUUID = otherBoid.getOwner();
        }
      }
    }
    if (((smallestDist < 4.0) && BoidsSimulationLauncher.exitLock.get())) {
      BoidsSimulationLauncher.exitLock.set(false);
      final UUID id = tmpUUID;
      this.influences.remove(id);
      this.boids.remove(id);
      Die ev = new Die();
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      class $SerializableClosureProxy implements Scope<Address> {
        
        private final UUID id;
        
        public $SerializableClosureProxy(final UUID id) {
          this.id = id;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, id);
        }
      }
      final Scope<Address> _function = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, id);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy.class, id);
        }
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
    }
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
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$resetData(final resetData occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$resetData$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Start(final Start occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Start$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Action(final Action occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Action$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ParticipantLeft(final ParticipantLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ParticipantLeft$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Die(final Die occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Die$4(occurrence));
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
    Environment other = (Environment) obj;
    if (other.width != this.width)
      return false;
    if (other.height != this.height)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.width);
    result = prime * result + Integer.hashCode(this.height);
    return result;
  }
  
  @SyntheticMember
  public Environment(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Environment(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Environment(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
  
  @Pure
  protected int getWidth() {
    return this.width;
  }
  
  protected void setWidth(final int width) {
    this.width = width;
  }
  
  @Pure
  protected int getHeight() {
    return this.height;
  }
  
  protected void setHeight(final int height) {
    this.height = height;
  }
  
  @Pure
  protected ConcurrentHashMap<UUID, PerceivedBoidBody> getBoids() {
    return this.boids;
  }
  
  protected void setBoids(final ConcurrentHashMap<UUID, PerceivedBoidBody> boids) {
    this.boids = boids;
  }
  
  @Pure
  protected ConcurrentSkipListSet<UUID> getInfluences() {
    return this.influences;
  }
  
  protected void setInfluences(final ConcurrentSkipListSet<UUID> influences) {
    this.influences = influences;
  }
  
  @Pure
  protected ConcurrentHashMap<UUID, ConcurrentHashMap<UUID, Contact>> getMapOfMap() {
    return this.mapOfMap;
  }
  
  protected void setMapOfMap(final ConcurrentHashMap<UUID, ConcurrentHashMap<UUID, Contact>> mapOfMap) {
    this.mapOfMap = mapOfMap;
  }
}
