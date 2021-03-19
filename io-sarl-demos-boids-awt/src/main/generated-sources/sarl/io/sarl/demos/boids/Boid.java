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
import io.sarl.core.Schedules;
import io.sarl.demos.boids.Action;
import io.sarl.demos.boids.BoidsSimulationLauncher;
import io.sarl.demos.boids.ChangeBoidGroup;
import io.sarl.demos.boids.Contact;
import io.sarl.demos.boids.Die;
import io.sarl.demos.boids.PerceivedBoidBody;
import io.sarl.demos.boids.Perception;
import io.sarl.demos.boids.Population;
import io.sarl.demos.boids.Settings;
import io.sarl.demos.boids.counterChoice;
import io.sarl.demos.boids.resetData;
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
import java.awt.Color;
import java.io.ObjectStreamException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * A boid agent evolving according C. Reynolds basic behavioral rules
 * @author Nicolas Gaud
 */
@SuppressWarnings("potential_field_synchronization_problem")
@SarlSpecification("0.11")
@SarlElementType(19)
public class Boid extends Agent {
  private UUID environment;
  
  private Vector2d position;
  
  private Vector2d speed;
  
  private Population group;
  
  private int xdest;
  
  private int ydest;
  
  private int step;
  
  private int seatNumber;
  
  private int seatGroup;
  
  private ConcurrentHashMap<UUID, Contact> contactMap;
  
  private boolean catering;
  
  private int nbx;
  
  private int nby;
  
  private int dist;
  
  private boolean booleanSeven;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    int _size = ((List<Object>)Conversions.doWrapArray(occurrence.parameters)).size();
    if ((_size > 4)) {
      Object _get = occurrence.parameters[0];
      if ((_get instanceof UUID)) {
        Object _get_1 = occurrence.parameters[0];
        this.environment = ((UUID) _get_1);
      }
      Object _get_2 = occurrence.parameters[1];
      if ((_get_2 instanceof Population)) {
        Object _get_3 = occurrence.parameters[1];
        this.group = ((Population) _get_3);
      }
      Object _get_4 = occurrence.parameters[2];
      if ((_get_4 instanceof Vector2d)) {
        Object _get_5 = occurrence.parameters[2];
        this.position = ((Vector2d) _get_5);
      }
      Object _get_6 = occurrence.parameters[3];
      if ((_get_6 instanceof Vector2d)) {
        Object _get_7 = occurrence.parameters[3];
        this.speed = ((Vector2d) _get_7);
        this.speed.setLength(1);
        this.speed.scale(this.group.maxSpeed);
      }
      Object _get_8 = occurrence.parameters[4];
      if ((_get_8 instanceof String)) {
        Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
        Object _get_9 = occurrence.parameters[4];
        _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName((_get_9 == null ? null : _get_9.toString()));
      }
      Object _get_10 = occurrence.parameters[5];
      if ((_get_10 instanceof Integer)) {
        Object _get_11 = occurrence.parameters[5];
        this.seatNumber = ((((Integer) _get_11)) == null ? 0 : (((Integer) _get_11)).intValue());
      }
      Object _get_12 = occurrence.parameters[6];
      if ((_get_12 instanceof Integer)) {
        Object _get_13 = occurrence.parameters[6];
        this.seatGroup = ((((Integer) _get_13)) == null ? 0 : (((Integer) _get_13)).intValue());
      }
      Object _get_14 = occurrence.parameters[7];
      if ((_get_14 instanceof Boolean)) {
        Object _get_15 = occurrence.parameters[7];
        this.catering = ((((Boolean) _get_15)) == null ? false : (((Boolean) _get_15)).booleanValue());
      }
      this.step = 0;
      this.xdest = 250;
      if ((BoidsSimulationLauncher.scenario == 1)) {
        this.ydest = 60;
      } else {
        if ((BoidsSimulationLauncher.scenario == 2)) {
          if (((this.seatGroup == 1) || (this.seatGroup == 2))) {
            this.ydest = (-60);
          } else {
            this.ydest = 60;
          }
        } else {
          if ((BoidsSimulationLauncher.scenario == 3)) {
            if ((this.seatGroup == 1)) {
              this.ydest = (-180);
            } else {
              if ((this.seatGroup == 2)) {
                this.ydest = (-60);
              } else {
                if ((this.seatGroup == 3)) {
                  this.ydest = 60;
                } else {
                  this.ydest = 180;
                }
              }
            }
          }
        }
      }
      this.booleanSeven = false;
      this.nbx = BoidsSimulationLauncher.NB_X_SEAT;
      this.nby = BoidsSimulationLauncher.NB_Y_SEAT;
      this.dist = BoidsSimulationLauncher.DIST_BETWEEN_SEAT;
      ConcurrentHashMap<UUID, Contact> _concurrentHashMap = new ConcurrentHashMap<UUID, Contact>();
      this.contactMap = _concurrentHashMap;
    }
    if (Settings.isLogActivated) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info("Boids activated");
    }
  }
  
  private void $behaviorUnit$Perception$1(final Perception occurrence) {
    ConcurrentHashMap<UUID, PerceivedBoidBody> boids = occurrence.perceivedAgentBody;
    PerceivedBoidBody myBody = boids.get(this.getID());
    if (((myBody != null) && Objects.equal(myBody.getOwner(), this.getID()))) {
      this.position = myBody.getPosition();
      this.speed = myBody.getVitesse();
    }
    Schedules _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER();
    final Procedure1<Agent> _function = (Agent it) -> {
      Vector2d _think = this.think(boids.values());
      Action ev = new Action(_think, this.xdest, this.ydest, this.step, this.contactMap, this.seatGroup, this.booleanSeven);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      class $SerializableClosureProxy implements Scope<Address> {
        
        private final UUID $_environment;
        
        public $SerializableClosureProxy(final UUID $_environment) {
          this.$_environment = $_environment;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, $_environment);
        }
      }
      final Scope<Address> _function_1 = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, Boid.this.environment);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy.class, Boid.this.environment);
        }
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function_1);
      if (Settings.isLogActivated) {
        Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Sending Influences.");
      }
    };
    _$CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER.in(Settings.pause, _function);
  }
  
  private void $behaviorUnit$resetData$2(final resetData occurrence) {
    synchronized (this.contactMap) {
      this.contactMap.clear();
    }
  }
  
  private void $behaviorUnit$counterChoice$3(final counterChoice occurrence) {
    this.changeStep(true);
  }
  
  private void $behaviorUnit$Die$4(final Die occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  /**
   * The core boids behavior : aggregating all forces into a influence
   */
  protected Vector2d think(final Collection<PerceivedBoidBody> perception) {
    if ((perception != null)) {
      Vector2d force = null;
      Vector2d influence = new Vector2d();
      influence.set(this.xdest, this.ydest);
      if (this.group.separationOn) {
        force = this.separation(perception);
        force.scale(this.group.separationForce);
        influence.operator_add(force);
      }
      if ((!((this.step == 0) || (this.step == 7)))) {
        this.changeStep(false);
      }
      this.addContact(perception);
      return influence;
    }
    return null;
  }
  
  /**
   * Determine whether a body is visible or not according to the perception range
   */
  @Pure
  protected boolean isVisible(final PerceivedBoidBody otherBoid, final double distance) {
    Vector2d _position = otherBoid.getPosition();
    Vector2d tmp = _position.operator_minus(this.position);
    double _length = tmp.getLength();
    if ((_length > distance)) {
      return false;
    }
    Vector2d tmp2 = this.speed.clone();
    tmp2.normalize();
    double _multiply = tmp2.operator_multiply(tmp);
    if ((_multiply < this.group.visibleAngleCos)) {
      return false;
    }
    return true;
  }
  
  @Pure
  protected boolean isVisible360(final PerceivedBoidBody otherBoid, final double distance) {
    Vector2d _position = otherBoid.getPosition();
    Vector2d tmp = _position.operator_minus(this.position);
    double _length = tmp.getLength();
    if ((_length > distance)) {
      return false;
    }
    return true;
  }
  
  /**
   * Compute the separation force.
   */
  protected Vector2d separation(final Collection<PerceivedBoidBody> otherBoids) {
    Vector2d force = new Vector2d();
    double len = 0.0;
    for (final PerceivedBoidBody otherBoid : otherBoids) {
      if ((((otherBoid != null) && (otherBoid.getOwner() != this.getID())) && this.isVisible(otherBoid, this.group.distSeparation))) {
        Vector2d _position = otherBoid.getPosition();
        Vector2d tmp = this.position.operator_minus(_position);
        len = tmp.getLength();
        tmp.scale((len * len));
        force.operator_add(tmp);
      }
    }
    return force;
  }
  
  protected void infect(final Collection<PerceivedBoidBody> otherBoids) {
    for (final PerceivedBoidBody otherBoid : otherBoids) {
      if ((((((otherBoid != null) && (otherBoid.getOwner() != this.getID())) && Objects.equal(otherBoid.getGroup().color, Color.GREEN)) && Objects.equal(this.group.color, Color.RED)) && this.isVisible(otherBoid, 8))) {
        otherBoid.setGroup(this.group);
        ChangeBoidGroup ev = new ChangeBoidGroup(this.group);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        class $SerializableClosureProxy implements Scope<Address> {
          
          private final UUID $_owner;
          
          public $SerializableClosureProxy(final UUID $_owner) {
            this.$_owner = $_owner;
          }
          
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            return Objects.equal(_uUID, $_owner);
          }
        }
        final Scope<Address> _function = new Scope<Address>() {
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            UUID _owner = otherBoid.getOwner();
            return Objects.equal(_uUID, _owner);
          }
          private Object writeReplace() throws ObjectStreamException {
            return new SerializableProxy($SerializableClosureProxy.class, otherBoid.getOwner());
          }
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(ev, _function);
      }
    }
  }
  
  protected void addContact(final Collection<PerceivedBoidBody> otherBoids) {
    synchronized (this.contactMap) {
      for (final PerceivedBoidBody otherBoid : otherBoids) {
        {
          Contact contacttmp = this.contactMap.get(otherBoid.getOwner());
          if (((otherBoid != null) && (otherBoid.getOwner() != this.getID()))) {
            boolean _isVisible360 = this.isVisible360(otherBoid, this.group.distContact);
            if (_isVisible360) {
              if ((contacttmp == null)) {
                long _time = new Date().getTime();
                Contact _contact = new Contact(0, _time);
                this.contactMap.put(otherBoid.getOwner(), _contact);
              } else {
                long _lastStart = contacttmp.getLastStart();
                if ((_lastStart == 0)) {
                  contacttmp.setLastStart(new Date().getTime());
                } else {
                  long _time_1 = contacttmp.getTime();
                  long _time_2 = new Date().getTime();
                  long _lastStart_1 = contacttmp.getLastStart();
                  contacttmp.setTime(((_time_1 + _time_2) - _lastStart_1));
                  contacttmp.setLastStart(new Date().getTime());
                }
              }
            } else {
              if ((contacttmp == null)) {
                Contact _contact_1 = new Contact(0, 0);
                this.contactMap.put(otherBoid.getOwner(), _contact_1);
              } else {
                long _lastStart_2 = contacttmp.getLastStart();
                if ((_lastStart_2 != 0)) {
                  long _time_3 = contacttmp.getTime();
                  long _time_4 = new Date().getTime();
                  long _lastStart_3 = contacttmp.getLastStart();
                  contacttmp.setTime(((_time_3 + _time_4) - _lastStart_3));
                  contacttmp.setLastStart(0);
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected Object changeStep(final boolean bl) {
    Object _xifexpression = null;
    if ((((this.position.getX() == this.xdest) && (this.position.getY() == this.ydest)) || bl)) {
      Object _xblockexpression = null;
      {
        if (((this.step == 4) && (BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_2))) {
          if ((!this.catering)) {
            this.step = 12;
          } else {
            this.step = 5;
          }
        } else {
          if (((((((((this.step >= 0) && (this.step <= 3)) || (this.step == 5)) || (this.step == 6)) || (this.step == 7)) || ((this.step >= 8) && (this.step <= 11))) || ((this.step == 12) && (BoidsSimulationLauncher.currentTime >= BoidsSimulationLauncher.PHASE_4))) || (this.step >= 13))) {
            this.step = (this.step + 1);
          }
        }
        Object _xifexpression_1 = null;
        if ((this.step == 1)) {
          int _xblockexpression_1 = (int) 0;
          {
            this.xdest = 0;
            _xblockexpression_1 = this.ydest = ((((this.seatNumber / this.nbx) * this.dist) - ((this.dist * (this.nby - 1)) / 2)) - (this.dist / 2));
          }
          _xifexpression_1 = Integer.valueOf(_xblockexpression_1);
        } else {
          Object _xifexpression_2 = null;
          if ((this.step == 2)) {
            _xifexpression_2 = Integer.valueOf(this.xdest = (((-250) + ((this.seatNumber % this.nbx) * this.dist)) - (this.dist / 2)));
          } else {
            Object _xifexpression_3 = null;
            if (((((this.step == 3) || (this.step == 5)) || (this.step == 11)) || (this.step == 13))) {
              int _xblockexpression_2 = (int) 0;
              {
                this.xdest = (((-250) + ((this.seatNumber % this.nbx) * this.dist)) - (this.dist / 2));
                int _ydest = this.ydest;
                _xblockexpression_2 = this.ydest = (_ydest + (this.dist / 2));
              }
              _xifexpression_3 = Integer.valueOf(_xblockexpression_2);
            } else {
              Object _xifexpression_4 = null;
              if (((this.step == 4) || (this.step == 12))) {
                _xifexpression_4 = Integer.valueOf(this.xdest = ((-250) + ((this.seatNumber % this.nbx) * this.dist)));
              } else {
                Object _xifexpression_5 = null;
                if ((this.step == 6)) {
                  boolean _xblockexpression_3 = false;
                  {
                    this.ydest = ((((120 / this.nbx) * this.dist) - ((this.dist * (this.nby - 1)) / 2)) + this.dist);
                    if ((BoidsSimulationLauncher.scenario == 1)) {
                      this.ydest = ((((120 / this.nbx) * this.dist) - ((this.dist * (this.nby - 1)) / 2)) + this.dist);
                    } else {
                      this.ydest = ((((120 / (this.nbx / 2)) * this.dist) - ((this.dist * (this.nby - 1)) / 2)) + this.dist);
                    }
                    boolean _xifexpression_6 = false;
                    if (((this.position.getY() >= this.ydest) && (!this.booleanSeven))) {
                      _xifexpression_6 = this.booleanSeven = true;
                    }
                    _xblockexpression_3 = _xifexpression_6;
                  }
                  _xifexpression_5 = Boolean.valueOf(_xblockexpression_3);
                } else {
                  int _xifexpression_6 = (int) 0;
                  if ((this.step == 7)) {
                    int _xblockexpression_4 = (int) 0;
                    {
                      this.xdest = 0;
                      _xblockexpression_4 = this.ydest = 240;
                    }
                    _xifexpression_6 = _xblockexpression_4;
                  } else {
                    int _xifexpression_7 = (int) 0;
                    if ((this.step == 8)) {
                      int _xblockexpression_5 = (int) 0;
                      {
                        int _xdest = this.xdest;
                        this.xdest = (_xdest + this.dist);
                        int _ydest = this.ydest;
                        _xblockexpression_5 = this.ydest = (_ydest - this.dist);
                      }
                      _xifexpression_7 = _xblockexpression_5;
                    } else {
                      int _xifexpression_8 = (int) 0;
                      if ((this.step == 9)) {
                        _xifexpression_8 = this.ydest = ((((this.seatNumber / this.nbx) * this.dist) - ((this.dist * (this.nby - 1)) / 2)) - (this.dist / 2));
                      } else {
                        int _xifexpression_9 = (int) 0;
                        if ((this.step == 10)) {
                          _xifexpression_9 = this.xdest = (((-250) + ((this.seatNumber % this.nbx) * this.dist)) - (this.dist / 2));
                        } else {
                          int _xifexpression_10 = (int) 0;
                          if ((this.step == 11)) {
                            _xifexpression_10 = this.ydest = (((this.seatNumber / this.nbx) * this.dist) - ((this.dist * (this.nby - 1)) / 2));
                          } else {
                            int _xifexpression_11 = (int) 0;
                            if ((this.step == 13)) {
                              _xifexpression_11 = this.xdest = (((-250) + ((this.seatNumber % this.nbx) * this.dist)) - (this.dist / 2));
                            } else {
                              int _xifexpression_12 = (int) 0;
                              if ((this.step == 14)) {
                                _xifexpression_12 = this.ydest = ((-((this.dist * (this.nby - 1)) / 2)) - (this.dist / 2));
                              } else {
                                int _xifexpression_13 = (int) 0;
                                if ((this.step == 15)) {
                                  int _xblockexpression_6 = (int) 0;
                                  {
                                    this.xdest = (-300);
                                    _xblockexpression_6 = this.ydest = (-300);
                                  }
                                  _xifexpression_13 = _xblockexpression_6;
                                }
                                _xifexpression_12 = _xifexpression_13;
                              }
                              _xifexpression_11 = _xifexpression_12;
                            }
                            _xifexpression_10 = _xifexpression_11;
                          }
                          _xifexpression_9 = _xifexpression_10;
                        }
                        _xifexpression_8 = _xifexpression_9;
                      }
                      _xifexpression_7 = _xifexpression_8;
                    }
                    _xifexpression_6 = _xifexpression_7;
                  }
                  _xifexpression_5 = Integer.valueOf(_xifexpression_6);
                }
                _xifexpression_4 = ((Object)_xifexpression_5);
              }
              _xifexpression_3 = ((Object)_xifexpression_4);
            }
            _xifexpression_2 = ((Object)_xifexpression_3);
          }
          _xifexpression_1 = ((Object)_xifexpression_2);
        }
        _xblockexpression = ((Object)_xifexpression_1);
      }
      _xifexpression = ((Object)_xblockexpression);
    }
    return _xifexpression;
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
  
  /**
   * New attributes related to the concert context
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$counterChoice(final counterChoice occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$counterChoice$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$resetData(final resetData occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$resetData$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Die(final Die occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Die$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Perception(final Perception occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Perception$1(occurrence));
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
    Boid other = (Boid) obj;
    if (!java.util.Objects.equals(this.environment, other.environment))
      return false;
    if (other.xdest != this.xdest)
      return false;
    if (other.ydest != this.ydest)
      return false;
    if (other.step != this.step)
      return false;
    if (other.seatNumber != this.seatNumber)
      return false;
    if (other.seatGroup != this.seatGroup)
      return false;
    if (other.catering != this.catering)
      return false;
    if (other.nbx != this.nbx)
      return false;
    if (other.nby != this.nby)
      return false;
    if (other.dist != this.dist)
      return false;
    if (other.booleanSeven != this.booleanSeven)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + java.util.Objects.hashCode(this.environment);
    result = prime * result + Integer.hashCode(this.xdest);
    result = prime * result + Integer.hashCode(this.ydest);
    result = prime * result + Integer.hashCode(this.step);
    result = prime * result + Integer.hashCode(this.seatNumber);
    result = prime * result + Integer.hashCode(this.seatGroup);
    result = prime * result + Boolean.hashCode(this.catering);
    result = prime * result + Integer.hashCode(this.nbx);
    result = prime * result + Integer.hashCode(this.nby);
    result = prime * result + Integer.hashCode(this.dist);
    result = prime * result + Boolean.hashCode(this.booleanSeven);
    return result;
  }
  
  @SyntheticMember
  public Boid(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Boid(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Boid(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
