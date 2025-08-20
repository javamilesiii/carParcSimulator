package parkhausSimulator;

import java.util.Scanner;

public class IOHandler {

    public void startSimulation(CarParc carParc) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Parkhaus Simulator" +
                "\n-----------------------------------");

        System.out.println("What do you want to do?" +
                "\n1. Enter the parking garage" +
                "\n2. Pay your ticket" +
                "\n3. Exit the parking garage" +
                "\n4. Exit the simulation");
        int answer = scanner.nextInt();

        switch (answer) {
            case 1:
                carParc.getBarrier().passThroughBarrier();
                System.out.println("Which floor do you want to enter?");
                int floor = scanner.nextInt();

                break;
            case 2:

                break;
            case 3:

            case 4:
        }
    }
}
