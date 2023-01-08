package com.example.Narad.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.openMocks;

public class MarketSubscriberTest {

  @InjectMocks
  public MarketSubscriber marketSubscriber;

  @BeforeEach
  public void setup(){
    openMocks(this);
  }

  @Test
  public void receiveUpdatesTest(){
   //
  }
}
