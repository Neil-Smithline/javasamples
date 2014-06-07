public class AVLTree extends Tree {
    private int height;

    private int setHeight() {
	height = 1 + Math.max((leftChild == null) ? 0 : leftChild.height,
			      (rightChild == null) ? 0 : rightChild.height);
	return height;
    }

    
    
