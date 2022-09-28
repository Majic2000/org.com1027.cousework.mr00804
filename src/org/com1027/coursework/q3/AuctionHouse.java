/**
 * 
 */
package org.com1027.coursework.q3;

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
    forSaleProducts = new HashMap<Product, User>();
    soldProducts = new HashMap<Product, User>();
    unsoldProducts = new HashMap<Product, User>();
  }
  
  public boolean checkExistence(Product product) throws IllegalArgumentException {
    boolean validProduct = false;
   
    for (Map.Entry<Product, User> currentProduct : forSaleProducts.entrySet()) {
      
      if(currentProduct.getKey().equals(product)) {
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
  
    if(soldProducts.isEmpty()) {
      return emptyResponse; 
    } else {
      
      for(Map.Entry<Product, User> pair: soldProducts.entrySet()) {  
        
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
    
    if(unsoldProducts.isEmpty()) {
      return response.toString();
    }
    for(Map.Entry<Product, User> pair: unsoldProducts.entrySet()) {
      
      idOfProduct = pair.getKey().getProductId();
      productName = pair.getKey().getProductName();
      
      empty = (idOfProduct + " - " + productName + "\n");
      response.append(empty);
      
    }
    
    return response.toString();
  }
  
  public void endAuction(Product product) {
    
    Product unavailable = product;
    User winner = forSaleProducts.get(product);
    int id = product.getProductId();
    double price = product.getCurrentPrice();
    
    if(price == 0) {
      unsoldProducts.put(unavailable, null);
      
    }
    else {
      forSaleProducts.remove(product);
      soldProducts.put(unavailable, winner); 
      winner.wonAuction(id, price);
      
    }
    
  }
  
  public boolean placeBid(BiddableProduct product, User user, double bidValue) throws IllegalArgumentException {
    
    if(product == null) {
      throw new IllegalArgumentException();
    }
    
    if(bidValue <= 0) {
      throw new IllegalArgumentException();
    }
    
    if(user == null) {
      throw new IllegalArgumentException();
    }
    
    boolean valid = true;
    double highestPrice = 0;
    
    if(this.soldProducts.isEmpty() == true) {
      if(this.register(product, user) == true) {
        valid = false;
      } else {
        
        if(bidValue <= product.getCurrentPrice()) {
          valid = false;
          
        } else {
          product.attemptToPurchase(user, bidValue);
          valid = true;
        }   
        
      }
    } else {
  
      if(bidValue <= product.getCurrentPrice()) {
        valid = false;
        
      } else {
        product.attemptToPurchase(user, bidValue);
        valid = true;
      }
 
    }
 
    return valid;
    
  }
  
  public boolean register(Product product, User user) throws IllegalArgumentException {
    //need to put the product and user into the forSaleProducts in order to create an item in the given array and establish a connection
    
    boolean registered = true;
    
    if(product == null) {
      throw new IllegalArgumentException();
      
    } 
    else if (user == null) {
      throw new IllegalArgumentException();  
      
    } else {
      
      if(forSaleProducts.containsKey(product)) {
        User buyer = forSaleProducts.get(product);
        
        if(buyer.equals(user)) {
          registered = false;
          
        } else {
          forSaleProducts.put(product, user);
          unsoldProducts.put(product, user);
          
        }
        
      }
      
      forSaleProducts.put(product, user);
      unsoldProducts.put(product, user);
      
    }
  
    return registered;
  }
  
  public boolean buyNow(BuyNowProduct product, User buyer, int quantity) throws IllegalArgumentException {
    boolean valid = false;
    
    if(product == null) {
      throw new IllegalArgumentException();
    }
    if(buyer == null) {
      throw new IllegalArgumentException();
    }
    if(quantity <= 0) {
      throw new IllegalArgumentException();
    }
    
    
    if(product.attemptToPurchase(buyer, quantity) == false) {
      valid = false;
      
      
    } else {

      if(forSaleProducts.isEmpty() == true) {
        
        if(this.register(product, buyer) == false) {
          valid = false;
        
        } else {
          valid = true;
          buyer.buy(product.getProductId(), quantity);
          product.attemptToPurchase(buyer, quantity);
          buyer.buy(product.getProductId(), quantity);
          
        }
        
      } 
      else {
      
        if(this.register(product, buyer) == false) {
          
          valid = true;
          product.attemptToPurchase(buyer, quantity);            
          buyer.buy(product.getProductId(), quantity);
          
        } else {
          valid = false;
          
        }
        
      }
    }
    
    
    return valid;
  }
  
}
