package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.QueryTemplates;


public class DBOperator {

  private Connection dbConnection;

  public DBOperator() {
    this.dbConnection = DBConnector.connectDB();
  }

  public void createTable() throws SQLException {
    dbConnection.createStatement().executeUpdate(QueryTemplates.CREATE_TABLE);
  }

  public void deleteTable() throws SQLException {
    dbConnection.createStatement().executeUpdate(QueryTemplates.DELETE_TABLE);
  }

  public boolean checkIfProductExistsInTable(String title) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.SEARCH_BY_TITLE);
    preparedStatement.setString(1, title);
    ResultSet resultSet = preparedStatement.executeQuery();
    return resultSet.next();
  }

  public boolean addProductToTable(int prodid, String title, int cost) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.ADD_PRODUCT);
    preparedStatement.setInt(1, prodid);
    preparedStatement.setString(2, title);
    preparedStatement.setInt(3, cost);
    return preparedStatement.execute();
  }

  public boolean deleteProductFromTable(String title) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.DELETE_PRODUCT);
    preparedStatement.setString(1, title);
    return preparedStatement.execute();
  }

  public ResultSet listAllProductsInTable() throws SQLException {
    return dbConnection.createStatement().executeQuery(QueryTemplates.LIST_ALL);
  }

  public int getProductPriceFromTable(String title) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.SEARCH_BY_TITLE);
    preparedStatement.setString(1, title);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    return resultSet.getInt("cost");
  }

  public boolean changeProductPriceInTable(String title, int newCost) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.UPDATE_COST);
    preparedStatement.setInt(1, newCost);
    preparedStatement.setString(2, title);
    return preparedStatement.execute();
  }

  public ResultSet listProductsInPriceRangeFromTable(int from, int to) throws SQLException {
    PreparedStatement preparedStatement = dbConnection.prepareStatement(QueryTemplates.FILTER_BY_PRICE);
    preparedStatement.setInt(1, from);
    preparedStatement.setInt(2, to);
    return preparedStatement.executeQuery();
  }
}
