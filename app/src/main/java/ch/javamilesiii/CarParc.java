package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CarParc {
    private Barrier barrier;
    private TicketMachine ticketMachine;
    private ExitBarrier exitBarrier1;
    private ExitBarrier exitBarrier2;
    private Floor[] floors;
    private int spacesPerFloor;
    private float pricePerMinute;
    private Display mainDisplay;

    public CarParc(int floorCount, int spacesPerFloor, float pricePerMinute) {
        this.spacesPerFloor = spacesPerFloor;
        this.pricePerMinute = pricePerMinute;
        this.ticketMachine = new TicketMachine();
        this.barrier = new Barrier("Entrance Barrier");
        this.exitBarrier1 = new ExitBarrier("Exit Barrier 1", ticketMachine);
        this.exitBarrier2 = new ExitBarrier("Exit Barrier 2", ticketMachine);
        this.floors = new Floor[floorCount];
        this.mainDisplay = new Display();

        IntStream.range(0, floorCount).forEach(i -> {
            this.floors[i] = new Floor(spacesPerFloor, ticketMachine, this);
        });

        updateDisplay();
    }

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

    public int getTotalSpaces() {
        return floors.length * spacesPerFloor;
    }

    public int getFreeSpaces() {
        return Arrays.stream(floors).mapToInt(Floor::calcFreeSpaces).sum();
    }

    public Floor getFloor(int floorNumber) {
        return floors[floorNumber];
    }

    public Display getMainDisplay() {
        return mainDisplay;
    }

    public void updateDisplay() {
        int freeSpaces = getFreeSpaces();
        mainDisplay.setFreeSpaces(freeSpaces);
        mainDisplay.updateDisplay();
    }

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