package parkhausSimulator.app.src.main.java.ch.javamilesiii;

// Class: Represents a single parking space
// SRP: Single responsibility - tracking parking spot state (occupied/free)
// Encapsulation: Private state with controlled public access
public class ParkingSpot {
    // Attribute: Simple boolean state representing spot availability
    // Encapsulation: Private field protects state from direct modification
    private boolean occupied;

    // Method: Provides read access to spot status
    // Encapsulation: Controlled read access to private state
    // Boolean getter convention: uses 'is' prefix instead of 'get'
    public boolean isOccupied() {
        return occupied;
    }

    // Method: Allows controlled modification of spot status
    // Encapsulation: Only way to change private occupied field
    // SRP: Single job - update occupation status
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
