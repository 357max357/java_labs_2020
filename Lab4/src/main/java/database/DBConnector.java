package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

  private static final String URL = "jdbc:mysql://localhost:3306/lab4?useSSL=false&serverTimezone=Europe/Warsaw";
  private static final String USER = "root";
  private static final String PASSWORD = "maxmaxmax357";

  public static Connection connectDB() {
    try {
      Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
      return connection;
    } catch (SQLException e) {
      throw new RuntimeException("Could not connect db");
    }
  }
}
