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
import io.sarl.demos.boids.BoidsSimulation;
import io.sarl.demos.boids.Population;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * The main class configuring the various boids populations and launching the simulation
 * 
 * @author Nicolas Gaud
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class BoidsSimulationLauncher extends JFrame implements ActionListener {
  private JButton bouton1;
  
  private JButton bouton2;
  
  private JButton bouton3;
  
  private static JFrame jFrame;
  
  public static Population pBlue = null;
  
  public static Population pRed = null;
  
  public static Population pGreen = null;
  
  public static AtomicBoolean lock = new AtomicBoolean();
  
  public static AtomicBoolean lock2 = new AtomicBoolean();
  
  public static AtomicBoolean lock3 = new AtomicBoolean();
  
  public static AtomicBoolean lock4 = new AtomicBoolean();
  
  public static AtomicBoolean lock5 = new AtomicBoolean();
  
  public static AtomicBoolean exitLock = new AtomicBoolean();
  
  public static Date initTime;
  
  public static long currentTime;
  
  public static boolean startTime = false;
  
  public static int scenario;
  
  public static int TIME_CONTACT_1 = 167;
  
  public static int TIME_CONTACT_2 = 10000;
  
  public static int TIME_CONTACT_3 = 15000;
  
  public static int PHASE_1 = 60000;
  
  public static int PHASE_2 = 105000;
  
  public static int PHASE_3 = 125000;
  
  public static int PHASE_4 = 170000;
  
  /**
   * public static var PHASE_1 = 30000
   * public static var PHASE_2 = 35000
   * public static var PHASE_3 = 60000
   * public static var PHASE_4 = 75000
   */
  public static int BOIDSIZE = 4;
  
  public static int NB_X_SEAT = 6;
  
  public static int NB_Y_SEAT = 20;
  
  public static int DIST_BETWEEN_SEAT = 10;
  
  public static int NB_BOID = 120;
  
  public JPanel buildContentPane(final BoidsSimulationLauncher config) {
    JPanel panel = new JPanel();
    FlowLayout _flowLayout = new FlowLayout();
    panel.setLayout(_flowLayout);
    JButton _jButton = new JButton("Scénario 1");
    config.bouton1 = _jButton;
    config.bouton1.addActionListener(this);
    Dimension _dimension = new Dimension(100, 100);
    config.bouton1.setPreferredSize(_dimension);
    panel.add(config.bouton1);
    JButton _jButton_1 = new JButton("Scénario 2");
    config.bouton2 = _jButton_1;
    config.bouton2.addActionListener(this);
    Dimension _dimension_1 = new Dimension(100, 100);
    config.bouton2.setPreferredSize(_dimension_1);
    panel.add(config.bouton2);
    JButton _jButton_2 = new JButton("Scénario 3");
    config.bouton3 = _jButton_2;
    config.bouton3.addActionListener(this);
    Dimension _dimension_2 = new Dimension(100, 100);
    config.bouton3.setPreferredSize(_dimension_2);
    panel.add(config.bouton3);
    return panel;
  }
  
  public void actionPerformed(final ActionEvent e) {
    Object source = e.getSource();
    boolean _equals = Objects.equal(source, this.bouton1);
    if (_equals) {
      System.out.println("Lancement scénario 1 ...");
      BoidsSimulationLauncher.scenario = 1;
    } else {
      boolean _equals_1 = Objects.equal(source, this.bouton2);
      if (_equals_1) {
        System.out.println("Lancement scénario 2 ...");
        BoidsSimulationLauncher.scenario = 2;
        BoidsSimulationLauncher.NB_X_SEAT = (2 * BoidsSimulationLauncher.NB_X_SEAT);
      } else {
        System.out.println("Lancement scénario 3 ...");
        BoidsSimulationLauncher.scenario = 3;
        BoidsSimulationLauncher.NB_X_SEAT = (2 * BoidsSimulationLauncher.NB_X_SEAT);
        BoidsSimulationLauncher.DIST_BETWEEN_SEAT = 15;
        Population.DEFAULT_SEPARATION_DIST = 7;
      }
    }
    BoidsSimulationLauncher.start();
    BoidsSimulationLauncher.jFrame.dispose();
  }
  
  /**
   * @param args command line arguments
   */
  public static void main(final String... args) {
    BoidsSimulationLauncher config = new BoidsSimulationLauncher();
    JFrame _jFrame = new JFrame();
    BoidsSimulationLauncher.jFrame = _jFrame;
    JPanel jPanel = config.buildContentPane(config);
    BoidsSimulationLauncher.jFrame.pack();
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int height = tailleEcran.height;
    int width = tailleEcran.width;
    BoidsSimulationLauncher.jFrame.setSize((width / 2), (height / 2));
    BoidsSimulationLauncher.jFrame.setLocationRelativeTo(null);
    BoidsSimulationLauncher.jFrame.getContentPane().add(jPanel);
    BoidsSimulationLauncher.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    BoidsSimulationLauncher.jFrame.setVisible(true);
  }
  
  public static void start() {
    BoidsSimulation simu = new BoidsSimulation();
    Population _population = new Population(Color.RED);
    BoidsSimulationLauncher.pRed = _population;
    Population _population_1 = new Population(Color.GREEN);
    BoidsSimulationLauncher.pGreen = _population_1;
    Population _population_2 = new Population(Color.BLUE);
    BoidsSimulationLauncher.pBlue = _population_2;
    BoidsSimulationLauncher.lock.set(true);
    BoidsSimulationLauncher.lock2.set(true);
    BoidsSimulationLauncher.lock3.set(true);
    BoidsSimulationLauncher.lock4.set(true);
    BoidsSimulationLauncher.lock5.set(true);
    BoidsSimulationLauncher.exitLock.set(true);
    for (int i = 0; (i < BoidsSimulationLauncher.NB_BOID); i++) {
      simu.addBoid(BoidsSimulationLauncher.pGreen);
    }
    simu.start();
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @SyntheticMember
  public BoidsSimulationLauncher() {
    super();
  }
  
  @SyntheticMember
  public BoidsSimulationLauncher(final GraphicsConfiguration gc) {
    super(gc);
  }
  
  @SyntheticMember
  public BoidsSimulationLauncher(final String title) {
    super(title);
  }
  
  @SyntheticMember
  public BoidsSimulationLauncher(final String title, final GraphicsConfiguration gc) {
    super(title, gc);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -19259855954L;
}
