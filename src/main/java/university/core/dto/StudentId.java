package university.core.dto;

import java.io.Serializable;

public class StudentId implements Serializable {
    private static final long serialVersionUID = 2L;

    private final long id;

    public StudentId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


}