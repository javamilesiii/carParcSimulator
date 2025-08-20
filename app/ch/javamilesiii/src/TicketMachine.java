package parkhausSimulator.app.ch.javamilesiii.src;

import java.util.ArrayList;

public class TicketMachine {
    private final ArrayList<Ticket> activeTickets = new ArrayList<>();

    public Ticket generateTicket() {
        Ticket ticket = new Ticket();
        activeTickets.add(ticket);
        return ticket;
    }
    public void removeTicket(Ticket ticket) {
        activeTickets.remove(ticket);
    }
    public Ticket getTicketFromNumber(int ticketNumber) {
        return activeTickets.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst()
                .orElseThrow(() -> new TicketNotFoundException(ticketNumber));
    }
}
