public class Truck extends  Car {
    @Override
    public void setType(String newType) {
        super.setType(newType);
    }

    public Truck(int id, String model, String brand) {
        super(id, model, brand);
        this.setType("Грузовая");
    }
}
