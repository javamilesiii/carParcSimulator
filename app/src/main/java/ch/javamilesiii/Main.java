package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int floors = 5;
        int parkingSpots = 50;
        float pricePerMinute = 1.5f;
        CarParc carParc = new CarParc(floors, parkingSpots, pricePerMinute);

        carParc.getMainDisplay().setTotalSpaces(carParc.getTotalSpaces());
        carParc.getMainDisplay().showWelcomeMessage();

        IOHandler handler = new IOHandler();
        handler.startSimulation(carParc);
    }
}