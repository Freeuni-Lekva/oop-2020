import java.sql.*;

public class Main {
    StaticSample st = new StaticSample();
    public static void main(String[] args) {
        try {
            // Class.forName("StaticSample");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost",
                    "root",
                    "12345678");
            System.out.println("Connected!!!!");
//            Statement createDbStm = connection.createStatement();
//            createDbStm.execute("CREATE DATABASE test_db;");
            Statement useDbStm = connection.createStatement();
            useDbStm.execute("USE test_db;");
//            Statement createTableStm = connection.createStatement();
//            createTableStm.execute(
//                    "CREATE TABLE metropolises (" +
//                    "metropolis CHAR(64)," +
//                    "continent  CHAR(64)," +
//                    "population BIGINT" +
//                            ");");
            Statement queryStm = connection.createStatement();
            ResultSet countRS = queryStm.executeQuery("SELECT count(*) FROM metropolises;");
            System.out.println(countRS.next());
            System.out.println(countRS.getInt(1));
            System.out.println(countRS.next());
            queryStm.executeUpdate(
                    "INSERT INTO metropolises VALUES" +
                            "(\"Mumbai\", \"Asia\", 20400000)," +
                            "(\"New York\", \"North America\", 21295000)," +
                            "(\"San Francisco\", \"North America\", 5780000);");
            ResultSet rs = queryStm.executeQuery("SELECT metropolis, continent, population FROM metropolises;");
            while (rs.next()) {
                System.out.printf("City: %s Continent: %s Population: %d\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
