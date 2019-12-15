package image;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import static image.ImageReader.readImage;

public class ImageArt {

  public static void main(String[] args) throws IOException {

    RGBPixel prussianBlue = new RGBPixel(0, 48, 80);
    RGBPixel desaturatedCyan = new RGBPixel(112, 150, 160);
    RGBPixel peachYellow = new RGBPixel(250, 227, 173);
    RGBPixel lava = new RGBPixel(218, 20, 21);
    List<RGBPixel> faireyColors = Arrays.asList(prussianBlue, lava, desaturatedCyan, peachYellow);
    Path currentRelativePath = Paths.get("");
    Path readImage = currentRelativePath.resolve("src/main/resources/tokio.jpg");
    Path writeImage = currentRelativePath.resolve("src/main/resources/grayscale.jpg");
    Path writeFairyImage = currentRelativePath.resolve("src/main/resources/fairy.jpg");
    List<List<RGBPixel>> imagePixels = readImage(readImage);


    List<List<GrayscalePixel>> grayscalePixelsLists = new ArrayList<>();
    for (List<RGBPixel> e : imagePixels) {
      List<GrayscalePixel> grayscalePixels = new ArrayList<>();
      for (RGBPixel f : e) {
        GrayscalePixel pixel = new GrayscalePixel(f.ConvertToGrayscale());
        grayscalePixels.add(pixel);
      }
      grayscalePixelsLists.add(grayscalePixels);
    }
    ImageWriter.writeImage(writeImage, grayscalePixelsLists);


    TreeSet<GrayscalePixel> grayscaleTreeSet = new TreeSet<GrayscalePixel>();
    grayscalePixelsLists.stream().forEach(e -> e.stream().forEach(f -> grayscaleTreeSet.add(f)));
    Map<GrayscalePixel, RGBPixel> map = createTranslationMap(faireyColors, grayscaleTreeSet);
    List<List<RGBPixel>> fairyPixels = new ArrayList<>();
    for (List<GrayscalePixel> e: grayscalePixelsLists) {
      List<RGBPixel> rgbPixels = new ArrayList<>();
      GrayscalePixel closest = map.keySet().iterator().next();
      for (GrayscalePixel f: e) {

        for (Map.Entry<GrayscalePixel, RGBPixel> entry : map.entrySet()){
          if (f.compareTo(entry.getKey()) < f.compareTo(closest)){
            closest = entry.getKey();
          }
        }
        RGBPixel pixel = new RGBPixel(map.get(closest).getRed(),map.get(closest).getGreen(),map.get(closest).getBlue());
        rgbPixels.add(pixel);
      }
      fairyPixels.add(rgbPixels);
    }
    ImageWriter.writeImage(writeFairyImage, fairyPixels);



  }

  private static Map<GrayscalePixel, RGBPixel> createTranslationMap(List<RGBPixel> faireyColors, TreeSet<GrayscalePixel> allGreyscalePixels) {
    int size = allGreyscalePixels.size() / faireyColors.size();

    Map<GrayscalePixel, RGBPixel> translationMap = new HashMap<>();
    Iterator<GrayscalePixel> iterator = allGreyscalePixels.iterator();
    int startIndex = size / 2;
    List<Integer> preferedIndeces = new ArrayList<>();
    for (int group = 0; group < faireyColors.size(); group++) {
      preferedIndeces.add(startIndex);
      startIndex += size;
    }
    int index = 0;
    while (iterator.hasNext()) {
      GrayscalePixel grayscalePixel = iterator.next();
      if (preferedIndeces.contains(index)) {
        int position = preferedIndeces.indexOf(index);
        translationMap.put(grayscalePixel, faireyColors.get(position));
      }
      index++;
    }
    return translationMap;
  }
}
