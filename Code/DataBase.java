import java.sql.*;

public class DataBase {
    Connection con;
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1/hospital", "root", "");
            /*con = DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306/smart_healthcare", "oopd2018", "oopd2018");*/
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    };

}