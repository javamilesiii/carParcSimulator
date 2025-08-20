package parkhausSimulator;

import java.time.Duration;
import java.time.LocalDateTime;

public class CashDesk {
    private final TicketMachine ticketMachine;
    private final CarParc carParc;
    public CashDesk(TicketMachine ticketMachine, CarParc carParc) {
        this.ticketMachine = ticketMachine;
        this.carParc = carParc;
    }

    public float calcParkingPrice(Ticket ticket, LocalDateTime currentTime) {
        long minutes = Duration.between(ticket.getPurchaseTime(), currentTime).toMinutes();
        return minutes * carParc.getPricePerMinute();
    }

    public void payTicket(int ticketNumber) {
        ticketMachine.getTicketFromNumber(ticketNumber).setPaid(true);
    }
}
