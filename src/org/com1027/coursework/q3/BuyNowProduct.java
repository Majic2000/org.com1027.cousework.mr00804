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
public class BuyNowProduct extends Product {
  private double price;
  private int quantity;
  private List<Purchase> purchase;
  
  public BuyNowProduct(int productId, String productName, double price, int quantity) throws IllegalArgumentException {
    super(productId, productName);
    
    if(productId <= 0 || productName == null || price <= 0 || quantity <= 0) {
      throw new IllegalArgumentException();
    }
    
    this.price = price;
    this.quantity = quantity;
    purchase = new ArrayList<Purchase>();
  }
  
  public boolean attemptToPurchase(User user, int quantity) throws IllegalArgumentException {
    boolean valid =  false;
    
    //needs to go through the array and find highest
    //also needs to check if the wanted quantatiy > actual quantity if it is then return false
    //something to do with price?
    int updatedQuantity = this.howManyPurchases();
    updatedQuantity = this.quantity - updatedQuantity;
    //int updatedQuantity = this.quantity;
    
    if(quantity <= 0) {
      throw new IllegalArgumentException();
    }
      
    if(quantity > 0 && quantity <= updatedQuantity ) {
      purchase.add(new Purchase(user, quantity));
      updatedQuantity -= quantity;
      valid = true;
        
    } else {
      valid = false;
    }
    
    //if(updatedQuantity <= 0) {
    //  valid = false;
    //}
    
    return valid;
      
  }
    
  public int getQuantity() {
    return this.quantity;
  }
  
  public int howManyPurchases() {
    //needs to go through the puchases array and count them up
    int counter = 0;
    
    for(Purchase i : purchase) {
      counter += i.getQuantityPurchased();
    }
    
    return counter;
  }
  
  @Override
  public String displayHistory() {
    
    StringBuffer buffer = new StringBuffer(this.getProductId() + ": " + this.getProductName() + " quantity: " + this.quantity + "\n");
    StringBuffer buffer2 = new StringBuffer("");
    String variable = "buy now history: \n";
    String name = null;
    double quantityBought = 0;
    
    if(purchase.isEmpty()) {
      return buffer.toString() + "no purchases";
    } else {
      
      for(Purchase i : purchase) {
        
        name = i.getBuyer().toString();
        quantityBought = i.getQuantityPurchased();
        
        if(i != purchase.get(purchase.size()-1)) {
          buffer2.append(name + " bought " + (int)quantityBought + "\n");
        }
      }
     
    }
    
    return buffer.toString() + variable + buffer2.toString() + this.displayUserInfoForProduct() + "\n";
  }
  
  @Override
  public double getCurrentPrice() {
    
    return this.price;
    
  }
  
  @Override
  public boolean isProductSold() {
    boolean valid = false;
    //needs to check weather there has been any purchases of the current item
    double updatedLimit = this.howManyPurchases();
    updatedLimit = this.quantity - updatedLimit;
    
    if(updatedLimit <= 0) {
      valid = true;
    } else {
      valid = false;
    }
    
    return valid;
  }
  
  @Override
  public String displayUserInfoForProduct() {
    
    StringBuffer buffer = new StringBuffer("");
    String name = null;
    double quantityPurchased = 0;
    Purchase p = null;
    
    if(this.howManyPurchases() == 0) {
      return buffer.toString();
      
    } else {
      
      p = purchase.get(purchase.size()-1);
      name = p.getBuyer().toString();
      quantityPurchased = p.getQuantityPurchased();
      
      buffer.append(name + " bought " + (int)quantityPurchased);
      
    }
    return buffer.toString(); 
    
  }

}
