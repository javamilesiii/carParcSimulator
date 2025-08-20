package parkhausSimulator.app.ch.javamilesiii.src;

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

    public CarParc(int floorCount, int spacesPerFloor, float pricePerMinute) {
        this.floors = new Floor[floorCount];
        this.ticketMachine = new TicketMachine();
        this.barrier = new Barrier("Entrance Barrier");
        this.exitBarrier1 = new ExitBarrier("Exit Barrier 1", ticketMachine);
        this.exitBarrier2 = new ExitBarrier("Exit Barrier 2", ticketMachine);
        this.spacesPerFloor = spacesPerFloor;
        this.pricePerMinute = pricePerMinute;
        IntStream.range(0, floorCount).forEach(i -> {
            this.floors[i] = new Floor(spacesPerFloor, new TicketMachine(), this);
        });
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
}
