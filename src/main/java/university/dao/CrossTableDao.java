package university.dao;

import university.core.dto.CrossTable;
import university.core.entity.Group;
import university.core.entity.Student;
import university.dao.api.ICrossTableDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrossTableDao implements ICrossTableDao {

    private static final CrossTableDao instance = new CrossTableDao();

    private CrossTableDao() {
    }


    @Override
    public void addStudents(Group group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO education.groups_and_students(group_name, student_id) \n" +
                             "SELECT education.groups.name, education.students.student_id\n" +
                             "FROM education.students,education.groups\n" +
                             "WHERE education.groups.name = (SELECT name\n" +
                             "FROM education.groups\n" +
                             "WHERE group_name = ?)\n" +
                             "AND education.students.student_id = ?;"
             );
        ) {
            for (Student student :  group.getStudents()) {
                preparedStatement.setString(1, group.getName());
                preparedStatement.setLong(2, student.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudents(CrossTable crossTable) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM education.groups_and_students\n" +
                             "WHERE group_name = \n" +
                             "(SELECT id FROM education.groups \n" +
                             "WHERE group_name = ?)\n" +
                             "AND student_id = \n" +
                             "(SELECT student_id FROM education.students\n" +
                             "WHERE students.student_id = ?);")) {
            for (Student student : crossTable.getStudentList()) {
                preparedStatement.setString(1,crossTable.getGroupName());
                preparedStatement.setLong(2,student.getId());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static CrossTableDao getInstance() {
        return instance;
    }
}
