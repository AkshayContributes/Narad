package com.example.Narad.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


public interface MarketSubscriber {
  public String getSubscriberName();

  public void receiveUpdates(String message);

}
