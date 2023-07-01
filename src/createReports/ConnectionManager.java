
package createReports;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url =  "jdbc:sqlserver://zc.database.windows.net:1433;databaseName=tienda_virtual"; 
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//com.microsoft.sqlserver.jdbc.SQLServerDriver   
    //com.mysql.jdbc.Driver
    private static String username = "zckeeper"; 
    private static String password = "SecurityBad21";
    private static Connection con=null;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
}




