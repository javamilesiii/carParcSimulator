package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Class: Handles displaying parking information to users
// SRP: Single responsibility - showing parking status information only
// Encapsulation: Manages its own display data and formatting
public class Display {
    // Attributes: Display state data
    // Encapsulation: Private fields for display information
    private int freeSpaces;
    private int totalSpaces;
    private LocalDateTime lastUpdate;

    // Constructor: Initializes display with current time
    public Display() {
        this.lastUpdate = LocalDateTime.now();
    }

    // Methods: Control display data and output
    // Encapsulation: Controlled way to update display information
    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
        this.lastUpdate = LocalDateTime.now(); // Update timestamp when data changes
    }

    // Method: Shows current parking status
    // SRP: Single job - display current information
    // Abstraction: Hides formatting complexity behind simple method call
    public void updateDisplay() {
        String timestamp = lastUpdate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      PARKING DISPLAY         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║  FREE SPACES: %-15d║%n", freeSpaces);
        System.out.printf("║  LAST UPDATE: %-15s║%n", timestamp);
        System.out.println("╚══════════════════════════════╝");
    }

    // Method: Shows welcome message for new visitors
    // SRP: Single purpose - display welcome information
    public void showWelcomeMessage() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║       WELCOME TO OUR         ║");
        System.out.println("║      PARKING GARAGE!         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║  AVAILABLE: %-17d║%n", freeSpaces);
        System.out.printf("║  RATE: %.2f CHF/min          ║%n", 1.5f);
        System.out.println("╚══════════════════════════════╝");
    }

    // Method: Shows message when garage is full
    // SRP: Single purpose - show full garage message
    public void showFullMessage() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         GARAGE FULL          ║");
        System.out.println("║     PLEASE COME BACK         ║");
        System.out.println("║         LATER                ║");
        System.out.println("╚══════════════════════════════╝");
    }

    // Methods: Provide read access to display data
    // Encapsulation: Controlled access to internal state
    public int getFreeSpaces() {
        return freeSpaces;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }
}
