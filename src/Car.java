class Car {
    private final String carId;
    private final String carModel;
    private final String carBrand;
    private final double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String carModel, String carBrand, double basePricePerDay) {
        this.carId = carId;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Car{" + "carId='" + carId + '\'' + ", carModel='" + carModel + '\'' + ", carBrand='" + carBrand + '\'' + ", basePricePerDay=" + basePricePerDay + ", isAvailable=" + isAvailable + '}';
    }

    public String getCarId() {
        return carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}