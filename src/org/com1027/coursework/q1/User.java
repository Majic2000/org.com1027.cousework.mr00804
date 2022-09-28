/**
 * 
 */
package org.com1027.coursework.q1;


/**
 * @author mr00804
 *
 */
public class User {

  private String name;
  
  
  public User(String name) throws IllegalArgumentException {
    
    if(name == null) {
      throw new IllegalArgumentException("name is null");
    }
    this.name = name;
    
  }
  
  public String getName() {
 
    return this.name;
    
  }
  

  @Override
  public String toString() {
    return this.name;
  }
  
  
  
}
