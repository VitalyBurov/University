package university.service;

import university.core.dto.CrossTable;
import university.core.entity.Group;
import university.dao.CrossTableDao;
import university.dao.api.ICrossTableDao;
import university.service.api.ICrossTableService;

import java.util.Objects;

public class CrossTableService implements ICrossTableService {

    private static final CrossTableService instance = new CrossTableService();
    private final ICrossTableDao dao;

    public CrossTableService() {
        this.dao = CrossTableDao.getInstance();
    }

    @Override
    public void addStudents(Group group) {
        if (Objects.isNull(group)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.addStudents(group);
    }

    @Override
    public void deleteStudents(CrossTable crossTable) {
        if (Objects.isNull(crossTable)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.deleteStudents(crossTable);
    }
}
