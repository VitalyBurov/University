package university.dao;

import java.util.List;

public interface ICRUDDao<T,ID> extends AutoCloseable {

    /**
     * Создать объект
     * @param item студент или группа
     * @return
     */
    void create(T item);

    /**
     * Получить все объекты
     * @return
     */
    List<T> getAll();

    /**
     * Получить все объекты
     * @param id уникальный номер объекта
     * @return
     */
    T get(Long id);

    /**
     * Обновить  объект
     * @param item студент или группа
     * @param id уникальный номер объекта
     * @return
     */
    void update(ID id, T item);

    /**
     * Создать объект
     * @param id уникальный номер объекта
     * @void
     */
    void delete(ID id);

}
