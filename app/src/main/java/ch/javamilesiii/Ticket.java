package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketNumber;
    private LocalDateTime purchaseTime;
    private boolean isPaid;

    public Ticket() {
        this.purchaseTime = LocalDateTime.now();
        this.ticketNumber = generateTicketNumber();
        this.isPaid = false;
    }

    private int generateTicketNumber(){
        return (int) (Math.random() * 1000000);
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}