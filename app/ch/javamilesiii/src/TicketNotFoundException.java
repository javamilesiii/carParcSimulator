package parkhausSimulator.app.ch.javamilesiii.src;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(int ticketNumber) {
        super("Couldn't find parking ticket with id: " + ticketNumber);
    }
}
