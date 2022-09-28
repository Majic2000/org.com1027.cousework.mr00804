/**
 * 
 */
package org.com1027.coursework.q1;

import java.util.ArrayList;

/**
 * @author mr00804
 *
 */
public class Product {

  private int productId;
  private String prodctName;
  private double reservedPrice;
  private ArrayList<Bid> bids;
  
  public Product(int productId, String productName, double reservedPrice) throws IllegalArgumentException {
    if(productId <= 0) {
      throw new IllegalArgumentException();
    }
    this.productId = productId;
    
    if(productName == null) {
      throw new IllegalArgumentException();
    }
    this.prodctName = productName;
    
    if(reservedPrice <= 0) {
      throw new IllegalArgumentException();
    }
    this.reservedPrice = reservedPrice;
    this.bids = new ArrayList<Bid>();
  }
  
  public Bid getHighestBid() {
    //goes through array list and compares all bids and finds the highest one
    //s1 = (buyer = "garry":User, bidValue = 10:double )
    //s2 = (buyer = "Maciej":User, bidValue = 12:double )
    //i goes through s1 and s2 and uses "getBidValue" to retrive both values/go through both values
    //1st compared with all others using Math.max() and returns the object outcome as its of type Bid
    //or could compare with if statement
    
    //double greater = this.bids.get(0).getBidValue();
    double lower = 0;
    Bid wantedObject = null;
    
    if(bids.size() == 0) {
      System.out.println("no bid returned");
    } else {
      double greater = this.bids.get(0).getBidValue();
      
      for(Bid i : bids) {
        
        //lower = Math.max(lower, i.getBidValue());
        lower = i.getBidValue();
        
        if(lower >= greater) {
          greater = lower;
          // i needs to be returned into a var so that it can be returned as an object
          wantedObject = i;
        } 
      }
    } 
    return wantedObject;
    
  }
  
 
  
  public int getProductId() {
    //returns the product id after validation
    return this.productId;
    
  }
  
  public String getProductName() {
    //returns the product name with validation
    return this.prodctName;
  }
  
  public double getReservedPrice() {
    //returns that price that the person wnats to acution for the given item id in the constructor
    return this.reservedPrice;
  }
  
  public boolean placeBid(User user, double bidValue) throws IllegalArgumentException {
    //allows user to place bid into bids ArrayList
    //uses get highest bid function and possibly returns true or false depending if its the highest bid
    //after array the bid is placed for a specific given product
    boolean valid = false;
    
    double currentHighest = 0;
    
    if(bidValue == 0) {
      throw new IllegalArgumentException();
    }
    else if(user == null) {
      throw new IllegalArgumentException();
    } else {
      if(bids.size() == 0) {
        
        bids.add(new Bid(user, bidValue));
        valid = true;
        
      } else {
        
        currentHighest = this.getHighestBid().getBidValue();
        
        if(bidValue > currentHighest) {
          bids.add(new Bid(user, bidValue));
          valid = true;
        }
        
      }
      return valid;
    }

    
  } 
  
}
