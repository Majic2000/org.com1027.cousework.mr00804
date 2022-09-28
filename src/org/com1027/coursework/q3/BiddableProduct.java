/**
 * 
 */
package org.com1027.coursework.q3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author magic
 *
 */

public class BiddableProduct extends Product {
  
  private double reservedPrice;
  private List<Bid> bids;
  
  public BiddableProduct(int productId, String productName, double reservedPrice) throws IllegalArgumentException {
    
    super(productId, productName);
    
    if(productId <= 0) {
      throw new IllegalArgumentException();
    }
    if(productName == null) {
      throw new IllegalArgumentException();
    }
    
    this.reservedPrice = reservedPrice;
    bids = new ArrayList<Bid>();
     
  }
  
  @Override
  public int getProductId() {
    return super.getProductId();
    
  }
  
  @Override
  public String getProductName() {
    return super.getProductName();
    
  }
  
  @Override
  public double getCurrentPrice() {
    double currentItemValue = 0;
    double currentHighest = 0;
    
    if(bids.isEmpty()) {
      return 0;
      
    } else {
      
      
      currentHighest = bids.get(0).getBidValue();
      for(Bid i : bids) {
        currentItemValue = i.getBidValue();
          
        if(currentItemValue >= currentHighest) {
          currentHighest = currentItemValue;
            
        }    
      }
    }
    
    return currentHighest;
  }
  
  public boolean attemptToPurchase(User user, double bidValue) throws IllegalArgumentException {
    boolean valid = false;
    double currentPrice = this.getCurrentPrice();  //the highest price on item, bids is not empty
    
    if(bidValue <= 0) {
      throw new IllegalArgumentException();
    }
    
    if(bids.isEmpty()) {
      
      if(bidValue > 0) {
        bids.add(new Bid(user, bidValue));
        valid = true;
        
      } else {
        valid = false;
      }
      
    }else {
      
      if(bidValue > currentPrice && bidValue > 0) {
        
        bids.add(new Bid(user, bidValue));
        valid = true;
        
      }else {
        valid = false;
      } 
      
    }

    
    return valid;  
  }

  public boolean isProductSold() {
    //if there are bids on an item then return true
    boolean valid = true;
    
    double check = this.getCurrentPrice(); ///if it gives back 0 then there has been no bids therefore not sold
    
    if(check < this.reservedPrice) {
      valid = false;
    } else {
      valid = true;
    }
    
    
    return valid;
  }
  
  
  public String displayUserInfoForProduct() {
    //method has to know if there has been any bids put onto the product
    //find the highest bidder and output them 
    
    
    double bidVal = 0;
    String name = null;
    StringBuffer buff1 = new StringBuffer("");
    //double currentItemValue = 0;
    Bid b = null;
    
    if(bids.isEmpty()) {
      return buff1.toString();
    } else {
      
      b = bids.get(bids.size()-1);
      name = b.getBuyer().toString();
      bidVal = b.getBidValue();
      
      buff1.append(name + " bid £" + bidVal);
          
    }
    return buff1.toString();
  }
  
  public String displayHistory() {
    StringBuffer history = new StringBuffer("");
    StringBuffer buff3 = new StringBuffer(this.getProductId() + ": " + this.getProductName() + " = ");
    
    if(this.bids.isEmpty()) {
      
      return buff3.toString() + "no bids";
    
    } else {
        history.append('\n');
        for(Bid i : bids) {
          
          if(i.getBidValue() != this.getCurrentPrice()) {
            history.append(i.getBuyer().toString() + " bid £" + i.getBidValue() + "\n");
            
          }
          
        }
      
    }
    
    String variable = this.getProductId() + ": " + this.getProductName() + " = \n";
    
    return variable + this.displayUserInfoForProduct() + history.toString();
    //return this.getProductId() + ": " + this.getProductName() + " = \n" + this.bids.toString() + " \n" + buff2; 
    
  
  }
  
}
