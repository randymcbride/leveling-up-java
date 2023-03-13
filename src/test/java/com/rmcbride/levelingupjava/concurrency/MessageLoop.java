package com.rmcbride.levelingupjava.concurrency;

import static com.rmcbride.levelingupjava.concurrency.ConcurrencyTest.threadMessage;

public class MessageLoop implements Runnable {
  static String[] messages = {
    "Hello",
    "World",
    "from",
    "a",
    "thread"
  };
  
  @Override
  public void run() {
    try {
      for (var i = 0; i < messages.length; i++) {
        threadMessage(messages[i]);
        Thread.sleep(400);
      }
    } catch (InterruptedException e) {
      threadMessage("Interruption received, no longer looping messages");
    }
    
  }
}
