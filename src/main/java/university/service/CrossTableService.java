package university.service;

import university.core.dto.CrossTable;
import university.dao.CrossTableDao;
import university.dao.api.ICrossTableDao;
import university.service.api.ICrossTableService;

import java.util.List;
import java.util.Objects;

public class CrossTableService implements ICrossTableService {

    private static final CrossTableService instance = new CrossTableService();
    private final ICrossTableDao dao;

    public CrossTableService() {
        this.dao = CrossTableDao.getInstance();
    }


    @Override
    public List<CrossTable> readAll() {
        return dao.readAll();
    }

    @Override
    public void create(CrossTable crossTable) {
        if (Objects.isNull(crossTable)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.create(crossTable);
    }


    @Override
    public void delete(CrossTable crossTable) {
        if (Objects.isNull(crossTable)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.delete(crossTable);
    }

    public static CrossTableService getInstance() {
        return instance;
    }
}
