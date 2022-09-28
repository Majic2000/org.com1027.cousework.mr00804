/**
 * 
 */
package org.com1027.coursework.q1;


/**
 * @author mr00804
 *
 */
public class Bid {

   private User buyer;
   private double bidValue;
   
   public Bid(User buyer, double bidValue) {
     
     this.buyer = buyer;
     
     //if(this.bidValue <= 0) {
       //System.out.println("invalid bid value!");
     //}
     this.bidValue = bidValue;
     
   }
   
   public double getBidValue() {
     
     //if(this.bidValue <= 0) {
       //System.out.println("fail");
     //}
     return this.bidValue;
     
   }
   
   public User getBuyer() {
     
     return buyer;
     
   }

  @Override
  public String toString() {
    return this.getBuyer() + " bid £" + this.getBidValue();
  }
   

}
