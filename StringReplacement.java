class StringReplacement {
    static private String upcaseLetters(String sin) {
	StringBuffer sout = new StringBuffer();
	
	if (sin != null) {
	    for (int i=0; i<sin.length(); i++) {
		sout.append(Character.toUpperCase(sin.charAt(i)));
	    }
	}
	return sout.toString();
    }

    static private String reverseIterative(String sin) {	
	StringBuffer sout = new StringBuffer();
	
	if (sin != null) {
	    for (int i=sin.length()-1; i>=0; i--) {
		sout.append(sin.charAt(i));
	    }
	}
	return sout.toString();
    }

    static private String reverseRecursive(String sin) {	
	if (sin == null) {
	    return null;
	}
	else {
	    return reverseRecursiveWork(sin, sin.length()-1);
	}
    }
    
    static private String reverseRecursiveWork(String sin, int pos) {
	if (pos == -1) {
	    return "";
	}
	else {
	    return sin.charAt(pos) + reverseRecursiveWork(sin, pos-1);
	}
    }

    static public void main(String args[]) {
	for (String s : args) {
	    System.out.println("================================================================");
	    System.out.printf("-------------------------------- %s\n", s);
	    System.out.printf("upcaseLetters: %s\n", upcaseLetters(s));
	    System.out.printf("reverseIterative: %s\n", reverseIterative(s));
	    System.out.printf("reverseRecursive: %s\n", reverseRecursive(s));
	}
    }
}