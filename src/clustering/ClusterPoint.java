package clustering;

import java.awt.image.BufferedImage;

public class ClusterPoint {

  private final int x;
  private final int y;
  private final BufferedImage image;

  public ClusterPoint(int x, int y, BufferedImage image) {
    this.x = x;
    this.y = y;
    this.image = image;
  }


  public void setRGB(int newRGB) {
    image.setRGB(x, y, newRGB);
  }

  public int getRGB() {
    return image.getRGB(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
