import java.util.ArrayList;

public class Customer implements CustomerIn {

    private int ID;
    private String name;
    private int age;
    private boolean isMale;
    private ArrayList<Car> cars = new ArrayList<>();

    public Customer(int id, String name, int age, boolean isMale) {
        this.ID = id;
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public boolean getGender() {
        return this.isMale;
    }

    @Override
    public ArrayList<Car> getCars() {
        return this.cars;
    }

    public Car getCar(String model, String brand) {
        Car resultCar =new Car(0,"", "");

        for (Car car : cars) {
            if (car.getModel().equals(model) && car.getBrand().equals(brand)) {
                resultCar = car;
            }
        }
        return resultCar;
    }

    @Override
    public void setID(int newID) {
        this.ID = newID;
    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public void setAge(int newAge) {
        this.age = newAge;
    }

    @Override
    public void setGender(boolean isMale) {
        this.isMale = isMale;
    }

    @Override
    public void SetCar(Car newCar) {
        this.cars.add(newCar);
    }
}
