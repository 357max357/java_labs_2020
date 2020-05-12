import database.DBConnector;
import model.Menu;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

  public static void main(String[] args) {
    try {
      Menu menu = new Menu();
      menu.show();
    } finally {
      try {
        DBConnector.connectDB().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
