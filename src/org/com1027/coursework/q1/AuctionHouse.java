/**
 * 
 */
package org.com1027.coursework.q1;

//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/**
 * @author mr00804
 *
 */
public class AuctionHouse {
  
  //creating variables that all hold maps from the uml diagram
  private Map<Product, User> forSaleProducts;
  private Map<Product, User> soldProducts;
  private Map<Product, User> unsoldProducts;
  
  //creating the constructor that initialises all maps
  public AuctionHouse() {
    forSaleProducts = new HashMap<Product, User>();
    soldProducts = new HashMap<Product, User>();
    unsoldProducts = new HashMap<Product, User>();
  }
  
  //creating a method of type boolean called "checkExistance" checks weather the wanted product is for sale
  public boolean checkExistence(Product product) throws IllegalArgumentException {
    
    //initialises a variable of type boolean to false called validProduct
    boolean validProduct = false;
   
    //goes through all the current items in forSaleProducts one by one
    for (Map.Entry<Product, User> currentProduct : forSaleProducts.entrySet()) {     
      
      //while going through the Map, if the key of the current item is equal to the paramiter variable product then...
      if(currentProduct.getKey().equals(product)) {
        //validProduct is then equal to true.
        validProduct = true;
      }    
      
    } 
    
    //returns true or false based on principles above
    return validProduct;
    
  }
  
  //a method of Sype String called displaySoldProducts
  public String displaySoldProducts() {
    
    //declaring and initialising a new StringBuffer of name response to be able to append stings if needed
    StringBuffer response = new StringBuffer("");
    //delcaring and initialising a new variable of type tring that holds the StringBuffer.toString() (changed the type of buffer string to string)
    String emptyResponse = response.toString();
    //initialising and declaring a variable of type int to 0
    int productId = 0;
    //initialising and declaring a variable of type String to null
    String price = null;    
    //initialising and declaring a variable of type String to null
    String usersNameBid = null; 
    //initialising and declaring a variable of type String to ""
    String appendedResponse = "";

    //if the map of soldProducts is empty then return an empty response
    //will automatically quit function
    if(soldProducts.isEmpty()) {
      return emptyResponse; 
    }
    
    //a foreach loop to go through the soldProducts map one by one
    for(Map.Entry<Product, User> pair: soldProducts.entrySet()) {
      
      //initialising productId to have the id of the product of the current item 
      productId = pair.getKey().getProductId();
      //initialising usersNameBid to have the current user object, then .toString() the object is called which is a string in the User class
      usersNameBid = pair.getValue().toString();
      //initialising price to have the current key (of type product) then calls getHighestBid to get the highest bid 
      //value for the item and then toString occurs which makes it of type string
      price = pair.getKey().getHighestBid().toString();
      
      //initialising appendedResponse to be equal to the string of what we want to append
      appendedResponse = (productId + " - " + price + "\n");
      //when appended, string buffer gains "productId + " - " + price + "\n""
      response.append(appendedResponse);
      
    }

    //returns whatever is in the string Buffer
    return response.toString();
  }
  
  
  //method of type String called displayUnsoldProducts()
  public String displayUnsoldProducts() {
    
    //declaring and initialising a new String buffer for this method 
    StringBuffer response = new StringBuffer("");
    
    //declaring and initialising a variable of type int holding 0 
    int idOfProduct = 0;
    //declaring and initialising a variable of type String holding null
    String productName = null;
    //declaring and initialising a variable of type String holding ""
    String empty = "";
    
    //if the unsoldProducts map is empty then return response.toString (which is "")
    if(unsoldProducts.isEmpty()) {
      return response.toString();
    }
    //else a for loop to go through all the unsoldProducts one by one
    for(Map.Entry<Product, User> pair: unsoldProducts.entrySet()) {
      
      //variable gets the product id through accessing the key which is of type Product abd calling the getProductName method
      idOfProduct = pair.getKey().getProductId();
      //variable gets the product name through accessing the key which is of type Product and calling the getProductName method
      productName = pair.getKey().getProductName();
      
      //empty variable gets the wanted formate of how the data should be displayed
      empty = (idOfProduct + " - " + productName + "\n");
      //respond buffer gets appended with the corrent data ("empty variable data")
      response.append(empty);
      
    }
    
    //returns what ever the StringBuffer, .toString is used in order to change from StringBuffer to string
    return response.toString();
  }
  
  //end auction method of type void, that takes in the paramiters of type Product
  public void endAuction(Product product) {
    
    /**
    get the user that had the product also to put him/her into soldProducts
    needs to remove the key and the value
    needs to put that link and all data into forSoldProducts hashmap
    also need to take away from unsold items
    
    some times needs to add to sold items 
    with if statement?
    need to see if the value of the bidded price is not 0.0
    in bid there might be a method that does that ?
    
    */
    
    //declaring and initialising a variable of type Product to be equal to the parameter variable of the method
    Product unavailable = product;
    //declaring a variable of type User
    User winner;
    //declaring and initialising a variable of type double that holds 0 
    double reservedPs = 0;
   
    //initialising winner to be equal to the product that is retruved from the forSaleProducts map
    winner = forSaleProducts.get(product);
    
    //gets price of item for check
    reservedPs = product.getReservedPrice();
    
    //if the price is smaller of equal to 0 then add the product to the unsoldProducts map and make a link with link as no user 
    if(reservedPs <= 0) {
      unsoldProducts.put(unavailable, null);
      
    }
    //else remove the key thats given as a paramiter 
    //and add the item and the user into the soldProducts map
    else {
      forSaleProducts.remove(product);
      soldProducts.put(unavailable, winner);   
      
    }
    
  }
  
  //method of type boolean that takes in 3 paramites, used to check if a user can submit a bid also throws an exception
  public boolean placeBid(Product product, User user, double bidValue) throws IllegalArgumentException {
    
    //initialising and declaring a variable of type boolean to false
    boolean valid = false;
    
    
    //if one of the variable parameters is null then throw an exception
    if(product == null || user == null) {
      throw new IllegalArgumentException();
    } else {
      
      //else and if the method of .placeBid of type Product validates to true then make the variable valid equals to true
      if(product.placeBid(user, bidValue) == true) {
        valid = true;
      }  
 
    }
    
    //return valid;
    return valid;
    
  }
  
  //method of type boolean that takes in 2 paramiter variables, is public and throws an exception
  public boolean register(Product product, User user) throws IllegalArgumentException {
    
    //need to put the product and user into the forSaleProducts in order to create an item in the given array and establish a connection
    
    //initialising and declaring a variable of type boolean to be equal to true
    boolean registered = true;
    
    //if either of the paramiter variables are null then an exception is thrown
    if(product == null || user == null) {
      throw new IllegalArgumentException();
      
    } else {
      
      //else and if if the map forSaleProducts contains a key of the paramiter variable then...
      if(forSaleProducts.containsKey(product)) {
        
        //declaring and initialising a variable of type User to hold a user that is associated with the key product
        User buyer = forSaleProducts.get(product);
        
        //if the buyer variable is equal to the user paramaterised variable then registered is equal to false
        if(buyer.equals(user)) {
          registered = false;
          
        //else puts the user and the product paramiter variables into the forSaleProducts map and the unsoldProducts map
        } else {
          forSaleProducts.put(product, user);
          unsoldProducts.put(product, user);
          
        }
        
      }
      
      //or else if it doesnt contain the wanted key then puts 
      //the product and the user into the forSaleProducts and the unsoldProducts map
      forSaleProducts.put(product, user);
      unsoldProducts.put(product, user);
      
    }
    
    //check if theres a key product match, if there is then 
    //map.key() ==> gives the value of the given key
    //if value of given key is also equal to the paramiter 
  
    //returns registered, either true or false
    return registered;
  }
  
}
