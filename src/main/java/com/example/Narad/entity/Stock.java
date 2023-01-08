package com.example.Narad.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Stock {

  private String symbol;
  private double price;

}
