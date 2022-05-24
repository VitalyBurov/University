package university.dao;

import university.core.entity.Group;
import university.dao.api.IGroupDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsPoolDao implements IGroupDao {

    private static final GroupsPoolDao instance = new GroupsPoolDao();

    public GroupsPoolDao() {
    }

    @Override
    public void create(Group group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO\n " +
                             "education.groups (name)\n" +
                             "VALUES (?);\n"
             );
        ) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT \n " +
                             "id, name\n" +
                             "FROM education.groups;")

        ) {
            while (resultSet.next()) {
                groups.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public Group get(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT \n " +
                             "id, name\n" +
                             "FROM education.groups\n" +
                             "WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Long id, Group group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE education.groups \n" +
                             "SET name = ? " +
                             "WHERE id = ?;"
             );
        ) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM education.groups \n" +
                             "WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Group map(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong("id"));
        group.setName(rs.getString("name"));
        return group;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static GroupsPoolDao getInstance() {
        return instance;
    }

}
