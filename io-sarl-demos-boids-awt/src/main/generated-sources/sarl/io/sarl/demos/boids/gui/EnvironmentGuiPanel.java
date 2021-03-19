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
package io.sarl.demos.boids.gui;

import io.sarl.demos.boids.BoidsSimulationLauncher;
import io.sarl.demos.boids.PerceivedBoidBody;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * The GUI of the Simulation
 * 
 * @author Nicolas GAUD
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
class EnvironmentGuiPanel extends Panel {
  /**
   * Double buffering management.
   */
  private Graphics myGraphics;
  
  /**
   * Double buffering management.
   */
  private Graphics myCanvas;
  
  /**
   * Double buffering management.
   */
  private Image myImage;
  
  private int width;
  
  private int height;
  
  private Map<UUID, PerceivedBoidBody> boids;
  
  public void setBoids(final Map<UUID, PerceivedBoidBody> boids) {
    this.boids = boids;
  }
  
  public EnvironmentGuiPanel(final int iheight, final int iwidth, final Map<UUID, PerceivedBoidBody> iboids) {
    super();
    this.width = iwidth;
    this.height = iheight;
    this.boids = iboids;
  }
  
  @Override
  public void paint(final Graphics g) {
    try {
      if (((this.myCanvas != null) && (this.myGraphics != null))) {
        final Color bgColor = new Color(0.2F, 0.2F, 0.2F);
        this.myCanvas.setColor(bgColor);
        this.myCanvas.fillRect(0, 0, ((this.width * 2) - 1), ((this.height * 2) - 1));
        this.myCanvas.setColor(Color.BLACK);
        this.myCanvas.drawRect(0, 0, ((this.width * 2) - 1), ((this.height * 2) - 1));
        final BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource("backgroundimage.png"));
        this.myCanvas.drawImage(image, ((this.width - 400) + 2), ((this.height - 300) + 2), null);
        Collection<PerceivedBoidBody> _values = this.boids.values();
        for (final PerceivedBoidBody boid : _values) {
          this.paintBoid(this.myCanvas, boid);
        }
        this.paintSeat(this.myCanvas);
        this.myCanvas.setColor(Color.BLACK);
        this.myCanvas.drawRect(((this.width - 400) + 1), ((this.height - 300) + 1), (800 + 2), (600 + 2));
        this.myCanvas.setColor(Color.ORANGE);
        this.myCanvas.fillOval((this.width + 250), (this.height - 180), 4, 4);
        this.myCanvas.fillOval((this.width + 250), (this.height - 60), 4, 4);
        this.myCanvas.fillOval((this.width + 250), (this.height + 60), 4, 4);
        this.myCanvas.fillOval((this.width + 250), (this.height + 180), 4, 4);
        this.myCanvas.setColor(Color.PINK);
        this.myCanvas.fillOval(this.width, this.height, 4, 4);
        this.myCanvas.fillOval(this.width, (this.height + 138), 4, 4);
        this.myGraphics.drawImage(this.myImage, 0, 0, this);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void update(final Graphics g) {
    this.paint(g);
  }
  
  @Override
  public void doLayout() {
    super.doLayout();
    this.width = (this.getSize().width / 2);
    this.height = (this.getSize().height / 2);
    this.myImage = this.createImage((this.width * 2), (this.height * 2));
    this.myCanvas = this.myImage.getGraphics();
    this.myGraphics = this.getGraphics();
  }
  
  public void paintBoid(final Graphics g, final PerceivedBoidBody boid) {
    double _x = boid.getPosition().getX();
    int posX = (this.width + ((int) _x));
    double _y = boid.getPosition().getY();
    int posY = (this.height + ((int) _y));
    g.setColor(boid.getGroup().color);
    g.fillOval(posX, posY, BoidsSimulationLauncher.BOIDSIZE, BoidsSimulationLauncher.BOIDSIZE);
    if (BoidsSimulationLauncher.startTime) {
      long _time = new Date().getTime();
      long _time_1 = BoidsSimulationLauncher.initTime.getTime();
      BoidsSimulationLauncher.currentTime = (_time - _time_1);
    }
    g.setFont(g.getFont().deriveFont(18.0f));
    g.setColor(Color.WHITE);
    g.drawString(Long.valueOf((BoidsSimulationLauncher.currentTime / 1000)).toString(), this.width, (this.height - 325));
  }
  
  public void paintSeat(final Graphics g) {
    g.setColor(Color.RED);
    for (int i = 0; (i < BoidsSimulationLauncher.NB_Y_SEAT); i++) {
      for (int j = 0; (j < BoidsSimulationLauncher.NB_X_SEAT); j++) {
        g.fillOval(((this.width - 250) + (j * BoidsSimulationLauncher.DIST_BETWEEN_SEAT)), 
          (((((this.height * 2) - ((BoidsSimulationLauncher.DIST_BETWEEN_SEAT * BoidsSimulationLauncher.NB_Y_SEAT) - 1)) / 2) + 
            (i * BoidsSimulationLauncher.DIST_BETWEEN_SEAT)) + (BoidsSimulationLauncher.DIST_BETWEEN_SEAT / 2)), 
          2, 2);
      }
    }
  }
  
  @Pure
  private static double getAngle(final Vector2d v) {
    double zero = 1E-9;
    double _x = v.getX();
    double _x_1 = v.getX();
    if (((_x * _x_1) < zero)) {
      double _y = v.getY();
      if ((_y >= 0)) {
        return (Math.PI / 2);
      }
      return (((-1) * Math.PI) / 2);
    }
    double _x_2 = v.getX();
    if ((_x_2 >= 0)) {
      double _y_1 = v.getY();
      double _x_3 = v.getX();
      return Math.atan((_y_1 / _x_3));
    }
    double _y_2 = v.getY();
    if ((_y_2 >= 0)) {
      double _y_3 = v.getY();
      double _x_4 = v.getX();
      double _atan = Math.atan((_y_3 / _x_4));
      return (Math.PI + _atan);
    }
    double _y_4 = v.getY();
    double _x_5 = v.getX();
    double _atan_1 = Math.atan((_y_4 / _x_5));
    return (_atan_1 - Math.PI);
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
    EnvironmentGuiPanel other = (EnvironmentGuiPanel) obj;
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
  private static final long serialVersionUID = 1383486174L;
}
