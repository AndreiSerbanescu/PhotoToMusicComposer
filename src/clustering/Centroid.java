package clustering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Centroid {


  //TODO cap the number of points

  private List<ClusterPoint> points;
  private int value;

  public Centroid(int defaultValue) {
    points = new ArrayList<>();
    value = defaultValue;
  }

  public void addPoint(ClusterPoint cp) {
    points.add(cp);
  }

  public void resetPoints() {
    points = new ArrayList<>();
  }

  public int getRGB() {
    return value;
  }

  public void recomputeMean() {
    if (points.isEmpty()) {
      value = 0;
      return;
    }

    int blue = 0;
    int red = 0;
    int green = 0;

    for (ClusterPoint point : points) {
      Color col = new Color(point.getRGB());

      blue += col.getBlue();
      red += col.getRed();
      green += col.getGreen();
    }

    blue /= points.size();
    red /= points.size();
    green /= points.size();

    value = (new Color(red, green, blue)).getRGB();
  }

  private int rgbMean(int rgb1, int rgb2) {
    Color col1 = new Color(rgb1);
    Color col2 = new Color(rgb2);

    int red = (col1.getRed() + col2.getRed()) / 2;
    int green = (col1.getGreen() + col2.getGreen()) / 2;
    int blue = (col1.getBlue() + col2.getBlue()) / 2;


    return (new Color(red, green, blue)).getRGB();
  }

  public void updateImage() {

    recomputeMean();

    for (ClusterPoint point : points) {

      point.setRGB(getRGB());
    }
  }
}
