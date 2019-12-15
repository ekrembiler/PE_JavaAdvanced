package image;

import java.io.IOException;
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
      List<List<RGBPixel>> imagePixels = readImage(readImage);
      List<List<GrayscalePixel>> grayscalePixelsLists = new ArrayList<>();
      for (List<RGBPixel> e : imagePixels) {
        List<GrayscalePixel> grayscalePixels = new ArrayList<>();
        for (RGBPixel f: e) {
          GrayscalePixel pixel = new GrayscalePixel(f.ConvertToGrayscale());
          grayscalePixels.add(pixel);
        }
        grayscalePixelsLists.add(grayscalePixels);
      }
      ImageWriter.writeImage(writeImage,grayscalePixelsLists);












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
