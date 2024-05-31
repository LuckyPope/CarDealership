public interface CarIn {
    String getType();
    int getID();
    String getModel();
    String getBrand();
    Customer getCustomer();

    void setType(String newType);
    void setModel(String newModel);
    void setBrand(String newBrand);
    void setCustomer(Customer newCustomer);
}
