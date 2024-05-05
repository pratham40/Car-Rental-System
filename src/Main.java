import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car car1 = new Car("C001","Mahindra","Thar",255);
        Car car2 = new Car("C002","Mahindra","Bolero-SUV",145.78);
        Car car3 = new Car("C003","Toyota","Camry",70);
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

    }

}
