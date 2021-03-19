package io.sarl.demos.boids;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author benjamin
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Exemple extends JFrame implements ActionListener {
  private JButton bouton1;
  
  private JButton bouton2;
  
  private JButton bouton3;
  
  public static void main(final String... args) {
    Exemple e = new Exemple();
    JFrame jFrame = new JFrame();
    JPanel jPanel = e.buildContentPane();
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int height = tailleEcran.height;
    int width = tailleEcran.width;
    jFrame.setSize((width / 2), (height / 2));
    jFrame.setLocationRelativeTo(null);
    jFrame.getContentPane().add(jPanel);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setVisible(true);
  }
  
  public JPanel buildContentPane() {
    JPanel panel = new JPanel();
    FlowLayout _flowLayout = new FlowLayout();
    panel.setLayout(_flowLayout);
    JButton _jButton = new JButton("Scénario 1");
    this.bouton1 = _jButton;
    this.bouton1.addActionListener(this);
    panel.add(this.bouton1);
    JButton _jButton_1 = new JButton("Scénario 2");
    this.bouton2 = _jButton_1;
    this.bouton2.addActionListener(this);
    panel.add(this.bouton2);
    JButton _jButton_2 = new JButton("Scénario 3");
    this.bouton3 = _jButton_2;
    this.bouton3.addActionListener(this);
    panel.add(this.bouton3);
    return panel;
  }
  
  public void actionPerformed(final ActionEvent e) {
    Object source = e.getSource();
    boolean _equals = Objects.equal(source, this.bouton1);
    if (_equals) {
      System.out.println("Lancement scénario 1 ...");
    } else {
      boolean _equals_1 = Objects.equal(source, this.bouton2);
      if (_equals_1) {
        System.out.println("Lancement scénario 2 ...");
      } else {
        System.out.println("Lancement scénario 3 ...");
      }
    }
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
  public Exemple() {
    super();
  }
  
  @SyntheticMember
  public Exemple(final GraphicsConfiguration gc) {
    super(gc);
  }
  
  @SyntheticMember
  public Exemple(final String title) {
    super(title);
  }
  
  @SyntheticMember
  public Exemple(final String title, final GraphicsConfiguration gc) {
    super(title, gc);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -3823056927L;
}
