import java.util.Random;
/**
 * Implementaion of a treap per http://enil.us/1oe5MbH.  A binary
 * search tree with a probablistic arrangement that has best average
 * performance (http://enil.us/1oVqrCI) of commonly used BSTs.
 *
 * The data for each node is an String for convenience. It should
 * likely be an interface if we were storing real data.
 *
 * The random priority is an int. An int gives us 4.2G possible values
 * to avoid a collision. For our toy trees, this is fine. For bigger
 * data sets, a long or byte string might be required. They, of
 * course, slow down the implementation and their cost would need to
 * be weighed against the time of using another BST implementation
 * that doesn't use random numbers. I'm implementing a treap because I
 * want to implement a BST and I've never implemented a treap so I
 * wanna have some fun.
 * 
 * Making the type of the random data an interface that implemented
 * Comparable would allow us to change the size of the random data
 * easily. A good idea if we think we may have to change the size of
 * the random data but too much effort for this example.
 *
 * Keep in mind that a random int includes negative values.
 **/
public class Treap {
    // We'll share a single PRNG for all Treaps.
    private static Random random = new Random();

    private String data; 
    private final int priority = new random.nextInt();

    private Treap leftChild = null;
    private Treap rightChild = null;

    public Treap(String data) {
	this.data = data;
    }

    // Insert the node. Rebalance as we back out of the recursion.
    private Treap insert(Treap node) {
	int relation = node.data.compareTo(this.data);
	if (relation == 0) {
	    throw new IllegalArgumentException("Tried to insert duplicate key: " + 
					       node.data);
	}

	if (relation < 0) {
	    if (leftChild != null) {
		// Recurse down left subtree
		return leftChild.findNearestNode(node);
	    }
	    else {
		this.leftChild = node;
	    }
	}
	else {
	    // relation is > 0
	    if (rightChild != null) {
		// Recurse down right subtree
		return rightChild.findNearestNode(node);
	    }
	    else {
		this.rightChild = node;
	    }
	}

    }
}