
package DoublyLinkedList;

public class DListNode {

    /**
     *  item references the item stored in the current node.
     *  prev references the previous node in the DList.
     *  next references the next node in the DList.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    public int quantity, type, hunger;
    protected DListNode prev;
    protected DListNode next;

    /**
     *  DListNode() constructor.
     *  @param q the quantity to store in the node.
     *  @param p the node previous to this node.
     *  @param n the node following this node.
     */
    DListNode(int q, int t, int h , DListNode p, DListNode n) {
        quantity = q;
        type = t;
        hunger = h;
        prev = p;
        next = n;
    }
    public DListNode getNext() {
        return next;
    }
    public DListNode getPrev() {
        return prev;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getType() {
        return type;
    }
    public int getHunger() {
        return hunger;
    }
}