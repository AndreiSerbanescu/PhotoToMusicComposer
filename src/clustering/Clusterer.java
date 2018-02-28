package clustering;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import java.util.Random;

public class Clusterer {

  private BufferedImage image;

  public Clusterer(BufferedImage image) {
    this.image = image;
  }

  public List<Centroid> computeCluster(int points) {

    List<Centroid> centroids = initCentroids(points);
    List<ClusterPoint> clusterPoints = new ArrayList<>();
    iterate(clusterPoints, centroids);


    for (int i = 0; i < 5; i++) {


      for (Centroid centroid : centroids) {
        centroid.recomputeMean();
      }

      List<Centroid> newCentroids = new ArrayList<>();

      for (Centroid centroid : centroids) {
        newCentroids.add(new Centroid(centroid.getRGB()));
      }

      iterate(clusterPoints, newCentroids);
      centroids = newCentroids;
    }

    for (Centroid centroid : centroids) {
      centroid.updateImage();
    }

    utils.ImageUtils.saveImage(image);

    return null;
  }

  private void iterate(List<ClusterPoint> clusterPoints, List<Centroid> centroids) {

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        clusterPoints.add(new ClusterPoint(i, j, image));
      }
    }

    for (ClusterPoint clusterPoint : clusterPoints) {

      int minDist = Integer.MAX_VALUE;
      Centroid closestCentroid = null;

      for (Centroid centroid : centroids) {

        int dist = computeSimilarity(clusterPoint.getRGB(), centroid.getRGB());

        if (dist < minDist) {
          minDist = dist;
          closestCentroid = centroid;
        }
      }

      closestCentroid.addPoint(clusterPoint);

    }
  }

  private List<Centroid> initCentroids(int points) {
    List<Centroid> centroids = new ArrayList<>(points);
    for (int i = 0; i < points; i++) {

      Random rand = new Random();
      int x = rand.nextInt(image.getWidth());
      int y = rand.nextInt(image.getHeight());
      int defaultRGB = image.getRGB(x, y);

      centroids.add(new Centroid(defaultRGB));
    }

    return centroids;
  }

  public static int computeSimilarity(int rgb1, int rgb2) {
    Color col1 = new Color(rgb1);
    Color col2 = new Color(rgb2);

    double blue = Math.pow(col1.getBlue() - col2.getBlue(), 2d);
    double red = Math.pow(col1.getRed() - col2.getRed(), 2d);
    double green = Math.pow(col1.getGreen() - col2.getGreen(), 2d);

    double res = Math.sqrt(blue + red + green);

    return (int) Math.floor(res);
  }

}
