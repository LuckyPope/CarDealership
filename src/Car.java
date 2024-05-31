public class Car implements CarIn {

    private int ID;
    private String type;
    private String model;
    private String brand;
    private Customer customer;

    public Car(int id, String brand, String model) {
        this.ID = id;
        this.model = model;
        this.brand = brand;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public void setType(String newType) {
        this.type = newType;
    }

    @Override
    public void setModel(String newModel) {
        this.model = newModel;
    }

    @Override
    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    @Override
    public void setCustomer(Customer newCustomer) {
        this.customer = newCustomer;
    }
}
