package io.sarl.demos.boids;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author benjamin
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Contact {
  private long time;
  
  private long lastStart;
  
  public Contact(final long t, final long lt) {
    this.time = t;
    this.lastStart = lt;
  }
  
  public void setTime(final long t) {
    this.time = t;
  }
  
  public void setLastStart(final long t) {
    this.lastStart = t;
  }
  
  @Pure
  public long getTime() {
    return this.time;
  }
  
  @Pure
  public long getLastStart() {
    return this.lastStart;
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
    Contact other = (Contact) obj;
    if (other.time != this.time)
      return false;
    if (other.lastStart != this.lastStart)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Long.hashCode(this.time);
    result = prime * result + Long.hashCode(this.lastStart);
    return result;
  }
}
