package university.service;

import university.core.entity.Group;
import university.dao.GroupsPoolDao;
import university.dao.IGroupDao;
import university.service.api.IGroupService;

import java.util.List;
import java.util.Objects;

public class GroupService implements IGroupService {

    private static final GroupService instance = new GroupService();
    private final IGroupDao dao;

    public GroupService() {
        this.dao = GroupsPoolDao.getInstance();
    }

    @Override
    public void create(Group group) {
        if (Objects.isNull(group)) {
          throw new IllegalArgumentException("Data is empty!!!");
        }

        if(GroupsPoolDao.getInstance().get(group.getId()) != null) {
            throw new IllegalArgumentException("Student is already exist!!!");
        }

        dao.create(group);
    }

    @Override
    public List<Group> getAll() {
        return dao.getAll();
    }

    @Override
    public Group get(Long id) {
        return dao.get(id);
    }

    @Override
    public void update(Long id, Group group) {
        if (Objects.isNull(id) || Objects.isNull(group)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.update(id, group);
    }

    @Override
    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Data is empty!!!");
        }
        dao.delete(id);
    }

    public static GroupService getInstance() {
        return instance;
    }

}