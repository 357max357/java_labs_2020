package database;

public class QueryTemplates {
  public final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS products ("
      + "id INT NOT NULL UNIQUE AUTO_INCREMENT,"
      + "prodid INT not NULL,"
      + "title VARCHAR(255) UNIQUE,"
      + "cost INT,"
      + "PRIMARY KEY (id))";
  public final static String DELETE_TABLE = "DROP TABLE IF EXISTS products";
  public final static String SEARCH_BY_TITLE = "SELECT * FROM products WHERE title = ?";
  public final static String LIST_ALL = "SELECT * FROM products";
  public final static String UPDATE_COST = "UPDATE products SET cost = ? WHERE title = ?";
  public final static String FILTER_BY_PRICE = "SELECT * FROM products WHERE cost BETWEEN ? AND ?";
  public final static String DELETE_PRODUCT = "DELETE FROM products WHERE title = ?";
  public final static String ADD_PRODUCT = "INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?)";
}
