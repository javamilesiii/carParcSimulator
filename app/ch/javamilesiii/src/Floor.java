package parkhausSimulator.app.ch.javamilesiii.src;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Floor {
    private ParkingSpot[] parkingSpots;
    private CashDesk cashDesk;

    public Floor(int floorNumber, TicketMachine ticketMachine, CarParc carParc) {
        this.parkingSpots = new ParkingSpot[50];
        IntStream.range(0, floorNumber).forEach(i -> {
            this.parkingSpots[i] = new ParkingSpot();
        });

        this.cashDesk = new CashDesk(ticketMachine, carParc);
    }

    public CashDesk getCashDesk() {
        return cashDesk;
    }

    public ParkingSpot[] getParkingSpots() {
        return parkingSpots;
    }

    public int calcFreeSpaces() {
        return (int) Arrays.stream(parkingSpots).filter(parkingSpot -> !parkingSpot.isOccupied()).count();
    }

    public void setParkingSpotOccupied(int spotNumber) {
        parkingSpots[spotNumber].setOccupied(true);
    }

    public void setParkingSpotFree(int spotNumber) {
        parkingSpots[spotNumber].setOccupied(false);
    }
}
