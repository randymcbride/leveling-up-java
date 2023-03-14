package com.rmcbride.levelingupjava.concurrency;

public class SynchronizedCounter {
  
  private int value;
  
  public synchronized void increment() {
    value++;
  }
  
  public synchronized void decrement() {
    value--;
  }
  
  public synchronized int getValue() {
    return value;
  }
}
