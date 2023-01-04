/**
 * Abstract class that is used to be the value inserted in a Node or
 * in a BiNode object.
 * 
 * Since a CounterObject is a Comparable it could be used elsewhere...
 * 
 * In order to use this clas one must provide the three abstract methods bodies.
 */


public abstract class CounterObject implements Comparable {

  protected int occurs;             // occurrences of this CounterObject

  /**
   * Tests if this object is less than the other.
   * 
   * @param other
   *  
   * @return  true if this is less than the other; false otherwise.
   */
  public abstract boolean lessThan (CounterObject other);
 
  /**
   * Tests if this object is greater than the other.
   * 
   * @param other
   *  
   * @return  true if this is greater than the other; false otherwise.
   */
  public abstract boolean greaterThan (CounterObject other);
  
  /**
   * 
   * @return anything you need!
   */
  public abstract Object getValue();

  /**
   * Compares this to the other CounterObjects calling the abstract methods.
   */
  public int compareTo(Object other) {
    if (this.lessThan((CounterObject)other))
      return -1;
    if (this.greaterThan((CounterObject)other))
      return 1;
    return 0;
  }

  public void addOccurrence()  {
    this.occurs++;
  }

  public int getOccurrences()  {
    return this.occurs;
  }

  public void setOccurrences (int occurs)  {
    this.occurs = occurs;
  }

}  //==> class CounterObject