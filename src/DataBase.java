import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBase {

    private final ArrayList<Car> carsFromDB = new ArrayList<>();
    private final ArrayList<Customer> customersFromDB = new ArrayList<>();
    private final ArrayList<Car> soldCarsFromDB = new ArrayList<>();

    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/CarsShop";

            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "220522");

            connection = DriverManager.getConnection(url, authorization);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public ArrayList<Car> getCars() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet table = statement.executeQuery("SELECT * FROM public.cars ORDER BY car_id");

        while (table.next()) {
            int ID = table.getInt("car_id");
            String brand = table.getString("brand");
            String model = table.getString("model");

            carsFromDB.add(new Car(ID, brand, model));
        }

        return carsFromDB;
    }

    public ArrayList<Customer> getCustomers() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet table = statement.executeQuery("SELECT * FROM public.customers ORDER BY cust_id");

        while (table.next()) {
            int ID = table.getInt("cust_id");
            String name = table.getString("name");
            int age = table.getInt("age");
            boolean isMale = (table.getInt("ismale") == 1);
            Array carsID_db = table.getArray("carsid");
//            int[] carsID = (int[]) carsID_db.getArray();
//
//            parseCustomer(carsID);
            customersFromDB.add(new Customer(ID, name, age, isMale));
        }

        return customersFromDB;
    }

    public ArrayList<Car> getSoldCars() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet table = statement.executeQuery("SELECT * FROM public.soldcars ORDER BY soldcarid");

        while (table.next()) {
            int carID = table.getInt("soldcarid");
            int customerID = table.getInt("cust_id");
            String model = table.getString("model");
            String brand = table.getString("brand");
            Car curCar = new Car(carID, brand, model);

            curCar.setCustomer(parseSoldCars(customerID));
            soldCarsFromDB.add(curCar);

        }

        return soldCarsFromDB;
    }

    public Customer parseSoldCars(int custID) {
        Customer curCustomer = new Customer(-1, "", -1, false);

        for (Customer cs : customersFromDB) {
            if (cs.getID() == custID) {
                curCustomer.setID(custID);
                curCustomer.setName(cs.getName());
                curCustomer.setAge(cs.getAge());
                curCustomer.setGender(cs.getGender());

                break;
            }
        }
        return curCustomer;
    }

    public void addCarToDB(Car newCar) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        statement.executeUpdate("INSERT INTO public.cars (brand, model)" + "values ('" + newCar.getBrand() + "','" + newCar.getModel() + "');");
    }

    public void addCustomerToDB(Customer newCustomer) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        int isMale = (newCustomer.getGender() ? 1 : 0);

        statement.executeUpdate("INSERT INTO public.customers (name, age, isMale)" + "values ('" + newCustomer.getName() + "','" + newCustomer.getAge() + "','" + isMale + "');");
    }

    public void addSoldToDB(Car soldCar, int customerID) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        statement.executeUpdate("INSERT INTO public.soldcars (soldcarid, cust_id, model, brand)" +
                "values ('" + soldCar.getID() + "','" + customerID + "','" + soldCar.getModel() + "','" + soldCar.getBrand() + "');");
    }

    public void editCar(Car car) throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "UPDATE public.cars SET model = ?, brand = ? WHERE car_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, car.getModel());
        pstmt.setString(2, car.getBrand());
        pstmt.setInt(3, car.getID());
        pstmt.executeUpdate();
    }

    public void editCustomer(Customer customer) throws SQLException {
        Connection connection = getConnection();

        int isMale = (customer.getGender() ? 1 : 0);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "UPDATE public.customers SET name = ?, age = ?, ismale = ? WHERE cust_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, customer.getName());
        pstmt.setInt(2, customer.getAge());
        pstmt.setInt(3, isMale);
        pstmt.setInt(4, customer.getID());
        pstmt.executeUpdate();
    }

    public void deleteCarFromDB(int carID) throws SQLException {
        Connection connection = getConnection();

        String sql = "DELETE FROM public.cars WHERE car_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, carID);
        pstmt.executeUpdate();
    }

    public void deleteCustomerFromDB(int customerID) throws SQLException {
        Connection connection = getConnection();

        String sql = "DELETE FROM public.customers WHERE cust_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, customerID);
        pstmt.executeUpdate();
    }
}
