package com.rmcbride.levelingupjava.concurrency;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class ConcurrencyTest {
  
  // utility function
  public static void threadMessage(String message) {
    System.out.println(String.format("%s: %s", Thread.currentThread().getName(), message));
  }
  
  @Test
  public void defining_and_starting_a_thread() {
    // create a new thread and execute a code block that extends runnable
    new Thread(new HelloRunnable()).start();
    
    // we can also use Java 8 lambdas to create a Runnable
    new Thread(() -> System.out.println("Hello from lambda")).start();
  }
  
  @Test
  public void sleep() throws InterruptedException {
    // https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html
    String[] messages = {
      "Hello",
      "I'm",
      "feeling",
      "sleepy"
    };
    
    //print the messages on one second intervals by telling the main thread to sleep
    for (String message : messages) {
      Thread.sleep(1000);
      System.out.println(message);
    }
  }
  
  @SneakyThrows
  @Test
  public void interruption() {
    // https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html
    
    // Threads should support being interrupted. There are two ways to do this. If the thread is frequently calling
    // a method that throws an interrupted exception (like sleep) then it can just return.
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        return;
      }
    }
    
    // But if the thread is not calling a method that throws interrupted exception, it should check frequently to see
    // if it has been interrupted.
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      if (Thread.interrupted()) {
        return;
      }
    }
    
    //In more complex situations it may make more sense to surface the interrupted exception and let something else 
    // decide what to do with it
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      if (Thread.interrupted()) {
        throw new InterruptedException();
      }
    }
  }
  
  @Test
  public void join() throws InterruptedException {
    // calling the join method on a thread object causes the current thread to wait for the completion of the other 
    // thread.
    Thread sleeper400 = new Thread(() -> {
      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        return;
      }
    });
    
    long start = System.currentTimeMillis();
    sleeper400.start();
    
    // this line runs immediately
    System.out.println(String.format("duration: %d", System.currentTimeMillis() - start));
    
    // wait for the thread to finish
    sleeper400.join();
    
    // this line runs after the thread finishes
    System.out.println(String.format("duration: %d", System.currentTimeMillis() - start));
  }
  
  @Test
  public void simple_example() throws InterruptedException {
    // https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html
    
    // this example applies every thing we have learned so far. The main thread starts a new thread by calling the
    // runnable process MessageLoop, and then waits for it to finish. The main thread then interrupts the MessageLoop, 
    // so it can continue.
    Thread t = new Thread(new MessageLoop());
    
    threadMessage("starting MessageLoop in a separate thread and waiting for up to 1 second for it to complete");
    t.start();
    t.join(1000);
    
    threadMessage("Sending interrupt");
    t.interrupt();
  }
}