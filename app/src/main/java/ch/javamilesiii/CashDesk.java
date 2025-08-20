package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.Duration;
import java.time.LocalDateTime;

// Class: Handles payment processing for parking tickets
// SRP: Single responsibility - calculating fees and processing payments only
// DIP: Depends on abstractions (TicketMachine, CarParc) not concrete implementations
public class CashDesk {
    // Attributes: Dependencies injected from outside
    // DIP: High-level CashDesk depends on abstractions, not low-level details
    // Encapsulation: Private fields protect internal dependencies
    private final TicketMachine ticketMachine;
    private final CarParc carParc;

    // Constructor: Receives dependencies from outside (dependency injection)
    // DIP: Constructor injection - doesn't create its own dependencies
    // SRP: Single job - setup dependencies
    public CashDesk(TicketMachine ticketMachine, CarParc carParc) {
        this.ticketMachine = ticketMachine;
        this.carParc = carParc;
    }

    // Method: Calculates parking price based on time
    // SRP: Single responsibility - price calculation
    // Pure function: Same inputs always produce same outputs
    // Uses modern Java Time API for robust date/time handling
    public float calcParkingPrice(Ticket ticket, LocalDateTime currentTime) {
        long minutes = Duration.between(ticket.getPurchaseTime(), currentTime).toMinutes();
        return minutes * carParc.getPricePerMinute();
    }

    // Method: Processes ticket payment
    // SRP: Single job - mark ticket as paid
    // Encapsulation: Uses public interface of TicketMachine
    public void payTicket(int ticketNumber) {
        ticketMachine.getTicketFromNumber(ticketNumber).setPaid(true);
    }
}