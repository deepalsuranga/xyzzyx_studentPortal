/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author deepalsuranga
 */
       

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageSize extends JPanel{
  private static Image image;
  public ImageSize(URL url) {
    image = Toolkit.getDefaultToolkit().getImage(url);
    rightSize();
  }

  private void rightSize() {
    int width = image.getWidth(this);
    int height = image.getHeight(this);
    if (width == -1 || height == -1)
      return;
    addNotify();
    System.out.println("Image width: "+width);
    System.out.println("Image height"+height);
  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y,
      int width, int height) {
    if ((infoflags & ImageObserver.ERROR) != 0) {
      System.out.println("Error loading image!");
      System.exit(-1);
    }
    if ((infoflags & ImageObserver.WIDTH) != 0
        && (infoflags & ImageObserver.HEIGHT) != 0)
      rightSize();
    if ((infoflags & ImageObserver.SOMEBITS) != 0)
      repaint();
    if ((infoflags & ImageObserver.ALLBITS) != 0) {
      rightSize();
      repaint();
      return false;
    }
    return true;
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {
    Insets insets = getInsets();
    g.drawImage(image, insets.left, insets.top, this);
  }
  public static void main(String[] args) throws Exception {
    String url = "http://i.stack.imgur.com/jcySE.jpg";
    
      ImageSize imageSize = new ImageSize(new URL(url));
    
  }
}