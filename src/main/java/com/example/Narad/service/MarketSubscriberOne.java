package com.example.Narad.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketSubscriberOne implements MarketSubscriber{
    private String subscriberName;
  @Override public void receiveUpdates(String message) {
    System.out.println(getSubscriberName() +" has received the update: "+message);
  }
}
