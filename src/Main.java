import clustering.Clusterer;
import utils.ImageUtils;

import java.awt.image.*;


public class Main {

  public static void main(String[] args) {

    BufferedImage img = ImageUtils.loadImage("./src/image.jpg");

    Clusterer clusterer = new Clusterer(img);
    clusterer.computeCluster(30);
  }

}
