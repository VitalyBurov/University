package university.core.dto;


import university.core.entity.Student;

import java.io.Serializable;
import java.util.List;

public class CrossTable implements Serializable {

    private String groupName;
    private List<Student> studentIdList;

    public CrossTable(String groupName) {
        this.groupName = groupName;
    }

    public CrossTable(String groupName, List<Student> studentIdList) {
        this.groupName = groupName;
        this.studentIdList = studentIdList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudentList() {
        return studentIdList;
    }

    public void setStudentIdList(List<Student> studentIdList) {
        this.studentIdList = studentIdList;
    }
}
