// Implement a simple doubly-linked list. Give it just enough
// functionality to do some simple operations on it. Functions like
// length() are left as an exercise to the reader. While I'm at it,
// adding function pointer-ish use of interfaces for walking the list
// in forward() and reverse().
public class LL1 {
    public interface Walker {
	public void walk(LL1 node);
    }
    private LL1 next = null;
    private LL1 prev = null;
    public String data = "";

    public LL1(String data) {
	this.data = data;
    }

    public LL1 getNext() {
	return next;
    }

    public LL1 getPrev() {
	return prev;
    }

    public LL1 last() {
	LL1 node;
	for(node=this; node.next!=null; node = node.next) {
	}
	
	return node;
    }

    /** 
     * Appends a node after the current node. The return value is
     * always the argument that is passed in. This is done to make
     * this function's signature similar to that of insert.
     *
     * @return The new head node (ie: this).
     **/
    public LL1 append(LL1 node) {	
	node.next = this.next;
	this.next = node;
	node.prev = this;
	if (node.next != null) {
	    node.next.prev = node;
	}
	return this;
    }

    /** 
     * Insert a node after the current node. 
     *
     * @return The new head node (ie: node).
     **/
    public LL1 insert(LL1 node) {	
	node.next = this;
	node.prev = this.prev;
	this.prev = node;
	if (node.prev != null) {
	    node.prev.next = node;
	}
	return node;
    }

    public void forward(Walker walker) {
	LL1 node = this;

	while (node != null) {
	    walker.walk(node);
	    node = node.next;
	}
    }

    public void reverse(Walker walker) {
	LL1 node = this;

	while (node != null) {
	    walker.walk(node);
	    node = node.prev;
	}
    }

    public static void main(String[] args) {
	System.out.println("Foo");
	
	LL1 n = new LL1("first");
	n = n.append(new LL1("second"));
	n = n.insert(new LL1("zero"));
	
	System.out.println("==== S/b 0, 1, 2");
	n.forward(new Walker() {
		public void walk(LL1 node) { System.out.println("--> " + node.data); 
		}});
		
	System.out.println("==== S/b 1, 0");
	n.getNext().reverse(new Walker() {
		public void walk(LL1 node) { System.out.println("<-- " + node.data); 
		}});

	System.out.println("==== S/b 2, 1, 0");
	n.last().reverse(new Walker() {
		public void walk(LL1 node) { System.out.println("<-- " + node.data); 
		}});

    }
    
}