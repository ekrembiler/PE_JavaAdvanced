package image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RGBPixel implements PixelToInt {
  private int red;
  private int green;
  private int blue;

  public RGBPixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int toRGB() {
    int rgb = red;
    rgb = (rgb << 8) + green;
    rgb = (rgb << 8) + blue;
    return rgb;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  @Override
  public String toString() {
    return "(" + red + ", " + green + ", " + blue + ")";
  }

  public int ConvertToGrayscale() {
    IntStream stream;
    stream = IntStream.of(red, green, blue);
    return (int) stream.average().getAsDouble();
  }
}
