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

import io.sarl.demos.boids.Contact;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Event from a boid to the environment containing his corresponding influence for the current simulation step.
 * @author Nicolas Gaud
 */
@SarlSpecification("0.11")
@SarlElementType(15)
@SuppressWarnings("all")
public class Action extends Event {
  public final Vector2d influence;
  
  public final int destx;
  
  public final int desty;
  
  public final int stp;
  
  public final ConcurrentHashMap<UUID, Contact> map;
  
  public final int group;
  
  public final boolean booleanSeven;
  
  public Action(final Vector2d mi, final int mx, final int my, final int ms, final ConcurrentHashMap<UUID, Contact> mm, final int sg, final boolean bs) {
    this.influence = mi;
    this.destx = mx;
    this.desty = my;
    this.stp = ms;
    this.group = sg;
    ConcurrentHashMap<UUID, Contact> _concurrentHashMap = new ConcurrentHashMap<UUID, Contact>(mm);
    this.map = _concurrentHashMap;
    this.booleanSeven = bs;
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
    Action other = (Action) obj;
    if (other.destx != this.destx)
      return false;
    if (other.desty != this.desty)
      return false;
    if (other.stp != this.stp)
      return false;
    if (other.group != this.group)
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
    result = prime * result + Integer.hashCode(this.destx);
    result = prime * result + Integer.hashCode(this.desty);
    result = prime * result + Integer.hashCode(this.stp);
    result = prime * result + Integer.hashCode(this.group);
    result = prime * result + Boolean.hashCode(this.booleanSeven);
    return result;
  }
  
  /**
   * Returns a String representation of the Action event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("influence", this.influence);
    builder.add("destx", this.destx);
    builder.add("desty", this.desty);
    builder.add("stp", this.stp);
    builder.add("map", this.map);
    builder.add("group", this.group);
    builder.add("booleanSeven", this.booleanSeven);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 1228680880L;
}
