public class PassengerCar  extends Car {

    public PassengerCar(int id, String model, String brand) {
        super(id, model, brand);
        this.setType("Легковая");
    }

    @Override
    public void setType(String newType) {
        super.setType(newType);
    }
}
