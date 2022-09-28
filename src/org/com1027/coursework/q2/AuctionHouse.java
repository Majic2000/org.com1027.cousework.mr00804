/**
 *
 */
package org.com1027.coursework.q2;

//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/**
 * @author mr00804
 *
 */
public class AuctionHouse {

  private Map<Product, User> forSaleProducts;
  private Map<Product, User> soldProducts;
  private Map<Product, User> unsoldProducts;

  public AuctionHouse() {
    this.forSaleProducts = new HashMap<Product, User>();
    this.soldProducts = new HashMap<Product, User>();
    this.unsoldProducts = new HashMap<Product, User>();
  }

  public boolean buyNow(BuyNowProduct product, User buyer, int quantity) throws IllegalArgumentException {
    boolean valid = false;

    if (product == null) {
      throw new IllegalArgumentException();
    }
    if (buyer == null) {
      throw new IllegalArgumentException();
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException();
    }

    if (product.attemptToPurchase(buyer, quantity) == false) {
      valid = false;
      // }

      // if(product.isProductSold() == true) {
      // return false;

    }
    else {

      if (this.forSaleProducts.isEmpty() == true) {

        if (this.register(product, buyer) == false) {
          valid = false;

        }
        else {
          valid = true;
          product.attemptToPurchase(buyer, quantity);

        }

      }
      else {

        if (this.register(product, buyer) == false) {

          valid = true;
          product.attemptToPurchase(buyer, quantity);

        }
        else {
          valid = false;

        }

      }
    }

    return valid;
  }

  public boolean checkExistence(Product product) throws IllegalArgumentException {
    boolean validProduct = false;

    for (Map.Entry<Product, User> currentProduct : this.forSaleProducts.entrySet()) {

      if (currentProduct.getKey().equals(product)) {
        validProduct = true;
      }
    }

    return validProduct;

  }

  public String displaySoldProducts() {

    StringBuffer response = new StringBuffer("");
    String emptyResponse = response.toString();

    int productId = 0;
    String productsToString = null;

    if (this.soldProducts.isEmpty()) {
      return emptyResponse;
    }
    else {

      for (Map.Entry<Product, User> pair : this.soldProducts.entrySet()) {

        productId = pair.getKey().getProductId();
        productsToString = pair.getKey().displayUserInfoForProduct();
        response.append(productId + " - " + productsToString + "\n");

      }

    }

    return response.toString();
  }

  public String displayUnsoldProducts() {

    StringBuffer response = new StringBuffer("");
    int idOfProduct = 0;
    String productName = null;
    String empty = "";

    if (this.unsoldProducts.isEmpty()) {
      return response.toString();
    }
    for (Map.Entry<Product, User> pair : this.unsoldProducts.entrySet()) {

      idOfProduct = pair.getKey().getProductId();
      productName = pair.getKey().getProductName();

      empty = (idOfProduct + " - " + productName + "\n");
      response.append(empty);

    }

    return response.toString();
  }

  public void endAuction(Product product) {

    Product unavailable = product;
    User winner;
    // double reservedPs = 0;

    winner = this.forSaleProducts.get(product);

    if (product.getCurrentPrice() == 0) {
      this.unsoldProducts.put(unavailable, null);

    }
    else {
      this.forSaleProducts.remove(product);
      this.soldProducts.put(unavailable, winner);

    }

  }

  public boolean placeBid(BiddableProduct product, User user, double bidValue) throws IllegalArgumentException {

    if (product == null) {
      throw new IllegalArgumentException();
    }

    if (bidValue <= 0) {
      throw new IllegalArgumentException();
    }

    if (user == null) {
      throw new IllegalArgumentException();
    }

    boolean valid = true;
    if (this.soldProducts.isEmpty() == true) {
      if (this.register(product, user) == true) {
        valid = false;
      }
      else {

        if (bidValue <= product.getCurrentPrice()) {
          valid = false;

        }
        else {
          product.attemptToPurchase(user, bidValue);
          valid = true;
        }

      }
    }
    else {

      if (bidValue <= product.getCurrentPrice()) {
        valid = false;

      }
      else {
        product.attemptToPurchase(user, bidValue);
        valid = true;
      }

    }

    return valid;

  }

  public boolean register(Product product, User user) throws IllegalArgumentException {
    // need to put the product and user into the forSaleProducts in order to create an item in the given array and establish a
    // connection

    boolean registered = true;

    if (product == null) {
      throw new IllegalArgumentException();

    }
    else if (user == null) {
      throw new IllegalArgumentException();

    }
    else {

      if (this.forSaleProducts.containsKey(product)) {
        User buyer = this.forSaleProducts.get(product);

        if (buyer.equals(user)) {
          registered = false;

        }
        else {
          this.forSaleProducts.put(product, user);
          this.unsoldProducts.put(product, user);

        }

      }

      this.forSaleProducts.put(product, user);
      this.unsoldProducts.put(product, user);

    }

    return registered;
  }

}
