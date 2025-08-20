package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Floor {
    private ParkingSpot[] parkingSpots;
    private CashDesk cashDesk;
    private CarParc carParc;

    public Floor(int spacesPerFloor, TicketMachine ticketMachine, CarParc carParc) {
        this.carParc = carParc;
        this.parkingSpots = new ParkingSpot[spacesPerFloor];
        IntStream.range(0, spacesPerFloor).forEach(i -> {
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
        return (int) Arrays.stream(parkingSpots)
                .filter(parkingSpot -> parkingSpot != null && !parkingSpot.isOccupied())
                .count();
    }

    public void setParkingSpotOccupied(int spotNumber) {
        if (spotNumber >= 0 && spotNumber < parkingSpots.length) {
            parkingSpots[spotNumber].setOccupied(true);
            carParc.updateDisplay();
        }
    }

    public void setParkingSpotFree(int spotNumber) {
        if (spotNumber >= 0 && spotNumber < parkingSpots.length) {
            parkingSpots[spotNumber].setOccupied(false);
            carParc.updateDisplay();
        }
    }
}