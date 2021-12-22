package DoublyLinkedList;

public class DListNode {

    /**
     *  item references the item stored in the current node.
     *  prev references the previous node in the DList.
     *  next references the next node in the DList.
     *
     *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    public Object obj;
    protected DListNode prev;
    protected DListNode next;

    /**
     *  DListNode() constructor.
     *  @param i the item to store in the node.
     *  @param p the node previous to this node.
     *  @param n the node following this node.
     */
    DListNode(Object o, DListNode p, DListNode n) {
        obj = o;
        prev = p;
        next = n;
    }
    public DListNode getNext() {
        return next;
    }
    public DListNode getPrev() {
        return prev;
    }
    public Object getValue() {
        return obj;
    }
}