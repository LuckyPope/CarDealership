import java.util.ArrayList;

public interface CustomerIn {
    String getName();

    int getID();

    int getAge();

    boolean getGender();

    ArrayList<Car> getCars();

    Car getCar(String model, String brand);

    void setID(int newID);

    void setName(String newName);

    void setAge(int newAge);

    void setGender(boolean isMale);

    void SetCar(Car newCar);
}
