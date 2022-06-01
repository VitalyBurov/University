package university.service.api;

import university.core.dto.CrossTable;

import java.util.List;

public interface ICrossTableService {
    /**
     * Получить группы и студентов
     */
    List<CrossTable> readAll();


    /**
     * Создать новую связь
     *
     * @param crossTable данные группы и списка студентов
     */
    void create(CrossTable crossTable);


    /**
     * Удалить существующую связь
     *
     * @param crossTable данные группы и списка студентов
     */
    void delete(CrossTable crossTable);


}
