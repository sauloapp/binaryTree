/*
 * A single linkable node that stores a CounterObject
 */
public class Node  {

  protected CounterObject value;    // information
  protected Node right;        // points to right node

  public Node () {}
  
  public Node (CounterObject value)  {
    this.value = value;
  }

  public void changeRight (Node other)  {
    this.right = other;
  }

  public void changeValue (CounterObject newValue)  {
    this.value = newValue;
  }

  public void addOccurrence()  {
    this.value.addOccurrence();
  }

  public void setOccurrences(int occurs)  {
    this.value.setOccurrences(occurs);
  }

  public int getOccurrences()  {
    return this.value.getOccurrences();
  }

  public Node getRight()  {
    return this.right;
  }

  public CounterObject getValue()  {
    return this.value;
  }

}  //==> class Node
