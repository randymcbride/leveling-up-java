package com.rmcbride.levelingupjava.concurrency;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConcurrencyTest {
  
  @Test
  public void the_problem() {
    // a common requirement for applications is that they must be responsive, i.e., speed matters. Sometimes tasks can 
    // take a long time. For example the following code retrieves all the pages of weather data from an api and then 
    // finds the coldest temperature
    List<ZipCodeTemperature> allTemps = new ArrayList<>();
    
    allTemps.addAll(Concurrency.getTemperatureData(0));
    allTemps.addAll(Concurrency.getTemperatureData(1));
    allTemps.addAll(Concurrency.getTemperatureData(2));
    allTemps.addAll(Concurrency.getTemperatureData(3));
    allTemps.addAll(Concurrency.getTemperatureData(4));
    
    double lowTemp = allTemps.stream().mapToDouble(ZipCodeTemperature::getLowTempCelsius).min().orElseThrow();
    System.out.println("the min temp is " + lowTemp);
  }
}
