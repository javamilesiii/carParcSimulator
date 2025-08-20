package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class ExitBarrier extends Barrier{
    private final TicketMachine ticketMachine;

    public ExitBarrier(String name, TicketMachine ticketMachine) {
        super(name);
        this.ticketMachine = ticketMachine;
    }

    public boolean checkTicket(int number){
        return ticketMachine.getTicketFromNumber(number).isPaid();
    }
}
