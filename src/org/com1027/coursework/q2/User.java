/**
 * 
 */
package org.com1027.coursework.q2;


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
    
    String name1 = this.name;
    char firstChar = this.name.charAt(0);
    char lastChar = this.name.charAt(this.name.length()-1);
    
    StringBuffer buff = new StringBuffer("");
    buff.append(firstChar);
    
    for(int i = 0; i < 3; i++) {
   
      buff.append('*');
      
    }
    
    buff.append(lastChar);
    
    return buff.toString();
    
  }
  

  @Override
  public String toString() {
    return this.getName();
  }
  
  
  
}
