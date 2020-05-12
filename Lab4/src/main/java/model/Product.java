package model;

import java.util.Random;

public class Product {

  private int id;
  private int prodId;
  private String title;
  private int cost;

  public Product(int id, int prodId, String title, int cost) {
    this.id = id;
    this.prodId = prodId;
    this.title = title;
    this.cost = cost;
  }

  public Product(String title, int cost) {
    Random random =  new Random();
    this.prodId = random.nextInt(100);
    this.title = title;
    this.cost = cost;
  }

  @Override
  public String toString() {
    return String.format("%s id:%d -> prodId: %d, title: %s, cost: %d",
        getClass().getSimpleName(), getId(),getProdId(),getTitle(), getCost());
  }

  public int getId() {
    return id;
  }

  public int getProdId() {
    return prodId;
  }

  public String getTitle() {
    return title;
  }

  public int getCost() {
    return cost;
  }
}
