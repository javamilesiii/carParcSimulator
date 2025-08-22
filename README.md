# Car Park Simulator

A Java-based parking garage simulation system that models the operations of a multi-floor car park with entry/exit barriers, ticket management, and payment processing.

## Overview

This project simulates a realistic parking garage environment with the following key components:
- Multi-floor parking structure
- Automated entry and exit barriers
- Ticket generation and validation system
- Payment processing with time-based pricing
- Real-time space availability tracking

## Features

### Core Functionality
- **Multi-floor Structure**: Configurable number of floors with customizable parking spaces per floor
- **Barrier System**: Automated entrance and exit barriers with ticket validation
- **Ticket Management**: Automatic ticket generation with unique IDs and timestamps
- **Pricing System**: Time-based parking fees calculated per minute
- **Space Tracking**: Real-time monitoring of available parking spaces
- **Payment Processing**: Cash desk system for ticket payment validation

### Current Implementation
- 5 floors with 50 parking spaces each (configurable)
- CHF 1.50 per minute pricing (configurable)
- Entrance barrier with automatic ticket generation
- Two exit barriers with payment verification
- Exception handling for invalid tickets

## Project Structure

```
app/ch/javamilesiii/src/
├── Main.java                    # Application entry point
├── CarParc.java                 # Main parking garage class
├── Barrier.java                 # Entry barrier implementation
├── ExitBarrier.java             # Exit barrier with ticket validation
├── Floor.java                   # Individual floor management
├── ParkingSpot.java             # Individual parking space
├── Ticket.java                  # Parking ticket with timestamp
├── TicketMachine.java           # Ticket generation and management
├── CashDesk.java                # Payment processing
├── IOHandler.java               # User interface handler
├── Display.java                 # Display system for real-time updates
└── TicketNotFoundException.java # Custom exception class
```

## Getting Started

### Prerequisites
- Java 8 or higher
- Java IDE (IntelliJ IDEA, Eclipse, VS Code, etc.) or command line tools

### Installation
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd carParcSimulator
   ```

2. Navigate to the source directory:
   ```bash
   cd app/src/main/java/ch/javamilesiii
   ```

3. Compile the Java files:
   ```bash
   javac -d . *.java
   ```

4. Run the simulation:
   ```bash
   java parkhausSimulator.Main
   ```

## Usage

The simulator provides an interactive console interface with the following options:
1. **Enter the parking garage** - Generate a ticket and pass through the entrance barrier
2. **Pay your ticket** - Process payment for parking duration
3. **Exit the parking garage** - Validate paid ticket and exit through barrier
4. **Exit the simulation** - Terminate the program

### Example Workflow
1. Customer enters parking garage → Ticket generated automatically
2. Customer parks on selected floor
3. Customer returns to pay ticket at cash desk
4. Customer exits through barrier after ticket validation

## Configuration

You can modify the parking garage settings in `Main.java`:

```java
int floors = 5;              // Number of floors
int parkingSpots = 50;       // Spaces per floor
float pricePerMinute = 1.5f; // Price in currency units per minute
```

## Class Relationships

- **CarParc**: Central controller managing all components
- **Floor**: Contains parking spots and cash desk
- **Barrier/ExitBarrier**: Control vehicle access
- **TicketMachine**: Handles ticket lifecycle
- **CashDesk**: Processes payments and calculates fees
- **Ticket**: Stores parking session data

The relationships are shown in the following UML diagram:
[UML](uml_parkingSimulator.png)

## Current Status

### Implemented Features ✅
- Basic parking garage structure
- Ticket generation and management
- Barrier operations with timing
- Payment calculation system
- Space availability tracking
- Custom exception handling

## Contributing

This project is part of an educational exercise. Contributions and suggestions for improvements are welcome!

## License

Licensed under the Apache License 2.0. See [LICENSE](LICENSE) file for details.

---
*This simulator demonstrates object-oriented programming principles including inheritance, encapsulation, and exception handling in a practical parking garage management scenario.*
