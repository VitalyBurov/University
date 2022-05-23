package university.service;

import university.core.entity.Student;
import university.dao.IStudentDao;
import university.dao.StudentPoolDao;
import university.service.api.IStudentService;

import java.util.List;
import java.util.Objects;

public class StudentService implements IStudentService {

    private static final StudentService instance = new StudentService();
    private final IStudentDao dao;

    public StudentService() {
        this.dao =StudentPoolDao.getInstance();
    }

    @Override
    public void create(Student student) {
        if (Objects.isNull(student)) {
          throw new IllegalArgumentException("Data is empty!!!");
        }

        if(StudentPoolDao.getInstance().get(student.getId()) != null) {
            throw new IllegalArgumentException("Student is already exist!!!");
        }

        dao.create(student);
    }

    @Override
    public List<Student> getAll() {
        return dao.getAll();
    }

    @Override
    public Student get(Long id) {
        return dao.get(id);
    }

    @Override
    public void update(Long id, Student student) {
        if (Objects.isNull(id) || Objects.isNull(student)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.update(id, student);
    }

    @Override
    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.delete(id);
    }

    public static StudentService getInstance() {
        return instance;
    }

}