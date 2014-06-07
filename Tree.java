import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A basic binary tree implementation. Not balanced or
 * nuthin. Intended to be a baseclass for useful implementations.
 *
 * Data is an Object of type Comparable with the key being what is
 * compared.
 **/
public class Tree {
    // These are protected instead of private with accessor/mutators
    // so that child classes can have efficient access to them.
    protected Tree leftChild = null;
    protected Tree rightChild = null;
    protected Comparable data = null;

    public interface Walker {
	public void walk(Tree node, String marker);
    }    

    public static class DataPrinterWalker implements Walker {
	public void walk(Tree node, String marker) {
	    System.out.printf("%-10s: %s\n", marker, node.data);
	}
    }

    public Tree(Comparable data) {
	this.data = data;
    }

    public void traverseInorder(Walker walker) {
	traverseInorder(walker, "X");
    }

    public void traverseInorder(Walker walker, String marker) {
 	if (leftChild != null) {
	    leftChild.traverseInorder(walker, marker + ".0");
	}
	walker.walk(this, marker);
	if (rightChild != null) {
	    rightChild.traverseInorder(walker, marker + ".1");
	}
    }

    public void traversePreorder(Walker walker) {
	traversePreorder(walker, "X");
    }

    public void traversePreorder(Walker walker, String marker) {
	walker.walk(this, marker);
 	if (leftChild != null) {
	    leftChild.traversePreorder(walker, marker + ".0");
	}
	if (rightChild != null) {
	    rightChild.traversePreorder(walker, marker + ".1");
	}
    }

    /**
     * Rotate tree left.
     * 
     * @ffoos New root.
     **/
    public Tree rotateRight() {
	Tree pivot = leftChild;
	
	leftChild = pivot.rightChild;
	pivot.rightChild = this;

	return pivot;
    }

    /**
     * Rotate tree right. 
     * 
     * @returns New root.
     **/
    public Tree rotateLeft() {
	Tree pivot = rightChild;
	
	rightChild = pivot.leftChild;
	pivot.leftChild = this;

	return pivot;
    }

    public int height() {
	return(1 + Math.max((leftChild == null) ? 0 : leftChild.height(),
			    (rightChild == null) ? 0 : rightChild.height()));
    }
    public static Tree makeTestTree() {
	Tree root = new Tree(5);
	root.leftChild = new Tree(3);
	root.leftChild.leftChild = new Tree(2);
	root.leftChild.rightChild = new Tree(4);
	root.rightChild = new Tree(8);
	root.rightChild.leftChild = new Tree(7);
	root.rightChild.rightChild = new Tree(10);

	return root;
    }

    ////////////////////////////////////////////////////////////////
    // Implement _iterative_ preorder traversal
    ////////////////////////////////////////////////////////////////
    private static class Breadcrumb {
	public Tree node;
	public String marker;

	public Breadcrumb(Tree node, String marker) {
	    this.node = node; 
	    this.marker = marker;
	}
    }

    public void traversePreorderIterative(Walker walker) {
	Deque<Breadcrumb> stack = new ArrayDeque<Breadcrumb>();

	stack.push(new Breadcrumb(this, "X"));
	traversePreorderIterative(stack, walker);
    }
    
    public static void traversePreorderIterative(Deque<Breadcrumb> stack, Walker walker) {
	for (Breadcrumb breadcrumb=stack.pop(); stack.peekFirst()!=null; breadcrumb=stack.pop()) {
	    Tree node = breadcrumb.node;
	    String marker = breadcrumb.marker;

	    // For preorder, process us first, then left child, then right
	    walker.walk(node, marker);
	    // Because we're using a stack, push kids in reverse order
	    // of our desired evaluation.
	    if (node.rightChild != null) {
		stack.push(new Breadcrumb(node.rightChild, 
					  marker + ".1"));
	    }
	    if (node.leftChild != null) {
		stack.push(new Breadcrumb(node.leftChild, 
					  marker + ".0"));
	    }
	}
    }
    ////////////////////////////////////////////////////////////////

    public static void main(String args[]) {
	Walker printer = new DataPrinterWalker(); // Keep one handy

	Tree root = makeTestTree();

	System.out.println("Height = " + root.height());
	System.out.println("Inorder");
	root.traverseInorder(printer);

	System.out.println("Preorder");
	root.traversePreorder(printer);

	System.out.println("Preorder Iterative");
	root.traversePreorderIterative(printer);

	System.out.println("================");
	root = root.rotateRight();
	System.out.println("Inorder");
	System.out.println("Height = " + root.height());
	root.traverseInorder(printer);
	
	System.out.println("================");
	root = root.rotateLeft();
	System.out.println("Height = " + root.height());
	System.out.println("Inorder");
	root.traverseInorder(printer);
    }
}
