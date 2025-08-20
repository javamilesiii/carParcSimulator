package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.util.Arrays;
import java.util.stream.IntStream;

// Class: Represents one floor of the parking garage
// SRP: Single responsibility - managing parking spots and services for one floor
// Composition: Floor HAS-A collection of ParkingSpots and HAS-A CashDesk
public class Floor {
    // Attributes: Floor's internal components
    // Composition: Floor contains (HAS-A) an array of ParkingSpot objects
    // Encapsulation: Private fields protect internal structure
    private final ParkingSpot[] parkingSpots;
    private final CashDesk cashDesk;
    private final CarParc carParc; // Reference back to main system for coordination

    // Constructor: Creates floor with all its components
    // DIP: Receives dependencies (TicketMachine, CarParc) from outside
    // Composition: Creates and owns ParkingSpot objects
    public Floor(int spacesPerFloor, TicketMachine ticketMachine, CarParc carParc) {
        this.carParc = carParc;

        // Object creation: Initialize array of ParkingSpot objects
        // Composition: Each Floor owns its ParkingSpots
        this.parkingSpots = new ParkingSpot[spacesPerFloor];
        IntStream.range(0, spacesPerFloor).forEach(i -> {
            this.parkingSpots[i] = new ParkingSpot(); // Create individual parking spots
        });

        // Composition: Floor creates and owns a CashDesk
        // DIP: CashDesk receives its dependencies from Floor
        this.cashDesk = new CashDesk(ticketMachine, carParc);
    }

    // Methods: Provide controlled access to floor components
    // Encapsulation: Getter methods control access to private objects
    public CashDesk getCashDesk() {
        return cashDesk;
    }

    public ParkingSpot[] getParkingSpots() {
        return parkingSpots;
    }

    // Method: Calculates how many parking spots are free
    // SRP: Single job - count available spaces
    // Abstraction: Hides complex counting logic behind simple method call
    // Uses functional programming with streams
    public int calcFreeSpaces() {
        return (int) Arrays.stream(parkingSpots)
                .filter(parkingSpot -> parkingSpot != null && !parkingSpot.isOccupied())
                .count();
    }

    // Method: Marks a parking spot as occupied
    // SRP: Single responsibility - update spot status
    // Includes bounds checking for safety
    // Side effect: Updates main display through carParc reference
    public void setParkingSpotOccupied(int spotNumber) {
        if (spotNumber >= 0 && spotNumber < parkingSpots.length) {
            parkingSpots[spotNumber].setOccupied(true);
            carParc.updateDisplay(); // Notify system of change
        }
    }

    // Method: Marks a parking spot as free
    // SRP: Single responsibility - free up parking spot
    // Includes validation to prevent array access errors
    public void setParkingSpotFree(int spotNumber) {
        if (spotNumber >= 0 && spotNumber < parkingSpots.length) {
            parkingSpots[spotNumber].setOccupied(false);
            carParc.updateDisplay(); // Update system display
        }
    }
}