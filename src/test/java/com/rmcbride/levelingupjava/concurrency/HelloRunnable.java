package com.rmcbride.levelingupjava.concurrency;

// In order to define the code that will run in a thread, we have to extend the Runnable interface
public class HelloRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println("Hello from a thread!");
  }
}
