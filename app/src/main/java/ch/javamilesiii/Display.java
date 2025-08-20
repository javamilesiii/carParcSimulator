package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Display {
    private int freeSpaces;
    private int totalSpaces;
    private LocalDateTime lastUpdate;

    public Display() {
        this.lastUpdate = LocalDateTime.now();
    }

    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
        this.lastUpdate = LocalDateTime.now();
    }

    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public void updateDisplay() {
        String timestamp = lastUpdate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      PARKING DISPLAY         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║  FREE SPACES: %-15d║%n", freeSpaces);
        System.out.printf("║  LAST UPDATE: %-15s║%n", timestamp);
        System.out.println("╚══════════════════════════════╝");
    }

    public void showWelcomeMessage() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║       WELCOME TO OUR         ║");
        System.out.println("║      PARKING GARAGE!         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║  AVAILABLE: %-17d║%n", freeSpaces);
        System.out.printf("║  RATE: %.2f CHF/min          ║%n", 1.5f);
        System.out.println("╚══════════════════════════════╝");
    }

    public void showFullMessage() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         GARAGE FULL          ║");
        System.out.println("║     PLEASE COME BACK         ║");
        System.out.println("║         LATER                ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public int getFreeSpaces() {
        return freeSpaces;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
