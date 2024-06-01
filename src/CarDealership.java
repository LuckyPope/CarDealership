import java.sql.SQLException;
import java.util.ArrayList;

public class CarDealership {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Car> soldCars = new ArrayList<>();

    private final DataBase db = new DataBase();

    public void fillData(ArrayList<Car> carsDB, ArrayList<Customer> customersDB, ArrayList<Car> soldCarsDB) {
        cars = carsDB;
        customers = customersDB;
        soldCars = soldCarsDB;
    }

    public ArrayList<Car> getCars() throws SQLException {
        this.cars.clear();
        this.cars = db.getCars();
        return this.cars;
    }

    public ArrayList<Car>  getSoldCars() throws SQLException {
        this.soldCars.clear();
        this.soldCars = db.getSoldCars();
        return this.soldCars;
    }

    public ArrayList<Customer> getCustomers() throws SQLException {
        this.customers.clear();
        this.customers = db.getCustomers();
        return this.customers;
    }

    public void addCar(String model, String brand) throws SQLException {
        int id = (cars.isEmpty() ? 0 : cars.size());
//        Car newCar = new Car(id, brand, model);
//        cars.add(newCar);

        db.addCarToDB(new Car(id, brand, model));
    }

    public void addCustomer(String name, int age, boolean isMale) throws SQLException {
        int id = (customers.isEmpty() ? 0 : customers.size());
//        customers.add(new Customer(id, name, age, isMale));
        db.addCustomerToDB(new Customer(id, name, age, isMale));
    }

    boolean sellCar(Customer customer, String model, String brand) throws SQLException {
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

//        cars.remove(soldCar.getID());
        db.deleteCarFromDB(soldCar.getID());
        soldCar.setCustomer(customer);

        customer.SetCar(soldCar);

        db.addSoldToDB(soldCar, customer.getID());

        return true;
    }

    public void deleteCar(int id) throws SQLException {
        db.deleteCarFromDB(id);
//        cars.remove(id);
    }

    public void deleteCustomer(int id) throws SQLException {
        db.deleteCustomerFromDB(id);
//        customers.remove(id);
    }
}
