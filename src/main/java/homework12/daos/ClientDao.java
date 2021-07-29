package homework12.daos;

import homework12.database.Database;
import homework12.entities.ClientStatus;
import homework12.entities.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private static final String SELECT_ALL_FROM_CLIENTS = "SELECT * FROM clients";
    private static final String INSERT_INTO_CLIENTS = "INSERT INTO clients (name,email,phone,about,age)" + "VALUES (?,?,?,?,?)";
    private static final String UPDATE_SET_CLIENTS = "UPDATE clients SET name=?, email=?, phone=?, about=?, age=? WHERE id=?";
    private static final String DELETE_FROM_CLIENTS = "DELETE FROM clients WHERE id=?";
    private static final String SELECT_BY_PHONE_CLIENTS = "SELECT * FROM clients WHERE phone =?";
    private static final String SELECT_CLIENTS_JOIN_ACCOUNTS = "SELECT c.* FROM clients AS c " + "INNER JOIN accounts AS a ON c.id = a.client_id";
    private static final String SELECT_NAME_EMAIL_ALIAS = "SELECT name,email,alias FROM client_status AS cs " + "JOIN clients AS c ON cs.client_id = c.id " + "JOIN statuses AS s ON cs.status_id = s.id WHERE age>18";
    private static final String SELECT_BY_ID_FROM_CLIENTS = "SELECT * FROM clients WHERE id=?";

    private Clients clientParameters(ResultSet resultSet) throws SQLException {

        Clients client = new Clients();
        client.setId(resultSet.getInt("id"));
        client.setName(resultSet.getString("name"));
        client.setEmail(resultSet.getString("email"));
        client.setPhone(resultSet.getLong("phone"));
        client.setAbout(resultSet.getString("about"));
        client.setAge(resultSet.getInt("age"));

        return client;
    }

    public List<Clients> findAllClients() {
        List<Clients> resultList = new ArrayList<>();

        try ( Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CLIENTS);

            while (resultSet.next()) {
                resultList.add(clientParameters(resultSet));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return resultList;
    }

    public Clients findClientsById (Integer id) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_FROM_CLIENTS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return clientParameters(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public Clients findClientsByPhone (Long phone) {

        try ( Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_PHONE_CLIENTS)) {
            statement.setLong(12, phone);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return clientParameters(resultSet);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    public void insertClients(Clients clients) {

        try ( Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CLIENTS)) {
            statement.setString(1, clients.getName());
            statement.setString(2, clients.getEmail());
            statement.setLong(3, clients.getPhone());
            statement.setString(4, clients.getAbout());
            statement.setInt(5,clients.getAge());
            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void deleteClients(Integer id) {

        try ( Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_CLIENTS)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateClients(Clients clients) {

        try ( Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SET_CLIENTS)) {
            statement.setString(1, clients.getName());
            statement.setString(2, clients.getEmail());
            statement.setLong(3, clients.getPhone());
            statement.setString(4, clients.getAbout());
            statement.setInt(5,clients.getAge());
            statement.setInt(6, clients.getId());
            statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Clients> findAllClientsWhereIDClientEqualsAccountsClientId () {

        List<Clients> clientsList = new ArrayList<>();

        try ( Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_CLIENTS_JOIN_ACCOUNTS)) {

            while (resultSet.next()) {
                clientsList.add(clientParameters(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientsList;
    }

    public List<ClientStatus> findNameEmailAlias() {

        List<ClientStatus> clientsList = new ArrayList<>();

        try ( Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_NAME_EMAIL_ALIAS)) {

            while (resultSet.next()) {
                ClientStatus clientStatus = new ClientStatus();
                clientStatus.setName(resultSet.getString("name"));
                clientStatus.setEmail(resultSet.getString("email"));
                clientStatus.setAlias(resultSet.getString("alias"));
                clientsList.add(clientStatus);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return clientsList;
    }
}
