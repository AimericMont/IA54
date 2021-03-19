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

import io.sarl.demos.boids.Population;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * The representation of a boid in the environment : his physical body
 * 
 * @author Nicolas Gaud
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class PerceivedBoidBody {
  @Accessors
  private Vector2d position;
  
  @Accessors
  private UUID owner;
  
  @Accessors
  private Vector2d vitesse;
  
  @Accessors
  private Vector2d acceleration;
  
  @Accessors
  private Population group;
  
  @Accessors
  private int step;
  
  @Accessors
  private int xdest;
  
  @Accessors
  private int ydest;
  
  @Accessors
  private int seatGroup;
  
  @Accessors
  private int guichet;
  
  @Accessors
  private boolean catering;
  
  public PerceivedBoidBody(final Population igroup, final UUID iowner, final Vector2d iposition, final Vector2d ispeed, final Boolean icatering, final Integer iguichet) {
    this.position = iposition;
    this.owner = iowner;
    this.vitesse = ispeed;
    Vector2d _vector2d = new Vector2d();
    this.acceleration = _vector2d;
    this.group = igroup;
    this.step = 0;
    this.xdest = 0;
    this.ydest = 0;
    this.catering = ((icatering) == null ? false : (icatering).booleanValue());
    this.guichet = ((iguichet) == null ? 0 : (iguichet).intValue());
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
    PerceivedBoidBody other = (PerceivedBoidBody) obj;
    if (!Objects.equals(this.owner, other.owner))
      return false;
    if (other.step != this.step)
      return false;
    if (other.xdest != this.xdest)
      return false;
    if (other.ydest != this.ydest)
      return false;
    if (other.seatGroup != this.seatGroup)
      return false;
    if (other.guichet != this.guichet)
      return false;
    if (other.catering != this.catering)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.owner);
    result = prime * result + Integer.hashCode(this.step);
    result = prime * result + Integer.hashCode(this.xdest);
    result = prime * result + Integer.hashCode(this.ydest);
    result = prime * result + Integer.hashCode(this.seatGroup);
    result = prime * result + Integer.hashCode(this.guichet);
    result = prime * result + Boolean.hashCode(this.catering);
    return result;
  }
  
  @Pure
  public Vector2d getPosition() {
    return this.position;
  }
  
  public void setPosition(final Vector2d position) {
    this.position = position;
  }
  
  @Pure
  public UUID getOwner() {
    return this.owner;
  }
  
  public void setOwner(final UUID owner) {
    this.owner = owner;
  }
  
  @Pure
  public Vector2d getVitesse() {
    return this.vitesse;
  }
  
  public void setVitesse(final Vector2d vitesse) {
    this.vitesse = vitesse;
  }
  
  @Pure
  public Vector2d getAcceleration() {
    return this.acceleration;
  }
  
  public void setAcceleration(final Vector2d acceleration) {
    this.acceleration = acceleration;
  }
  
  @Pure
  public Population getGroup() {
    return this.group;
  }
  
  public void setGroup(final Population group) {
    this.group = group;
  }
  
  @Pure
  public int getStep() {
    return this.step;
  }
  
  public void setStep(final int step) {
    this.step = step;
  }
  
  @Pure
  public int getXdest() {
    return this.xdest;
  }
  
  public void setXdest(final int xdest) {
    this.xdest = xdest;
  }
  
  @Pure
  public int getYdest() {
    return this.ydest;
  }
  
  public void setYdest(final int ydest) {
    this.ydest = ydest;
  }
  
  @Pure
  public int getSeatGroup() {
    return this.seatGroup;
  }
  
  public void setSeatGroup(final int seatGroup) {
    this.seatGroup = seatGroup;
  }
  
  @Pure
  public int getGuichet() {
    return this.guichet;
  }
  
  public void setGuichet(final int guichet) {
    this.guichet = guichet;
  }
  
  @Pure
  public boolean isCatering() {
    return this.catering;
  }
  
  public void setCatering(final boolean catering) {
    this.catering = catering;
  }
}
