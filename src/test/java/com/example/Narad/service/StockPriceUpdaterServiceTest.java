package com.example.Narad.service;

import com.example.Narad.entity.Stock;
import exception.DuplicateSubscriberException;
import exception.NonExistentSubscriberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;


public class StockPriceUpdaterServiceTest {

  @InjectMocks
  StockPriceUpdaterService stockPriceUpdaterService = StockPriceUpdaterService.builder().subscribers(new HashSet<>()).stockList(new HashMap<>()).build();

  private static final String SUBSCRIBER_NAME = "test_subscriber";
  private static final String STOCK_NAME = "test_stock";

  private static final Double STOCK_PRICE = 3.0d;

  @BeforeEach
  public void setup(){
    openMocks(this);
  }

  @Test
  public void testAddSubscriber() throws DuplicateSubscriberException {
      MarketSubscriberOne marketSubscriber = MarketSubscriberOne.builder().subscriberName(SUBSCRIBER_NAME).build();
      String response = stockPriceUpdaterService.addSubscriber(marketSubscriber);
      Assertions.assertEquals(String.format("Subscriber %s added to the list of subscribers", SUBSCRIBER_NAME), response);
  }

  @Test
  public void testAddSubscriberDuplicate() throws DuplicateSubscriberException {
    MarketSubscriberOne marketSubscriber = MarketSubscriberOne.builder().subscriberName(SUBSCRIBER_NAME).build();
    stockPriceUpdaterService.addSubscriber(marketSubscriber);
    assertThrows(DuplicateSubscriberException.class, ()-> stockPriceUpdaterService.addSubscriber(marketSubscriber));
  }



  @Test
  public void testRemoveSubscriber() throws NonExistentSubscriberException {
    MarketSubscriberOne marketSubscriber = MarketSubscriberOne.builder().subscriberName(SUBSCRIBER_NAME).build();
    String response = stockPriceUpdaterService.removeSubscriber(marketSubscriber);
    Assertions.assertEquals(String.format("Subscriber %s removed from the list of subscribers", SUBSCRIBER_NAME), response);
  }

  @Test
  public void testRemoveSubscriberNonExistent(){
    MarketSubscriberOne marketSubscriber = MarketSubscriberOne.builder().subscriberName(SUBSCRIBER_NAME).build();
    assertThrows(NonExistentSubscriberException.class, ()->stockPriceUpdaterService.removeSubscriber(marketSubscriber));
  }

  @Test
  public void testUpdateStockPrice(){
    Stock stock = Stock.builder().symbol(STOCK_NAME).price(STOCK_PRICE).build();
    String response = stockPriceUpdaterService.updateStockPrice(stock);
    Assertions.assertEquals(String.format("Stock price of %s updated to %s. All subscribers were successfully notified", STOCK_NAME, STOCK_PRICE), response);
  }

}
