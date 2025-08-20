package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IOHandler {

    public void startSimulation(CarParc carParc) throws InterruptedException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Parkhaus Simulator" +
                    "\n-----------------------------------");
            carParc.showFloorStatus();

            while (true) {
                System.out.println("What do you want to do?" +
                        "\n1. Enter the parking garage" +
                        "\n2. Pay your ticket" +
                        "\n3. Exit the parking garage" +
                        "\n4. Show current status" +
                        "\n5. Exit the simulation");
                int answer = scanner.nextInt();

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
                        carParc.showFloorStatus();
                        break;
                    case 5:
                        return;
                }
            }
        }
    }

    private void enterParkingGarage(CarParc carParc, Scanner scanner) throws InterruptedException {
        if (carParc.getFreeSpaces() == 0) {
            System.out.println("Sorry, the parking garage is full!");
            return;
        }

        carParc.getBarrier().passThroughBarrier();
        carParc.showFloorStatus();

        System.out.printf("Which floor do you want to enter? (1-%d)", carParc.getFloorCount());
        int floor = scanner.nextInt();

        if (carParc.getFloor(floor - 1).calcFreeSpaces() == 0) {
            System.out.println("Sorry, this floor is full! Please choose another floor.");
            return;
        }

        System.out.println("\n\nWhich spot do you want to park?");
        printFloor(carParc.getFloor(floor - 1));
        int spot = scanner.nextInt();

        if (carParc.getFloor(floor-1).getParkingSpots()[spot-1].isOccupied()) {
            System.out.println("This spot is already occupied! Please choose another spot.");
            return;
        }

        carParc.getFloor(floor-1).setParkingSpotOccupied(spot-1);
        printTicket(carParc);
        System.out.println("\n\nYou have successfully parked your car.");
    }

    private void exitParkingGarage(CarParc carparc, Scanner scanner) throws InterruptedException {
        System.out.println("\n\nIn which floor is your car parked?");
        int floor = scanner.nextInt();
        System.out.println("\n\nWhich spot is your car parked in?");
        printFloor(carparc.getFloor(floor-1));
        int spot = scanner.nextInt();
        if (!carparc.getFloor(floor-1).getParkingSpots()[spot-1].isOccupied()) {
            System.out.println("The selected parking spot is not occupied. Please check your input.");
            return;
        }
        System.out.println("\n\nWhich Exit do you want to take?\n" +
                "1. Exit Barrier 1\n" +
                "2. Exit Barrier 2");
        int exit = scanner.nextInt();
        System.out.println("\n\nPlease enter your ticket number:");
        int ticketNumber = scanner.nextInt();

        try {
            Ticket ticket = carparc.getTicketMachine().getTicketFromNumber(ticketNumber);
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
            System.out.println("Invalid ticket number. Please try again.");
        }
    }

    private void payTicket(CarParc carParc, Scanner scanner) {
        System.out.println("\n\nPlease enter your ticket number:");
        int ticketNumber = scanner.nextInt();

        try {
            Ticket ticket = carParc.getTicketMachine().getTicketFromNumber(ticketNumber);
            LocalDateTime currentTime = LocalDateTime.now();
            float price = carParc.getFloor(0).getCashDesk().calcParkingPrice(ticket, currentTime);

            System.out.printf("Parking fee: %.2f CHF\n", price);
            System.out.println("Do you want to pay? (1=Yes, 2=No)");
            int payChoice = scanner.nextInt();

            if (payChoice == 1) {
                carParc.getFloor(0).getCashDesk().payTicket(ticketNumber);
                System.out.println("Payment successful! You can now exit the parking garage.\n");
            } else {
                System.out.println("Payment cancelled.\n");
            }
        } catch (TicketNotFoundException e) {
            System.out.println("Invalid ticket number. Please try again.");
        }
    }

    public void printFloor(Floor floor){
        ParkingSpot[] spaces = floor.getParkingSpots();

        IntStream.range(0, spaces.length).forEach(i -> {
            if (spaces[i] != null && spaces[i].isOccupied()) {
                System.out.print("---  ");
            } else {
                System.out.printf("%03d  ", i + 1);
            }

            if ((i + 1) % 10 == 0 && i != spaces.length - 1) {
                System.out.println();
            }
        });

        System.out.println();
    }

    public void printTicket(CarParc carParc){
        Ticket ticket = carParc.getTicketMachine().generateTicket();
        int ticketNumber = ticket.getTicketNumber();
        String ticketTime = ticket.getPurchaseTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("########################" +
                "\n#  Ticket Information  #" +
                "\n#----------------------#" +
                "\n#      Nr. "+ticketNumber+"      #" +
                "\n# "+ticketTime+"  #" +
                "\n########################");
    }
}