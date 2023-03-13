package com.rmcbride.levelingupjava.concurrency;

public class Counter {
  private int value;
  
  public void increment() {
    value++;
  }
  
  public void decrement() {
    value--;
  }
  
  public int getValue() {
    return value;
  }
}
