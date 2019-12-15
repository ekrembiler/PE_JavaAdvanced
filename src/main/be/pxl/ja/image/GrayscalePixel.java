package image;
import common.DistanceFunction;

import java.awt.*;

import static java.lang.Math.abs;

public class GrayscalePixel implements PixelToInt, DistanceFunction<GrayscalePixel>, Comparable<GrayscalePixel> {
  private int greyscale;

  public GrayscalePixel(int greyscale) {
    this.greyscale = greyscale;
  }

  public int getGreyscale() {
    return greyscale;
  }

  @Override
  public int toRGB() {
    return new Color(greyscale, greyscale, greyscale).getRGB();
  }

  @Override
  public String toString() {
    return Integer.toString(greyscale);
  }

  public double distance(GrayscalePixel distance) {
    return abs(greyscale - distance.greyscale);
  }

  public int compareTo(GrayscalePixel compareGrayscalePixel) {

    int compareGreyscale = ((GrayscalePixel) compareGrayscalePixel).getGreyscale();
    return this.greyscale - compareGreyscale;

  }
}
