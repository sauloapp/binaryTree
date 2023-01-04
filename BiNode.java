
/**
 * A bi-linkable node that extends Node adding a left reference.
 * In fact, this implementation uses shadowing to simplify sone things..
 * 
 * @author saulo
 *
 */
public class BiNode extends Node {

  private BiNode right,     // shadows Node.right: points to right BiNode
	     		 left;      // points to left BiNode

  public BiNode()  {
    super();
  }

  public BiNode(CounterObject value)  {
    super(value);
  }

  /*
   * Overrides Node.changeRight.
   * 
   *  A ClassCastException will be raised if other is a Node object!
   */
  public void changeRight (Node other) throws ClassCastException {   
    this.right = (BiNode)other;           
  }                                       

  public void changeLeft (BiNode other)  {
    this.left = other;
  }

  /**
   * Overrides Node.getRight()
   * Thanks to the covariance rule!
   */
  public Node getRight()  {
    return this.right;
  }

  public BiNode getLeft()  {
    return this.left;
  }

} //==> class BiNode