package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(int ticketNumber) {
        super("Couldn't find parking ticket with id: " + ticketNumber);
    }
}
