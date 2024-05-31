import java.util.ArrayList;

public class CarDealership {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Car> soldCars = new ArrayList<>();

    public ArrayList<Car> getCars() {
        return this.cars;
    }

    public ArrayList<Car>  getSoldCars() {
        return this.soldCars;
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void addCar(String model, String brand) {
        int id = (cars.isEmpty() ? 0 : cars.size());
        cars.add(new Car(id, brand, model));
    }

    public void addCustomer(String name, int age, boolean isMale) {
        int id = (customers.isEmpty() ? 0 : customers.size());
        customers.add(new Customer(id, name, age, isMale));
    }

    boolean sellCar(Customer customer, String model, String brand) {
        Car soldCar = new Car(0, "", "");
        for(Car car : cars) {
            if (car.getModel().equals(model) && car.getBrand().equals(brand)) {
               soldCar = car;
               break;
            }
        }

        if (soldCar.getBrand().isEmpty() && soldCar.getModel().isEmpty()) {
            return false;
        }

        cars.remove(soldCar.getID());

        soldCar.setCustomer(customer);

        customer.SetCar(soldCar);

        soldCars.add(soldCar);

        return true;
    }

    public void deleteCar(int id) {
        cars.remove(id);
    }

    public void deleteCustomer(int id) {
        customers.remove(id);
    }
}
