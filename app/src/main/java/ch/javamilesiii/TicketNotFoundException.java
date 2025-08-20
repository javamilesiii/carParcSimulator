package parkhausSimulator.app.src.main.java.ch.javamilesiii;

// Class: Custom exception for ticket not found errors
// Inheritance: IS-A RuntimeException (extends creates parent-child relationship)
// LSP: Can be used anywhere RuntimeException is expected
// SRP: Single responsibility - representing this specific error condition
public class TicketNotFoundException extends RuntimeException {
    // Constructor: Creates meaningful error message with context
    // Inheritance: Calls parent constructor using super()
    // Good practice: Includes relevant information (ticket number) in error message
    public TicketNotFoundException(int ticketNumber) {
        super("Couldn't find parking ticket with id: " + ticketNumber);
    }
}
