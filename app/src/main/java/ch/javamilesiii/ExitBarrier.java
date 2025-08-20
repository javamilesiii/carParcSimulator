package parkhausSimulator.app.src.main.java.ch.javamilesiii;

// Inheritance: ExitBarrier IS-A Barrier (extends keyword creates parent-child relationship)
// LSP: ExitBarrier can be used anywhere Barrier is expected without breaking functionality
// OCP: Extends Barrier functionality without modifying the original Barrier class
public class ExitBarrier extends Barrier{

    // Attribute: Stores reference to TicketMachine object
    // Composition: ExitBarrier HAS-A TicketMachine (contains another object)
    // DIP: Depends on TicketMachine abstraction, not concrete implementation
    private final TicketMachine ticketMachine;

    // Constructor: Calls parent constructor and initializes own attributes
    // Inheritance: super() calls the parent class constructor
    // DIP: Receives dependency from outside instead of creating it internally
    public ExitBarrier(String name, TicketMachine ticketMachine) {
        super(name); // Inheritance: Delegate to parent constructor
        this.ticketMachine = ticketMachine; // Dependency injection
    }

    // Method: Adds new behavior specific to exit barriers
    // SRP: Single responsibility - validate if ticket is paid
    // Encapsulation: Uses private ticketMachine through its public interface
    public boolean checkTicket(int number){
        return ticketMachine.getTicketFromNumber(number).isPaid();
    }
}