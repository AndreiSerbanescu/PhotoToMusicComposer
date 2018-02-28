package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

  public static BufferedImage loadImage(String path) {
    BufferedImage res = null;
    try {
      res = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return res;
  }

  public static void saveImage(BufferedImage img) {
    try {
      ImageIO.write(img,"png", new File("./output"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
