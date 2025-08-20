package parkhausSimulator.app.src.main.java.ch.javamilesiii;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IOHandler {

    public void startSimulation(CarParc carParc) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Parkhaus Simulator" +
                "\n-----------------------------------");
        while (true) {
            System.out.println("What do you want to do?" +
                    "\n1. Enter the parking garage" +
                    "\n2. Pay your ticket" +
                    "\n3. Exit the parking garage" +
                    "\n4. Exit the simulation");
            int answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    enterParkingGarage(carParc, scanner);
                    break;
                case 2:

                    break;
                case 3:
                    exitParkingGarage(carParc, scanner);
                    break;
                case 4:
                    return;
            }
        }
    }

    private void enterParkingGarage(CarParc carParc, Scanner scanner) throws InterruptedException {
        carParc.getBarrier().passThroughBarrier();
        printTicket(carParc);
        System.out.printf("Which floor do you want to enter? (1-%d)\n", carParc.getFloorCount());
        int floor = scanner.nextInt();
        System.out.println("\n\nWhich spot do you want to park?\n");
        printFloor(carParc.getFloor(floor - 1));
        int spot = scanner.nextInt();
        carParc.getFloor(floor-1).setParkingSpotOccupied(spot-1);
        System.out.println("\n\nYou have successfully parked your car.");
    }
    private void exitParkingGarage(CarParc carparc, Scanner scanner) throws InterruptedException {
        System.out.println("\n\nIn which floor is your car parked?\n");
        int floor = scanner.nextInt();
        System.out.println("\n\nWhich spot is your car parked in?\n");
        printFloor(carparc.getFloor(floor-1));
        int spot = scanner.nextInt();
        if (!carparc.getFloor(floor-1).getParkingSpots()[spot-1].isOccupied()) {
            System.out.println("The selected parking spot is not occupied. Please check your input.");
            return;
        }
        System.out.println("\n\nWhich Exit do you want to take?\n" +
                "1. Exit Barrier 1\n" +
                "2. Exit Barrier 2\n");
        int exit = scanner.nextInt();
        System.out.println("\n\nPlease enter your ticket number:\n");
        int ticketNumber = scanner.nextInt();
        Ticket ticket = carparc.getTicketMachine().getTicketFromNumber(ticketNumber);
        if (ticket == null) {
            System.out.println("Invalid ticket number. Please try again.");
            return;
        }
        switch (exit) {
            case 1:
                if (carparc.getExitBarrier1().checkTicket(ticketNumber)){
                    carparc.getExitBarrier1().passThroughBarrier();
                }
                break;
            case 2:
                if (carparc.getExitBarrier2().checkTicket(ticketNumber)){
                    carparc.getExitBarrier2().passThroughBarrier();
                }
                break;
            default:
                System.out.println("Invalid exit number. Please try again.");
        }

        carparc.getFloor(floor-1).setParkingSpotFree(spot-1);
        carparc.getTicketMachine().removeTicket(ticket);
    }
    private void payTicket() {

    }
    public void printFloor(Floor floor){
        ParkingSpot[] spaces = floor.getParkingSpots();

        IntStream.range(0, spaces.length).forEach(i -> {
            if (spaces[i].isOccupied()) {
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
