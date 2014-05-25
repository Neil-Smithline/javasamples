public class P18_MaxPathSum {
    /*
     * We're going to store the tree as a single-dim array. Declaring
     * a 2D array will waste half the space because the tree is
     * triangular, not rectangular. I know that both memory for the
     * array, size n, and the 2D array, 2n, are both O(n), but wasting
     * that space hurts me.
     *
     * Being that row J of the tree has J elements in it, for an
     * element in the array at position P that is on row J, element
     * P+J is the left child and P+J+1 is the right child.
     */
    static final int height = 15;	// height of input tree 
    static final int data[] = {		// input tree
	75,
	95, 64,
	17, 47, 82,
	18, 35, 87, 10,
	20,  4, 82, 47, 65,
	19,  1, 23, 75,  3, 34,
	88,  2, 77, 73,  7, 63, 67,
	99, 65,  4, 28,  6, 16, 70, 92,
	41, 41, 26, 56, 83, 40, 80, 70, 33,
	41, 48, 72, 33, 47, 32, 37, 16, 94, 29,
	53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14,
	70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57,
	91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48,
	63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31,
	04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23,
    };

    /*
     * We're gonna do greedy summing. That is, we're gonna calculate
     * the max path sum from the bottom up for every node in the
     * tree. This is O(n) calculations and memory. Don't see how we can beat that.
     */
    static int sums[] = new int[data.length]; // A shadow array for data[] 

    public static void main(String args[]) {
	int h = height; 	// current row
	int c = h;		// current col

	// Loop through all cells
	for (int i=data.length-1;  i>=0; i--, c--) {
	    if (c == 0) {	// Change row height as we go
		h--;
		c = h;
	    }
	    sums[i] = data[i];

	    int j = i + h;
	    if (j < data.length) {
		sums[i] += Math.max(sums[j], sums[j+1]);
	    }
	}
	
	System.out.println(sums[0]);
    }

}