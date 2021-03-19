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
public class Data {
  private int contact1;
  
  private int contact2;
  
  private int contact3;
  
  public Data(final int v1, final int v2, final int v3) {
    this.contact1 = v1;
    this.contact2 = v2;
    this.contact3 = v3;
  }
  
  @Pure
  public int getContact1() {
    return this.contact1;
  }
  
  @Pure
  public int getContact2() {
    return this.contact2;
  }
  
  @Pure
  public int getContact3() {
    return this.contact3;
  }
  
  public void setContact1(final int v) {
    this.contact1 = v;
  }
  
  public void setContact2(final int v) {
    this.contact2 = v;
  }
  
  public void setContact3(final int v) {
    this.contact3 = v;
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
    Data other = (Data) obj;
    if (other.contact1 != this.contact1)
      return false;
    if (other.contact2 != this.contact2)
      return false;
    if (other.contact3 != this.contact3)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.contact1);
    result = prime * result + Integer.hashCode(this.contact2);
    result = prime * result + Integer.hashCode(this.contact3);
    return result;
  }
}
