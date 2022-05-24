package university.dao;

import university.core.entity.Student;
import university.dao.api.IStudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentPoolDao implements IStudentDao {

    private static final StudentPoolDao instance = new StudentPoolDao();

    public StudentPoolDao() {
    }

    @Override
    public void create(Student student) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO\n " +
                             "education.students (name, age, score, olympic_gamer)\n" +
                             "VALUES (?, ?, ?, ?);\n"
             );
        ) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setDouble(3, student.getScore());
            preparedStatement.setBoolean(4, student.isOlympicGamer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT \n " +
                             "id, name, age, score, olympic_gamer\n" +
                             "FROM education.students;")

        ) {
            while (resultSet.next()) {
                students.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student get(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT \n " +
                             "id, name, age, score, olympic_gamer\n" +
                             "FROM education.students\n" +
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
    public void update(Long id, Student student) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE education.students \n" +
                             "SET name = ? " +
                             "WHERE id = ?;"
             );
        ) {
            preparedStatement.setString(1, student.getName());
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
                     "DELETE FROM education.students \n" +
                             "WHERE id = ?;");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympic_gamer"));
        return student;
    }

    @Override
    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static StudentPoolDao getInstance() {
        return instance;
    }

}
