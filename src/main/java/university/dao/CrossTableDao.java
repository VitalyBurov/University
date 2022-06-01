package university.dao;

import university.core.dto.CrossTable;
import university.dao.api.ICrossTableDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrossTableDao implements ICrossTableDao {

    private static final CrossTableDao instance = new CrossTableDao();

    private CrossTableDao() {
    }


    @Override
    public void create(CrossTable crossTable) {
        for (int i = 0; i < crossTable.getStudentList().size(); i++) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO education.groups_and_students" +
                                 "(group_name, student_id) \n" +
                                 "    VALUES (?, ?);"
                 );
            ) {
                preparedStatement.setString(1, crossTable.getGroupName());
                preparedStatement.setLong(2, crossTable.getStudentList().get(i));
                {
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(CrossTable crossTable) {
        for (int i = 0; i < crossTable.getStudentList().size(); i++) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "DELETE FROM education.groups_and_students" +
                                 "WHERE group_name = ?\n" +
                                 "AND student_id = ?;"
                 );
            ) {
                preparedStatement.setString(1, crossTable.getGroupName());
                preparedStatement.setLong(2, crossTable.getStudentList().get(i));
                {
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<CrossTable> readAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT " +
                             "group_name, student_id " +
                             "FROM  education.groups_and_students;"
             );) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CrossTable> map(ResultSet rs) throws SQLException {
        List<CrossTable> crossTableList = new ArrayList<>();
        CrossTable crossTable = new CrossTable();

        while (rs.next()) {
            String groupName = rs.getString("group_name");

            if (crossTable.getGroupName() != null && !groupName.equals(crossTable.getGroupName())) {
                crossTableList.add(crossTable);

                crossTable = new CrossTable();
            }

            crossTable.setGroupName(rs.getString("group_name"));
            crossTable.addId(rs.getLong("student_id"));
        }

        if (crossTable.getGroupName() != null) {
            crossTableList.add(crossTable);
        }
        return crossTableList;
    }


    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static CrossTableDao getInstance() {
        return instance;
    }
}



