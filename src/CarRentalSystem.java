import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CarRentalSystem {
    private final List<Car> cars;
    private final List<Customer> customers;
    private final List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available!!!");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
        } else {
            System.out.println("Car was not rented !!!");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("====>Car Rental System<====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice ");
            int choice = sc.nextInt();
            System.out.println();
            if (choice == 1) {
                System.out.println("==>Rent a Car<===");
                System.out.print("Enter Your Name: ");
                String customerName = sc.nextLine();
                sc.nextLine();
                System.out.println("\nAvailable Cars: ");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getCarBrand() + " - " + car.getCarModel());
                    }
                }
                System.out.print("Enter the CAR ID you want to rent: ");
                String carId = sc.next();
                System.out.print("Enter the number of days for rental: ");
                int rentalDays = sc.nextInt();
                Customer newCustomer = new Customer("CUS" + customers.size() + 1, customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("===>Rental Information<===");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.println("Total Price: " + totalPrice);
                    System.out.print("Confirm Rental (Y/N): ");
                    String confirm = sc.next();
                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar is rented Successfully!!!");
                    } else {
                        System.err.println("\nRental cancelled");
                    }
                } else {
                    System.out.println("\nInvalid car or car is not available");
                }
            } else if (choice == 2) {
                System.out.println("===>Return a Car<===");
                System.out.print("Enter the Car ID you want to return");
                String returnCarId = sc.next();
                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(returnCarId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car return successfully by " + customer.getCustomerName());
                    } else {
                        System.out.println("Car was not rented  or rental information is missing");
                    }
                } else {
                    System.out.println("Invalid Car ID or car was not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid Choice!Please enter a valid choice!!! ");
            }
        }
        System.out.println("Thank you for choosing the Car Rental System");
    }

}