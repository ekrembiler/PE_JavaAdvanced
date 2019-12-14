package city;

import common.DistanceUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class DistancesBetweenCities {

  public static void main(String[] args) {
    City leuven = new City("Leuven", 50.88151970000001, 4.6967578);
    City roermond = new City("Roermond", 51.19417, 5.9875);
    City maastricht = new City("Maastricht", 50.84833, 5.68889);
    City aken = new City("Aken", 50.77664, 6.08342);
    List<City> cities = new ArrayList<City>();
    cities.add(leuven);
    cities.add(roermond);
    cities.add(maastricht);
    cities.add(aken);
    City heusden = new City("Heusden-Zolder", 51.03848, 5.29041);
    cities.sort((City city1, City city2) -> city1.toString().compareTo(city2.toString()));
    cities.forEach(System.out::println);
    Set<City> set = new HashSet<City>(cities);
    City closest = DistanceUtil.findClosest(set, heusden);
    System.out.println("Closest: " + closest.toString());


  }
}
