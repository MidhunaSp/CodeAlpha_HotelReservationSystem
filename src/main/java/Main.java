import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        this.isAvailable = false;
    }

    public void releaseRoom() {
        this.isAvailable = true;
    }
}

class Reservation {
    private int reservationId;
    private int roomNumber;
    private String guestName;
    private int numOfDays;
    private double totalAmount;

    public Reservation(int reservationId, int roomNumber, String guestName, int numOfDays, double totalAmount) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.numOfDays = numOfDays;
        this.totalAmount = totalAmount;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private int reservationIdCounter;

    public Hotel() {
        rooms = new ArrayList();
        reservations = new ArrayList();
        reservationIdCounter = 1;
        initializeRooms();
    }

    private void initializeRooms() {
        
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(103, "Deluxe"));
        rooms.add(new Room(104, "Deluxe"));
        rooms.add(new Room(105, "Suite"));
        rooms.add(new Room(106, "Suite"));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + " Category: " + room.getCategory());
            }
        }
    }

    public void makeReservation(int roomNumber, String guestName, int numOfDays) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                double totalAmount = calculateTotalAmount(room.getCategory(), numOfDays);
                reservations.add(new Reservation(reservationIdCounter++, roomNumber, guestName, numOfDays, totalAmount));
                room.bookRoom();
                System.out.println("Reservation successful! Reservation ID: " + (reservationIdCounter - 1));
                return;
            }
        }
        System.out.println("Sorry, the requested room is not available.");
    }

    private double calculateTotalAmount(String category, int numOfDays) {
        
        double pricePerNight = 100.0; 
        if (category.equals("Deluxe")) {
            pricePerNight = 150.0;
        } else if (category.equals("Suite")) {
            pricePerNight = 200.0;
        }
        return pricePerNight * numOfDays;
    }

    public void displayReservationDetails(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getRoomNumber());
                System.out.println("Number of Days: " + reservation.getNumOfDays());
                System.out.println("Total Amount: $" + reservation.getTotalAmount());
                return;
            }
        }
        System.out.println("Reservation not found with ID: " + reservationId);
    }
}

 public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.next();
                    System.out.print("Enter number of days: ");
                    int numOfDays = scanner.nextInt();
                    hotel.makeReservation(roomNumber, guestName, numOfDays);
                    break;
                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    hotel.displayReservationDetails(reservationId);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }
}
