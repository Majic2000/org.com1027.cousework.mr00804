/**
 *
 */
package org.com1027.coursework.q3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mr00804
 *
 */
public class User {

  private String                name;
  private Map<Integer, Integer> purchase;
  private Map<Integer, Double>  successfulBids;

  public User(String name) throws IllegalArgumentException {

    if (name == null) {
      throw new IllegalArgumentException("name is null");

    }
    this.name = name;

    this.purchase = new HashMap<Integer, Integer>();
    this.successfulBids = new HashMap<Integer, Double>();

  }

  public void buy(int productID, int quantity) throws IllegalArgumentException {
    // allocates to correct maps for the purchase map

    this.purchase.put(productID, quantity);

  }

  public String displayAllPurchases() {

    StringBuffer buffer = new StringBuffer("All Purchased Products: \nPurchases: \n");
    if (this.purchase.isEmpty()) {
      buffer.append("Successful Bids: \n");
      return buffer.toString();

    }
    else {
      buffer.append(this.displayPurchases());
      buffer.append("Successful Bids: \n");
      buffer.append(this.displayAllSuccessfulBids());

    }

    return buffer.toString();
  }

  public String displayAllSuccessfulBids() {
    // somehow needs to be able to go through bids? goes through successfullBids

    StringBuffer buffer = new StringBuffer();
    double winningPrice = 0;
    int id = 0;

    for (Map.Entry<Integer, Double> i : this.successfulBids.entrySet()) {

      id = i.getKey();
      winningPrice = i.getValue();
      buffer.append(id + " at a cost of " + winningPrice + "\n");

    }

    return buffer.toString();
  }

  public String displayPurchases() {
    // needs to display the most recent purchases

    StringBuffer buffer = new StringBuffer();
    int id = 0;
    int quantity = 0;

    for (Map.Entry<Integer, Integer> i : this.purchase.entrySet()) {

      this.purchase.put(i.getKey(), i.getValue());

      id = i.getKey();
      quantity = i.getValue();
      buffer.append(id + " with quantity " + quantity + "\n");

    }

    return buffer.toString();
  }

  public String getName() {

    char firstChar = this.name.charAt(0);
    char lastChar = this.name.charAt(this.name.length() - 1);

    StringBuffer buff = new StringBuffer("");
    buff.append(firstChar);

    for (int i = 0; i < 3; i++) {

      buff.append('*');

    }

    buff.append(lastChar);

    return buff.toString();

  }

  @Override
  public String toString() {
    return this.getName();
  }

  public void wonAuction(int productID, double winningPrice) throws IllegalArgumentException {
    // allocates to correct maps for the successfulBids map

    this.successfulBids.put(productID, winningPrice);

  }

}
