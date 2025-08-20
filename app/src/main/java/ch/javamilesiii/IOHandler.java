package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.IntStream;

// Class: Handles all user interaction and interface operations
// SRP: Single responsibility - managing user input/output only
// Separation of concerns: UI logic separated from business logic
public class IOHandler {

    // Method: Main simulation control loop
    // SRP: Single job - coordinate user interaction flow
    // DIP: Receives CarParc from outside instead of creating it internally
    // Resource management: try-with-resources ensures Scanner is properly closed
    public void startSimulation(CarParc carParc) throws InterruptedException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Parkhaus Simulator" +
                    "\n-----------------------------------");
            carParc.showFloorStatus();

            // Main interaction loop - keeps running until user exits
            while (true) {
                System.out.println("""
                        What do you want to do?\
                        
                        1. Enter the parking garage\
                        
                        2. Pay your ticket\
                        
                        3. Exit the parking garage\
                        
                        4. Show current status\
                        
                        5. Exit the simulation""");
                int answer = scanner.nextInt();

                // Delegation: Each case delegates to specialized private methods
                // SRP: Each case handles one specific user action
                switch (answer) {
                    case 1:
                        enterParkingGarage(carParc, scanner);
                        break;
                    case 2:
                        payTicket(carParc, scanner);
                        break;
                    case 3:
                        exitParkingGarage(carParc, scanner);
                        break;
                    case 4:
                        carParc.showFloorStatus(); // Delegation to CarParc
                        break;
                    case 5:
                        return; // Exit simulation
                }
            }
        }
    }

    // Method: Handles parking garage entry process
    // SRP: Single responsibility - manage entry workflow
    // Encapsulation: Private method - internal implementation detail
    private void enterParkingGarage(CarParc carParc, Scanner scanner) throws InterruptedException {
        // Validation: Check if garage has available space
        if (carParc.getFreeSpaces() == 0) {
            System.out.println("Sorry, the parking garage is full!");
            return;
        }

        // Delegation: Let barrier handle its own operation
        carParc.getBarrier().passThroughBarrier();
        printTicket(carParc); // Generate and display ticket
        carParc.showFloorStatus(); // Show current status

        System.out.printf("Which floor do you want to enter? (1-%d)\n", carParc.getFloorCount());
        int floor = scanner.nextInt();

        // Validation: Check if selected floor has available spaces
        if (carParc.getFloor(floor - 1).calcFreeSpaces() == 0) {
            System.out.println("Sorry, this floor is full! Please choose another floor.");
            return;
        }

        System.out.println("\n\nWhich spot do you want to park?\n");
        printFloor(carParc.getFloor(floor - 1)); // Show floor layout
        int spot = scanner.nextInt();

        // Validation: Check if selected spot is available
        if (carParc.getFloor(floor-1).getParkingSpots()[spot-1].isOccupied()) {
            System.out.println("This spot is already occupied! Please choose another spot.");
            return;
        }

        // Delegation: Let floor handle parking spot management
        carParc.getFloor(floor-1).setParkingSpotOccupied(spot-1);
        System.out.println("\n\nYou have successfully parked your car.");
    }

    // Method: Handles parking garage exit process
    // SRP: Single responsibility - manage exit workflow
    // Complex business logic with multiple validation steps
    private void exitParkingGarage(CarParc carparc, Scanner scanner) throws InterruptedException {
        System.out.println("\n\nIn which floor is your car parked?\n");
        int floor = scanner.nextInt();
        System.out.println("\n\nWhich spot is your car parked in?\n");
        printFloor(carparc.getFloor(floor-1));
        int spot = scanner.nextInt();

        // Validation: Verify the spot is actually occupied
        if (!carparc.getFloor(floor-1).getParkingSpots()[spot-1].isOccupied()) {
            System.out.println("The selected parking spot is not occupied. Please check your input.");
            return;
        }

        System.out.println("""
                
                
                Which Exit do you want to take?
                1. Exit Barrier 1
                2. Exit Barrier 2
                """);
        int exit = scanner.nextInt();
        System.out.println("\n\nPlease enter your ticket number:\n");
        int ticketNumber = scanner.nextInt();

        // Exception handling: Graceful handling of invalid tickets
        try {
            Ticket ticket = carparc.getTicketMachine().getTicketFromNumber(ticketNumber);

            // Delegation: Let appropriate exit barrier handle validation and opening
            switch (exit) {
                case 1:
                    if (carparc.getExitBarrier1().checkTicket(ticketNumber)){
                        carparc.getExitBarrier1().passThroughBarrier();
                        carparc.getFloor(floor-1).setParkingSpotFree(spot-1);
                        carparc.getTicketMachine().removeTicket(ticket);
                        System.out.println("Thank you for using our parking garage!");
                    } else {
                        System.out.println("Ticket not paid. Please pay your ticket first.");
                    }
                    break;
                case 2:
                    if (carparc.getExitBarrier2().checkTicket(ticketNumber)){
                        carparc.getExitBarrier2().passThroughBarrier();
                        carparc.getFloor(floor-1).setParkingSpotFree(spot-1);
                        carparc.getTicketMachine().removeTicket(ticket);
                        System.out.println("Thank you for using our parking garage!");
                    } else {
                        System.out.println("Ticket not paid. Please pay your ticket first.");
                    }
                    break;
                default:
                    System.out.println("Invalid exit number. Please try again.");
            }
        } catch (TicketNotFoundException e) {
            // Polymorphism: TicketNotFoundException IS-A RuntimeException
            System.out.println("Invalid ticket number. Please try again.");
        }
    }

    // Method: Handles ticket payment process
    // SRP: Single responsibility - process payments
    // Delegation: Uses CashDesk for price calculation and payment processing
    private void payTicket(CarParc carParc, Scanner scanner) {
        System.out.println("\n\nPlease enter your ticket number:\n");
        int ticketNumber = scanner.nextInt();

        // Exception handling: Handle invalid ticket numbers gracefully
        try {
            Ticket ticket = carParc.getTicketMachine().getTicketFromNumber(ticketNumber);
            LocalDateTime currentTime = LocalDateTime.now();

            // Delegation: Let CashDesk calculate the price
            float price = carParc.getFloor(0).getCashDesk().calcParkingPrice(ticket, currentTime);

            System.out.printf("Parking fee: %.2f CHF\n", price);
            System.out.println("Do you want to pay? (1=Yes, 2=No)");
            int payChoice = scanner.nextInt();

            if (payChoice == 1) {
                // Delegation: Let CashDesk handle payment processing
                carParc.getFloor(0).getCashDesk().payTicket(ticketNumber);
                System.out.println("Payment successful! You can now exit the parking garage.");
            } else {
                System.out.println("Payment cancelled.");
            }
        } catch (TicketNotFoundException e) {
            System.out.println("Invalid ticket number. Please try again.");
        }
    }

    // Method: Displays floor layout to user
    // SRP: Single job - format and display floor layout
    // Abstraction: Hides complex formatting logic behind simple method call
    public void printFloor(Floor floor){
        ParkingSpot[] spaces = floor.getParkingSpots();

        // Uses functional programming with streams for cleaner code
        IntStream.range(0, spaces.length).forEach(i -> {
            if (spaces[i] != null && spaces[i].isOccupied()) {
                System.out.print("---  "); // Show occupied spots as dashes
            } else {
                System.out.printf("%03d  ", i + 1); // Show available spot numbers
            }

            // Format output: New line every 10 spots for readability
            if ((i + 1) % 10 == 0 && i != spaces.length - 1) {
                System.out.println();
            }
        });

        System.out.println();
    }

    // Method: Generates and displays parking ticket
    // SRP: Single responsibility - ticket creation and display
    // Delegation: Uses TicketMachine to create ticket, then formats output
    public void printTicket(CarParc carParc){
        // Delegation: Let TicketMachine handle ticket creation
        Ticket ticket = carParc.getTicketMachine().generateTicket();
        int ticketNumber = ticket.getTicketNumber();
        String ticketTime = ticket.getPurchaseTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // Abstraction: Simple ticket display format hiding formatting complexity
        System.out.println("########################" +
                "\n#  Ticket Information  #" +
                "\n#----------------------#" +
                "\n#      Nr. "+ticketNumber+"      #" +
                "\n# "+ticketTime+"  #" +
                "\n########################");
    }
}