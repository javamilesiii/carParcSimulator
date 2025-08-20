package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;

// Class: Represents a parking ticket in the real world (abstraction)
// SRP: Single responsibility - managing ticket data and basic operations
// Encapsulation: Private attributes with controlled access through methods
public class Ticket {
    // Attributes: Variables that store object state
    // Encapsulation: Private fields protect data from direct external access
    private final int ticketNumber;
    private final LocalDateTime purchaseTime;
    private boolean isPaid;

    // Constructor: Creates and initializes ticket objects
    // Object creation: Each new Ticket() creates a unique object instance
    // Identity: Each ticket has unique ticketNumber (object identity principle)
    public Ticket() {
        this.purchaseTime = LocalDateTime.now(); // State: Captures creation time
        this.ticketNumber = generateTicketNumber(); // Behavior: Calls internal method
        this.isPaid = false; // Default state
    }

    // Method: Private helper method - implementation detail
    // Encapsulation: Private method hides ticket number generation logic
    // SRP: Single job - generate unique ticket numbers
    private int generateTicketNumber(){
        return (int) (Math.random() * 1000000);
    }

    // Methods: Provide controlled access to object attributes
    // Encapsulation: Public getter methods allow read access to private data

    // Immutable access: No setter for ticketNumber - value cannot be changed after creation
    public int getTicketNumber() {
        return ticketNumber;
    }

    // Immutable access: No setter for purchaseTime - timestamp is fixed at creation
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    // Read access to payment status
    public boolean isPaid() {
        return isPaid;
    }

    // Method: Allows controlled modification of payment status
    // Encapsulation: Only payment status can be changed, other data stays protected
    // This is the only mutable behavior - represents state change in ticket lifecycle
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}