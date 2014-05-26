import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Should go into a separate file if this was a real app. See class 
class FirstContactDates implements Comparator {
    private static Map<Integer, Date> dates = new HashMap<Integer, Date>();
    
    public void put(Integer eid, Date d) {
	    dates.put(eid, d);
    }
    
    // We get the employee ID for each employee, use it as the key
    // into our Map and then use the value for the comparison.
    public int compare(Object obj1, Object obj2) {
	// Gotta cast because Comparator.compare() takes
	// Objects. Use temp vars to keep the code simpler.
	CompStar emp1 = (CompStar)obj1;
	CompStar emp2 = (CompStar)obj2;
	long date1 = dates.get(emp2.getEId()).getTime();
	long date2 = dates.get(emp1.getEId()).getTime();
	
	return Long.signum(date2 - date1);
    }
}

/**
 * Some simple samples to use the {@link java.lang.Comparable} and
 * {@link java.util.Comparator} interfaces. We use a series of
 * Employees that contain employee name and ID. For the Comparable, we
 * sort by EID. For Comparator, we sort by an external database that
 * contains the time and date that each employee first contacted us
 * when looking for employment. It is typical to use Comparable to
 * order a series of Objects the right way (whatever that is) and to
 * use Comparator to add external ordering to the Objects.
 *
 * I named this class CompStar because it covers the Comp* interfaces
 * and I want my filenames to indicate what is covered within them. If
 * it was a real class it would be called something like Employee.
 *
 * See http://enil.us/1r7p9sw for more info.
 **/
// We should really have a type defined for employee ID but we just
// use an Integer because I don't feel like throwing more code into a
// sample.
public class CompStar implements Comparable<CompStar> {
    private final Integer eid;	// Employee id
    private final String name;	// Employee name
    private static int highestEId = 0; // internal counter of last EId assigned
    // Database for initial email contacts for new hires
    private static FirstContactDates initialContact = new FirstContactDates();
    // List of employees
    private static List<CompStar> employees = new ArrayList<CompStar>();

    // Helper function to print an employee (ie: CompStar) List
    private static void printEmployees() {
	for (CompStar emp : employees) {
	    System.out.println(emp.getName());
	}
    }

    // Small helper function to make adding employees simpler. It adds
    // to both the employees list and the first contact date mapping.
    private static void addEmployee(String name, Date date) {
	CompStar e = new CompStar(name);
	employees.add(e);
	initialContact.put(e.getEId(), date);
    }
	
    // Constructor
    public CompStar(String name) {
	this.eid = ++highestEId;
	this.name = name;
    }

    public Integer getEId() {	// Accessor
	return eid;
    }

    public String getName() {	// Accessor
	return name;
    }

    /**
     * Sorts by EId in reverse. Doesn't really make much sense but its
     * just a test case.
    **/
    public int compareTo(CompStar other) { 
	// To get reverse order we call compareTo() on the other EId
	return other.eid.compareTo(this.eid);
    }

    public static void main(String args[]) { // Main routine to run test cases
	final long DAY_MILLISECS = 1000*60*60*24;
	Date now = new Date();
	Date tomorrow = new Date(now.getTime() + DAY_MILLISECS);
	Date yesterday = new Date(now.getTime() - DAY_MILLISECS);

	// Create list - default order is BAC
	addEmployee("Bob", now);
	addEmployee("Al", yesterday);
	addEmployee("Carole", tomorrow);
	
	// Print by default order
	System.out.println("\n==== Default (BAC)");
	printEmployees();

	// Sort by dates - order is ABC
	Collections.sort(employees, new FirstContactDates());
	System.out.println("\n==== First Contact Dates (ABC)");
	printEmployees();

	// Sort by EId - order is CAB
	Collections.sort(employees);
	System.out.println("\n==== EID (CAB)");
	printEmployees();
    }
}

