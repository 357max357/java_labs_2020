package operator;

import database.DBOperator;
import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Commands {

  DBOperator dbOperator;

  public Commands() {
    dbOperator = new DBOperator();
  }

  public void createTable() {
    try {
      dbOperator.createTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteTable() {
    try {
      dbOperator.deleteTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void listProducts() {
    try {
      List<Product> products = makeProductList(dbOperator.listAllProductsInTable());
      if (products.isEmpty()) {
        System.out.println(ErrorMessages.NO_PRODUCTS_FOUND);
      } else {
        products.stream().forEach(System.out::println);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addProduct(Product product) {
    try {
      if (product.getCost() < 0 || dbOperator.checkIfProductExistsInTable(product.getTitle())) {
        System.out.println(ErrorMessages.INVALID_ADD);
      } else if (dbOperator.addProductToTable(product.getProdId(), product.getTitle(), product.getCost())){
        System.out.println(ErrorMessages.ACTION_FAILED);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteProduct(String title) {
    try {
      if (!dbOperator.checkIfProductExistsInTable(title)) {
        System.out.println(ErrorMessages.NO_PRODUCTS_FOUND);
      } else if (dbOperator.deleteProductFromTable(title)){
        System.out.println(ErrorMessages.ACTION_FAILED);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void getPrice(String title) {
    try {
      if (!dbOperator.checkIfProductExistsInTable(title)) {
        System.out.println(ErrorMessages.NO_PRODUCTS_FOUND);
      } else {
        System.out.println(dbOperator.getProductPriceFromTable(title));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void changePrice(String title, int newCost) {
    try {
      if (!dbOperator.checkIfProductExistsInTable(title) || newCost < 0) {
        System.out.println(ErrorMessages.INVALID_CHANGE);
      } else if (dbOperator.changeProductPriceInTable(title, newCost)) {
        System.out.println(ErrorMessages.ACTION_FAILED);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void filterByPrice(int from, int to) {
    try {
      if (from < 0 || to < 0 || from > to) {
        System.out.println(ErrorMessages.INVALID_INPUT);
      } else {
        List<Product> products = makeProductList(dbOperator.listProductsInPriceRangeFromTable(from, to));
        if (products.isEmpty()) {
          System.out.println(ErrorMessages.NO_PRODUCTS_FOUND);
        } else {
          products.stream().forEach(System.out::println);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private List<Product> makeProductList(ResultSet resultSet) throws SQLException {
    List<Product> products = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int prodid = resultSet.getInt("prodid");
      String title = resultSet.getString("title");
      int cost = resultSet.getInt("cost");
      products.add(new Product(id, prodid, title, cost));
    }
    return products;
  }
}
