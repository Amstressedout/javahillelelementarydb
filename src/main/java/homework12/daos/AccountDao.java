package homework12.daos;

import homework12.database.Database;
import homework12.entities.Accounts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final String INSERT_INTO_ACCOUNT = "INSERT INTO accounts (client_id, number, value) VALUES (?,?,?)";
    private static final String UPDATE_SET_ACCOUNT = "UPDATE accounts SET client_id=?, number=?, value=? WHERE id=?";
    private static final String DELETE_FROM_ACCOUNT = "DELETE FROM accounts WHERE id=?";
    private static final String SELECT_ALL_FROM_ACCOUNT = "SELECT * FROM accounts";
    private static final String SELECT_NUMBER_BY_VALUE = "SELECT number FROM accounts WHERE values>=?";
    private static final String SELECT_BY_ID_FROM_ACCOUNT = "SELECT * FROM accounts WHERE id=?";

    private Accounts accountParameters (ResultSet resultSet) throws SQLException {

        Accounts accounts = new Accounts();
        accounts.setId(resultSet.getInt("id"));
        accounts.setClient_id(resultSet.getInt("client_id"));
        accounts.setNumber(resultSet.getString("number"));
        accounts.setValue(resultSet.getDouble("value"));

        return accounts;
    }

    public List<Accounts> findAllAccounts() {

        List<Accounts> accountsList = new ArrayList<>();

        try ( Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ACCOUNT)) {

            while (resultSet.next()) {
                accountsList.add(accountParameters(resultSet));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return accountsList;
    }

    public Accounts findByIdAccounts(Integer id) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_FROM_ACCOUNT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return accountParameters(resultSet);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public void insertAccount(Accounts accounts) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ACCOUNT)) {
            preparedStatement.setInt(1, accounts.getClient_id());
            preparedStatement.setString(2, accounts.getNumber());
            preparedStatement.setDouble(3, accounts.getValue());
            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteAccount(Integer id) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_ACCOUNT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void  updateAccount(Accounts accounts) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SET_ACCOUNT)) {
            preparedStatement.setInt(1, accounts.getClient_id());
            preparedStatement.setString(2, accounts.getNumber());
            preparedStatement.setDouble(3, accounts.getValue());
            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public  List<String> findAccountNumberBiggerValue(Double value) {
        List<String> accountList = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_BY_VALUE)) {
            preparedStatement.setDouble(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountList.add(resultSet.getString("number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }
}
