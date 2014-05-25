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

    public void append(LL1 node) {	
	node.next = this.next;
	this.next = node;
	node.prev = this;
	if (node.next != null) {
	    node.next.prev = node;
	}
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
	n.append(new LL1("second"));
	
	n.forward(new Walker() {
		public void walk(LL1 node) { System.out.println("--> " + node.data); 
		}});
		
	n.next.reverse(new Walker() {
		public void walk(LL1 node) { System.out.println("<-- " + node.data); 
		}});

    }
    
}