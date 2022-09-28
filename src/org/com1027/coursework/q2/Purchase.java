/**
 * 
 */
package org.com1027.coursework.q2;


/////needs commenting

/**
 * @author magic
 *
 */
public class Purchase {

  private User buyer;
  private int quantityPurchased;
  
  public Purchase(User buyer, int amountPurchased) {
    
    this.buyer = buyer;
    this.quantityPurchased = amountPurchased;
    
  }
  
  public User getBuyer() {
    return this.buyer;
  }
  
  public double getQuantityPurchased() {
    return this.quantityPurchased;
  }

  @Override
  public String toString() {
    return this.buyer.toString() + " bought " + this.quantityPurchased;
  }
  

  
  
  
}
