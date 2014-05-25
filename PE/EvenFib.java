public class P2_EvenFib {
    public static void main(String args[]) {
	// Ignoring the initial few numbers, Fib numbers come in the
	// pattern of odd, odd, even. So we process Fibs 3 at a time.
	int sum=0;
	int o1=0, o2=1, e=2;
	
	while (e <= 4000000) {
	    sum += e;
	    o1 = o2 + e;
	    o2 = e + o1;
	    e = o1 + o2;
	}
	
	System.out.println(sum);
    }
}