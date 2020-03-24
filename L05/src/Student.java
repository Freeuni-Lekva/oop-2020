// Student.java
/*
 Demonstrates the most basic features of a class.

 A student is defined by their current number of units.
 There are standard get/set accessors for units.

 The student responds to getStress() to report
 their current stress level which is a function
 of their units.
*/
public class Student extends Object {
    // NOTE this is an "instance variable" named "units"
    // Every Student object will have its own units variable.
    // "protected" and "private" mean that clients do not get access
    protected int units;


    /* NOTE
     "public static final" declares a public readable constant that
     is associated with the class -- it's full name is Student.MAX_UNITS.
     It's a convention to put constants like that in upper case.
    */
    public static final int MAX_UNITS = 20;
    public static final int DEFAULT_UNITS = 15;


    // Constructs a student with the given units.
    public Student(int initUnits) {
        units = initUnits;

        // NOTE this is example of "Receiver Relative" coding --
        // "units" refers to the ivar of the receiver object.
        // OOP code is written relative to an implicitly present receiver.
    }

    // Default constructor (no arguments).
    // Inits with a default value of 15 units.
    public Student() {
        // "this" here is a special syntax to call one constructor
        // from another -- it must be the first line.
        this(DEFAULT_UNITS);
    }

    // Gets the current units value (standard "getter" accessor).
    public int getUnits() {
        return units;
    }

    // Sets the units, unless the new value would fall outside
    // the range 0..MAX_UNITS.
    public void setUnits(int units) {
        if ((units < 0) || (units > MAX_UNITS)) {
            return;
            // Could use a number of strategies here: throw an
            // exception, print to stderr, return false
        }
        this.units = units;
        // NOTE: "this.units" trick needed here since param and ivar
        // are both called "units". Perhaps "newUnits" would be a better
        // name, but we wanted  to show the this.unit syntax.
    }


    /*
     Gets the current stress of the student
     (defined as units *10).
     NOTE another example of "Receiver Relative" coding
    */
    public int getStress() {
        return (units * 10);
    }


    /*
     Tries to drop the given number of units.
     Does not drop if would go below 9 units.
     Returns true if the drop succeeds.
    */
    public boolean dropUnits(int drop) {
        if (units - drop >= 9) {
            setUnits(units - drop);        // NOTE send self a message
            return true;
        }
        return false;
    }

    // standard Hashcode auto-generated by Eclipse using the "source" menu
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + units;
        return result;
    }

    // standard Equals auto-generated by Eclipse using the "source" menu
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return units == other.units;
    }

    /*
     In main() we have some typical looking client-of-Student code.
     NOTE Invoking "java Student" from the command line runs this.
     It's handy to put test/demo/sample client code in the main() of a class.
    */
    public static void main(String[] args) {
        // Make two students
        Student a = new Student(12);    // new 12 unit student
        Student b = new Student();        // new 15 unit student (default ctor)

        // They respond to getUnits() and getStress()
        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());
        System.out.println("b u:" + b.getUnits() + " s:" + b.getStress());

        System.out.println("a drops 3 units");
        a.dropUnits(3);    // a drops a class
        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());

        // Now "b" points to the same object as "a" (pointer copy)
        b = a;
        b.setUnits(10);
        System.out.println("b = a, b.setUnits(10)");

        // So the "a" units have been changed
        System.out.println("a u:" + a.getUnits() + " s:" + a.getStress());

        // NOTE: public vs. private
        // A statement like "b.units = 10;" will not compile in a client
        // of the Student class when units is declared protected or private


		/*
         OUTPUT...
			a u:12 s:120
			b u:15 s:150
			a drop 3 units
			a u:9 s:90
			b = a, b.setUnits(10)
			a u:10 s:100
		*/
    }
}

/*
 Things to notice...

 -Demonstrates the Object-lifecycle -- clients create the object with new
 (must go through constructor), then send it messages. Hard for the client
 to mess up the state of the object. Note how setUnits() can maintain the
 internal correctness of the object.

 -The implementation code can refer to instance variables like "units"
 by name. It automatically binds to the ivar of the receiver.

 -"units" is declared protected. Thereore, a client cannot write something like
 "a.units++;". The client must go through public messages like setUnits().
 This promotes a less fragile design. The client may access things declared
 "public".

 -State vs. Computation -- notice that the client can't really tell if stress is
 stored or computed. It just appears to be a property that Students have. Whether
 it is stored or computed is just a detail. This is a nice separation between
 the abstraction exposed by client and how it is actually implemented.
*/