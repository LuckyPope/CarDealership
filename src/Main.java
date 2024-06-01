import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        CarDealershipConsole cdc = new CarDealershipConsole();
        cdc.initializeCarDealerShip();
//        try {
//            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/CarsShop";
//
//            Properties authorization = new Properties();
//            authorization.put("user", "postgres");
//            authorization.put("password", "220522");
//
//            Connection connection = DriverManager.getConnection(url, authorization);
//
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//            ResultSet table = statement.executeQuery("SELECT * FROM public.Cars");
//
//            table.first();
//
//            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
//                System.out.print(table.getMetaData().getColumnName(i) + "\t\t");
//            }
//            System.out.println();
//
//            table.beforeFirst();
//            while (table.next()) {
//                for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
//                    System.out.print(table.getString(i) + "\t\t");
//                }
//                System.out.println();
//            }
//
//            if (table != null)
//                table.close();
//
//            if (statement != null)
//                statement.close();
//
//            if (connection != null)
//                connection.close();
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}