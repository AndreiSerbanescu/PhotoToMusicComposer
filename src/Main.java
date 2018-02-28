import java.awt.*;
import java.awt.image.*;


public class Main {

  public static void main(String[] args) {

    BufferedImage img = ImageUtils.loadImage("./src/image.jpg");

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        img.setRGB(j, i, img.getRGB(j, i) * 2);
      }
    }

    ImageUtils.saveImage(img);
  }

}
