package parkhausSimulator.app.src.main.java.ch.javamilesiii;


// SRP: Main class has single responsibility - starting the application
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Configuration data - could be moved to separate config class for better SRP
        int floors = 5;
        int parkingSpots = 50;
        float pricePerMinute = 1.5f;

        // Object creation - CarParc is an instance of the CarParc class
        // DIP: Main depends on CarParc abstraction, receives configuration from outside
        CarParc carParc = new CarParc(floors, parkingSpots, pricePerMinute);

        // Method calls on objects - demonstrates object behavior
        carParc.getMainDisplay().setTotalSpaces(carParc.getTotalSpaces());
        carParc.getMainDisplay().showWelcomeMessage();

        // Composition: Main creates and uses IOHandler object
        IOHandler handler = new IOHandler();
        // Delegation: Hand over control to IOHandler
        handler.startSimulation(carParc);
    }
}