/**
 * This example was written to confirm an error on http://enil.us/1phlmEX
 *
 * The page claimed:
 ****************************************************************
 * The following rules for inherited methods are enforced:
 *
 * Methods declared public in a superclass also must be public in all
 * subclasses.
 * 
 * Methods declared protected in a superclass must either be protected
 * or public in subclasses; they cannot be private.
 * 
 * Methods declared without access control (no modifier was used) can
 * be declared more private in subclasses.
 * 
 * Methods declared private are not inherited at all, so there is no
 * rule for them.
 ****************************************************************
 * Compiling the example below produces this error:
 * $ javac C1.java
 * C1.java:7: error: deflt() in C1_2 cannot override deflt() in C1
 *     private String deflt() { return "C1_2.deflt"; }
 *                    ^
 * attempting to assign weaker access privileges; was package
 * 1 error
 **/
public class C1 {
    String deflt() { return "C1.deflt"; }

}

class C1_2 extends C1 {
    private String deflt() { return "C1_2.deflt"; }
}