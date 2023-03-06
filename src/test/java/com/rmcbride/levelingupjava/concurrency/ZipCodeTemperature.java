package com.rmcbride.levelingupjava.concurrency;

import lombok.Data;

@Data
public class ZipCodeTemperature {
  private final int zipCode;
  private final double highTempCelsius;
  private final double lowTempCelsius;
}
