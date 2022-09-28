/**
 * 
 */
package org.com1027.coursework.q2;


/**
 * @author mr00804
 *
 */
public abstract class Product {

 private int productId;
 private String productName;
 
 public Product(int productId, String productName) {
   
   this.productId = productId;
   this.productName = productName;
   
 }
 
 abstract String displayHistory();
 
 abstract String displayUserInfoForProduct();
 
 abstract double getCurrentPrice();
 
 public int getProductId() throws IllegalArgumentException {
   if(this.productId <= 0) {
     throw new IllegalArgumentException("invalid product id");
   }
   return this.productId;
 }
 
 public String getProductName() throws IllegalArgumentException {
   if(this.productName == null) {
     throw new IllegalArgumentException("invalud product name");
   }
   return this.productName; 
 }
 
 abstract boolean isProductSold();
 
 @Override
 public String toString() {
     return this.productId + " - " + this.productName;
 }
  
}

