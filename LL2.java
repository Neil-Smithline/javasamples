// Implement a simple singly-linked list. Give it just enough
// functionality to do some simple operations on it. Functions
public class LL2 {
    public interface Walker {
	public void walk(LL2 node);
    }
    public class Node {
	private LL2 next = null;
	public String data = "";

	public Node(String data) {
	    this.data = data;
	}

	public LL2 getNext() {
	    return next;
	}

	public String getData() {
	    return data;
	}

	// Return the predecessor to node. Null is returned if node is the
	// head. An IllegalArgumentException is thrown if node isn't part
	// of the list beginning with this.
	public LL2 findPredecessor(LL2 node) {
	    if (this == node) {
		return null;
	    }

	    for(LL2 temp=this; temp.next!=null; temp = temp.next) {
		if (temp.next == node) {
		    return temp;
		}
	    }
	
	    // Didn't find node - we have an error
	    throw new IllegalArgumentException("Couldn't find node in list.");
	}

	/** 
	 * Appends a node after the current node. The signature has been
	 * designed to match that of insert().
	 *
	 * @return New head (always the same as old head).
	 **/
	public LL2 append(LL2 head, LL2 node) {
	    if (head == null) {
		throw new IllegalArgumentException("Cannot have a null head.");
	    }

	    if (node == null) {
		throw new IllegalArgumentException("Cannot insert a null node.");
	    }

	    // The next two checks are of dubious value. They might catch
	    // errors but only when there's overlap between our
	    // arguments. For example, they will permit reuse of a node
	    // that's in the middle of the list.
	    if (head == node) {
		throw new IllegalArgumentException("Cannot insert head node a second time");
	    }

	    if (head == node) {
		throw new IllegalArgumentException("Cannot insert head node a second time");
	    }

	    node.next = this.next;
	    this.next = node;

	    return head;
	}


	/** 
	 * Insert a node before the current node.
	 *
	 * @return New head.
	 **/
	public LL2 insert(LL2 head, LL2 node) {
	    if (head == null) {
		throw new IllegalArgumentException("Cannot have a null head.");
	    }

	    if (node == null) {
		throw new IllegalArgumentException("Cannot insert a null node.");
	    }

	    // The next two checks are of dubious value. They might catch
	    // errors but only when there's overlap between our
	    // arguments. For example, they will permit reuse of a node
	    // that's in the middle of the list.
	    if (head == node) {
		throw new IllegalArgumentException("Cannot insert head node a second time");
	    }

	    if (head == node) {
		throw new IllegalArgumentException("Cannot insert head node a second time");
	    }

	    node.next = this.next;
	    this.next = node;

	    return this;
	}

	public void forward(Walker walker) {
	    LL2 node = this;

	    while (node != null) {
		walker.walk(node);
		node = node.next;
	    }
	}

    }

    private Node head;

    public LL2(String data) {
	head = new Node(data);
    }

    public Node first() {
	return head;
    }

    // This functions is horribly inefficient. As last() will
    // generally be called prior to calling reverse(), the utility of
    // reverse() is reduced. Having a list pointer that points to the
    // head and tail node of the list would fix this. That said,
    // that's not a use case for this sample so we go with slower than
    // molasses. With our small N, what does it matter.
    public LL2 last() {
	LL2 node;
	for(node=head; node.next!=null; node = node.next) {
	}
	
	return node;
    }


    public static void main(String args[]) {
	LL2 list = new LL2("second");
	head = head.append(list.head, new LL2("fourth")); // append to end of list
	head = head.append(head, new LL2("third"));  // append into middle
	//head = head.insert(head, new LL2("zero"));
	
	System.out.println("==== S/b 2, 3, 4");
	head.forward(new Walker() {
		public void walk(LL2 node) { System.out.println("--> " + node.data); 
		}});
	
	// Test last() and findPredecessor()
	LL2 last = head.last();
	System.out.printf("Last (s/b 4) = %s\n", last.getData());
	System.out.printf("Second to last (s/b 3) = %s\n", 
			  head.findPredecessor(last).getData());


	
    }
}