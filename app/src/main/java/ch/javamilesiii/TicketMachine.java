package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.util.ArrayList;

// Class: Manages the lifecycle of parking tickets
// SRP: Single responsibility - ticket creation, storage, and retrieval only
// Encapsulation: Private collection with controlled public access methods
public class TicketMachine {
    // Attribute: Private collection stores active tickets
    // Composition: TicketMachine HAS-A collection of Ticket objects
    // Encapsulation: Internal data structure hidden from external access
    private final ArrayList<Ticket> activeTickets = new ArrayList<>();

    // Method: Creates new tickets and adds them to collection
    // SRP: Single job - generate and store new tickets
    // Factory pattern: Creates Ticket objects for clients
    // Encapsulation: Controls how tickets are created and stored
    public Ticket generateTicket() {
        Ticket ticket = new Ticket(); // Object creation
        activeTickets.add(ticket); // Maintains collection of active tickets
        return ticket;
    }

    // Method: Removes tickets from active collection
    // SRP: Single responsibility - manage ticket removal
    // Encapsulation: Provides controlled way to modify internal collection
    public void removeTicket(Ticket ticket) {
        activeTickets.remove(ticket);
    }

    // Method: Finds tickets by their number
    // SRP: Single responsibility - ticket lookup by ID
    // Encapsulation: Hides the search implementation from clients
    // Uses functional programming with streams for data filtering
    // Exception handling: Throws custom exception for better error communication
    public Ticket getTicketFromNumber(int ticketNumber) {
        return activeTickets.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst()
                .orElseThrow(() -> new TicketNotFoundException(ticketNumber));
    }
}