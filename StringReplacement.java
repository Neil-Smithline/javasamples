class StringReplacement {
    // A stupid function but gets you extracting chars from a String
    static private String upcaseLetters(String sin) {
	StringBuffer sout = new StringBuffer();
	
	if (sin != null) {
	    for (int i=0; i<sin.length(); i++) {
		sout.append(Character.toUpperCase(sin.charAt(i)));
	    }
	}
	return sout.toString();
    }

    // Basically a minor mod of upcaseLetters()
    static private String reverseIterative(String sin) {	
	StringBuffer sout = new StringBuffer();
	
	if (sin != null) {
	    for (int i=sin.length()-1; i>=0; i--) {
		sout.append(sin.charAt(i));
	    }
	}
	return sout.toString();
    }

    // A recursive version of reverseIterative(). Way more fun. We use
    // a reverseRecursive(String, int) as a helper so that we don't
    // need to copy the String to a StringBuffer so that we can
    // shorten it as we go. Kind of silly to worry about performance
    // with all of this unneeded recursion but extra copying causes me
    // pain.
    static private String reverseRecursive(String sin) {	
	if (sin == null) {
	    return null;
	}
	else {
	    return reverseRecursive(sin, sin.length()-1);
	}
    }
    
    static private String reverseRecursive(String sin, int pos) {
	// We do one extra recursive call that returns the empty
	// String. We could terminate at pos==0 if we'd put a check
	// into reverseRecursive() to prevent a call with a
	// zero-length String. That is prolly more efficient but I
	// think this code is a bit neater as it has fewer
	// conditionals. I like its simplicity.
	if (pos == -1) {
	    return "";
	}
	else {
	    return sin.charAt(pos) + reverseRecursive(sin, pos-1);
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