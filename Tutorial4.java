import java.util.ArrayList;
import java.util.Scanner;

// =======================
// Abstract Base Class
// =======================
abstract class Vehicle {
    protected int vehicleId;
    protected String brand;
    protected String model;
    protected double ratePerDay;
    protected boolean isAvailable;

    public Vehicle(int vehicleId, String brand, String model, double ratePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.ratePerDay = ratePerDay;
        this.isAvailable = true;
    }

    public abstract double calculateRental(int days);

    public boolean rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getDetails() {
        return vehicleId + " | " + brand + " " + model +
                " | $" + ratePerDay + "/day | Available: " + isAvailable;
    }
}

// =======================
// Car Class
// =======================
class Car extends Vehicle {
    private int seatingCapacity;

    public Car(int vehicleId, String brand, String model, double ratePerDay, int seatingCapacity) {
        super(vehicleId, brand, model, ratePerDay);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public double calculateRental(int days) {
        return ratePerDay * days;
    }
}

// =======================
// Bike Class
// =======================
class Bike extends Vehicle {
    private int engineCapacity;

    public Bike(int vehicleId, String brand, String model, double ratePerDay, int engineCapacity) {
        super(vehicleId, brand, model, ratePerDay);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public double calculateRental(int days) {
        double discount = (days > 5) ? 0.9 : 1.0;
        return ratePerDay * days * discount;
    }
}

// =======================
// Truck Class
// =======================
class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(int vehicleId, String brand, String model, double ratePerDay, double loadCapacity) {
        super(vehicleId, brand, model, ratePerDay);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public double calculateRental(int days) {
        double insuranceFee = 50.0;
        return (ratePerDay * days) + insuranceFee;
    }
}

// =======================
// Rental System Class
// =======================
class RentalSystem {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }

        for (Vehicle v : vehicles) {
            System.out.println(v.getDetails());
        }
    }

    public Vehicle findVehicleById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId() == id) {
                return v;
            }
        }
        return null;
    }
}

// =======================
// Main Class
// =======================
public class Tutorial4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RentalSystem system = new RentalSystem();
        int choice;

        do {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Select Vehicle Type:");
                    System.out.println("1. Car");
                    System.out.println("2. Bike");
                    System.out.println("3. Truck");
                    int type = sc.nextInt();

                    System.out.print("Enter Vehicle ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Brand: ");
                    String brand = sc.nextLine();

                    System.out.print("Enter Model: ");
                    String model = sc.nextLine();

                    System.out.print("Enter Rate per day: ");
                    double rate = sc.nextDouble();

                    if (type == 1) {
                        System.out.print("Enter Seating Capacity: ");
                        int seats = sc.nextInt();
                        system.addVehicle(new Car(id, brand, model, rate, seats));

                    } else if (type == 2) {
                        System.out.print("Enter Engine Capacity (cc): ");
                        int engine = sc.nextInt();
                        system.addVehicle(new Bike(id, brand, model, rate, engine));

                    } else if (type == 3) {
                        System.out.print("Enter Load Capacity (tons): ");
                        double load = sc.nextDouble();
                        system.addVehicle(new Truck(id, brand, model, rate, load));

                    } else {
                        System.out.println("Invalid vehicle type.");
                    }
                    break;

                case 2:
                    system.displayVehicles();
                    break;

                case 3:
                    System.out.print("Enter Vehicle ID to rent: ");
                    int rentId = sc.nextInt();
                    Vehicle rentVehicle = system.findVehicleById(rentId);

                    if (rentVehicle != null && rentVehicle.isAvailable()) {
                        System.out.print("Enter number of days: ");
                        int days = sc.nextInt();
                        rentVehicle.rentVehicle();
                        double cost = rentVehicle.calculateRental(days);
                        System.out.println("Vehicle rented successfully!");
                        System.out.println("Total cost: $" + cost);
                    } else {
                        System.out.println("Vehicle not available or invalid ID.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Vehicle ID to return: ");
                    int returnId = sc.nextInt();
                    Vehicle returnVehicle = system.findVehicleById(returnId);

                    if (returnVehicle != null && !returnVehicle.isAvailable()) {
                        returnVehicle.returnVehicle();
                        System.out.println("Vehicle returned successfully!");
                    } else {
                        System.out.println("Invalid ID or vehicle was not rented.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}