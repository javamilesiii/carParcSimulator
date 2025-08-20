package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.util.Arrays;
import java.util.stream.IntStream;

// Class: Main coordinator of the parking system
// SRP: Single responsibility - coordinating the entire parking garage system
// Composition: CarParc HAS-A many other objects (not inheritance)
public class CarParc {
    // Attributes: Object state stored in private fields
    // Composition: CarParc contains multiple other objects (HAS-A relationships)
    // Encapsulation: All fields are private - data hiding principle
    private final Barrier barrier;
    private final TicketMachine ticketMachine;
    private final ExitBarrier exitBarrier1;
    private final ExitBarrier exitBarrier2;
    private final Floor[] floors;
    private final int spacesPerFloor;
    private final float pricePerMinute;
    private final Display mainDisplay;

    // Constructor: Creates and initializes the complete parking system
    // DIP: Constructor receives configuration from outside instead of hardcoding values
    // Composition: Creates and connects all system components
    public CarParc(int floorCount, int spacesPerFloor, float pricePerMinute) {
        this.spacesPerFloor = spacesPerFloor;
        this.pricePerMinute = pricePerMinute;

        // Object creation and composition - building the system from parts
        this.ticketMachine = new TicketMachine();
        this.barrier = new Barrier("Entrance Barrier");

        // DIP: Injecting TicketMachine dependency into ExitBarriers
        this.exitBarrier1 = new ExitBarrier("Exit Barrier 1", ticketMachine);
        this.exitBarrier2 = new ExitBarrier("Exit Barrier 2", ticketMachine);
        this.floors = new Floor[floorCount];
        this.mainDisplay = new Display();

        // Composition: Each floor gets shared dependencies (ticketMachine)
        IntStream.range(0, floorCount).forEach(i -> this.floors[i] = new Floor(spacesPerFloor, ticketMachine, this));

        updateDisplay();
    }

    // Methods: Provide controlled access to private attributes
    // Encapsulation: Getter methods allow read access without exposing internal structure
    public TicketMachine getTicketMachine() {
        return ticketMachine;
    }

    public float getPricePerMinute() {
        return pricePerMinute;
    }

    public Barrier getBarrier() {
        return barrier;
    }

    public ExitBarrier getExitBarrier1() {
        return exitBarrier1;
    }

    public ExitBarrier getExitBarrier2() {
        return exitBarrier2;
    }

    public int getFloorCount() {
        return floors.length;
    }

    // Method: Calculates total parking capacity
    // Abstraction: Simple method hides calculation details
    // SRP: Single job - calculate total spaces
    public int getTotalSpaces() {
        return floors.length * spacesPerFloor;
    }

    // Method: Calculates current free spaces across all floors
    // Abstraction: Complex calculation hidden behind simple interface
    // Uses streams for data processing
    public int getFreeSpaces() {
        return Arrays.stream(floors).mapToInt(Floor::calcFreeSpaces).sum();
    }

    public Floor getFloor(int floorNumber) {
        return floors[floorNumber];
    }

    public Display getMainDisplay() {
        return mainDisplay;
    }

    // Method: Updates the display with current information
    // SRP: Single responsibility - coordinate display updates
    // Encapsulation: Uses public interfaces of other objects
    public void updateDisplay() {
        int freeSpaces = getFreeSpaces();
        mainDisplay.setFreeSpaces(freeSpaces);
        mainDisplay.updateDisplay();
    }

    // Method: Shows detailed status of all floors
    // SRP: Single responsibility - display system status
    // Abstraction: Hides formatting complexity
    public void showFloorStatus() {
        System.out.println("\n=== PARKING GARAGE STATUS ===");
        for (int i = 0; i < floors.length; i++) {
            int floorFreeSpaces = floors[i].calcFreeSpaces();
            System.out.printf("Floor %d: %d/%d spaces free\n",
                    i + 1, floorFreeSpaces, spacesPerFloor);
        }
        System.out.printf("Total: %d/%d spaces free\n", getFreeSpaces(), getTotalSpaces());
        System.out.println("============================\n");
    }
}