package university.core.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CrossTable implements Serializable {

    private String groupName;
    private List<Long> studentIdList;

    public CrossTable(String groupName) {
        this.groupName = groupName;
    }

    public CrossTable() {
    }

    public CrossTable(String groupName, List<Long> studentIdList) {
        this.groupName = groupName;
        this.studentIdList = studentIdList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getStudentList() {
        return studentIdList;
    }

    public void addId(long a) {
        if (this.studentIdList == null) {
            this.studentIdList = new ArrayList<>();
        }

        this.studentIdList.add(a);
    }

    public void setStudentIdList(List<Long> studentIdList) {
        this.studentIdList = studentIdList;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
