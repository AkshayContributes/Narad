package com.example.Narad.service;

import com.example.Narad.entity.Stock;
import exception.DuplicateSubscriberException;
import exception.NonExistentSubscriberException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Builder
public class StockPriceUpdaterService {

  Map<String, Stock> stockList ;

  Set<MarketSubscriber> subscribers ;



  public String addSubscriber(MarketSubscriber marketSubscriber) throws DuplicateSubscriberException {
    if(subscribers.contains(marketSubscriber)) throw new DuplicateSubscriberException(String.format("%s already has a subscription", marketSubscriber.getSubscriberName()));
    subscribers.add(marketSubscriber);
    return String.format("Subscriber %s added to the list of subscribers", marketSubscriber.getSubscriberName());
  }

  public String removeSubscriber(MarketSubscriber marketSubscriber) throws NonExistentSubscriberException {
    if(!subscribers.contains(marketSubscriber)) throw new NonExistentSubscriberException(String.format("%s is not a subscriber", marketSubscriber.getSubscriberName()));
    subscribers.remove(marketSubscriber);
    return String.format("Subscriber %s removed from the list of subscribers", marketSubscriber.getSubscriberName());
  }

  public String updateStockPrice(Stock stock) {
    if(stockList.containsKey(stock.getSymbol()) && stockList.get(stock.getSymbol()).getPrice() == stock.getPrice()){
        return String.format("Stock price of %s updated to %s. All subscribers were successfully notified", stock.getSymbol(), stock.getPrice());
    }
    stockList.put(stock.getSymbol(), stock);

    for(MarketSubscriber subscriber: subscribers){
      subscriber.receiveUpdates(String.format("PRICE ALERT!!! the price of %s has changed to %s", stock.getSymbol(), stock.getPrice()));
    }

    return String.format("Stock price of %s updated to %s. All subscribers were successfully notified", stock.getSymbol(), stock.getPrice());
  }
}
