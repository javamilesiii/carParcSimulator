package parkhausSimulator.app.src.main.java.ch.javamilesiii;

// Class: Blueprint for creating barrier objects
// SRP: Single responsibility - managing barrier operations only
// Encapsulation: Hides internal data and provides controlled access through methods
public class Barrier {
    // Attribute: Variable that stores object state (barrier name)
    // Encapsulation: Private attribute - data hiding from outside access
    private final String barrierName;

    // Constructor: Special method for creating and initializing objects
    // Encapsulation: Controlled way to set initial object state
    public Barrier(String name) {
        this.barrierName = name;
    }

    // Method: Function that defines object behavior
    // Abstraction: Hides complex timing logic behind simple method call
    // SRP: Single job - coordinate complete barrier passage
    public void passThroughBarrier() throws InterruptedException {
        this.openBarrier();
        System.out.println("...");
        Thread.sleep(2000); // Simulates real barrier timing
        this.closeBarrier();
    }

    // Method: Defines specific behavior for opening barrier
    // OCP: This method can be overridden in subclasses without changing this class
    // Encapsulation: Public interface for controlled access to functionality
    public void openBarrier() {
        System.out.println(this.barrierName + " is now open.");
    }

    // Method: Defines specific behavior for closing barrier
    // OCP: Can be extended in subclasses for different barrier types
    public void closeBarrier() {
        System.out.println(this.barrierName + " is now closed.");
    }
}