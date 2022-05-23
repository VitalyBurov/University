package university.main;

import university.core.entity.Student;
import university.dao.GroupsPoolDao;
import university.dao.StudentPoolDao;

public class Main1 {

    public static void main(String[] args) {

        StudentPoolDao studentPoolDao = StudentPoolDao.getInstance();
        // Create and add Students
        /*studentPoolDao.create(new Student("Ivanov Ivan", 23, 5.0, false));
        studentPoolDao.create(new Student("Petrov Petr", 20, 3.2, true));
        studentPoolDao.create(new Student("Sidorov Nickolay", 19, 4.5, false));
        */


        // Get all Students
        /*
        List<Student> students = studentPoolDao.getAll();
        for (Student student : students) {
            System.out.println(student);
        }

         */

        //Get a single Student
        /*
        Student ivanov = studentPoolDao.get(1L);
        System.out.println(ivanov);
         */

        //Update a Student
        /*
        Student ivanovaSviatlana = new Student("Ivanova Sviatlana", 21, 5.0, false);
        studentPoolDao.update(1L,ivanovaSviatlana);
        System.out.println(studentPoolDao.get(1L));

         */

        //Delete a Student
        /*
        studentPoolDao.delete(6L);

         */

        GroupsPoolDao groupsPoolDao = GroupsPoolDao.getInstance();
        // Create and add Groups
        /*
        groupsPoolDao.create(new Group("10B"));
        groupsPoolDao.create(new Group("10C"));
        groupsPoolDao.create(new Group("10A"));

         */


        // Get all Groups
        /*
        List<Group> groups = groupsPoolDao.getAll();
        for (Group group : groups) {
            System.out.println(group);
        }

         */

        //Get a single group
        System.out.println(groupsPoolDao.get(2L));


        //Update a group
        /*
        Group group1 = new Group("11K");
        groupsPoolDao.update(2L, group1);

         */


        //Delete a group
        groupsPoolDao.delete(1L);


    }

}