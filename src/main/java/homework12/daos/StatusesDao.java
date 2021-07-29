package homework12.daos;

import homework12.database.Database;
import homework12.entities.Statuses;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusesDao {
    private static final String INSERT_INTO_STATUS = "INSERT INTO statuses (alias, description) VALUES (?,?)";
    private static final String UPDATE_SET_STATUS = "UPDATE statuses SET alias=?, description=? WHERE id=?";
    private static final String DELETE_FROM_STATUS = "DELETE FROM statuses WHERE id=?";
    private static final String SELECT_ALL_FROM_STATUS = "SELECT * FROM statuses";
    private static final String SELECT_BY_ID_FROM_STATUSES = "SELECT * FROM statuses WHERE id=?";

    private Statuses statusesParameters (ResultSet resultSet) throws SQLException {

        Statuses statuses = new Statuses();
        statuses.setId(resultSet.getInt("id"));
        statuses.setAlias(resultSet.getString("alias"));
        statuses.setDescription(resultSet.getString("description"));

        return statuses;
    }

    public List<Statuses> findAllStatuses() {

        List<Statuses> statusesList = new ArrayList<>();

        try ( Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_STATUS)){

            while (resultSet.next()) {
                statusesList.add(statusesParameters(resultSet));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return statusesList;
    }

    public Statuses findStatusesById (Integer id) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_FROM_STATUSES)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return statusesParameters(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertStatuses (Statuses statuses) {

        try ( Connection connection = Database.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_STATUS)){
            preparedStatement.setString(1, statuses.getAlias());
            preparedStatement.setString(2, statuses.getDescription());
            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateStatuses (Statuses statuses) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SET_STATUS)){
            preparedStatement.setString(1, statuses.getAlias());
            preparedStatement.setString(2, statuses.getDescription());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStatuses (Integer id) {

        try ( Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_STATUS)){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
