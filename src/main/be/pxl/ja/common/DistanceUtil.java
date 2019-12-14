package common;
import city.City;

import java.util.Set;

public class DistanceUtil{
    static public City findClosest(Set<City> elements, City otherElement){
      City closestCity = elements.iterator().next();
      for (City city :elements) {
          if (city.distance(otherElement) < closestCity.distance(otherElement)){
            closestCity = city;
          }
      }
      return  closestCity;
    }
}
