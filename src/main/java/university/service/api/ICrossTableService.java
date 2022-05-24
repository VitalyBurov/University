package university.service.api;

import university.core.dto.CrossTable;
import university.core.entity.Group;

public interface ICrossTableService {
    void addStudents(Group group);
    void deleteStudents(CrossTable crossTable);
}
