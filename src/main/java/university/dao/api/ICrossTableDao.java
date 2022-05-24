package university.dao.api;

import university.core.dto.CrossTable;
import university.core.entity.Group;

public interface ICrossTableDao extends AutoCloseable{
    void addStudents(Group group);
    void deleteStudents(CrossTable crossTable);

}
