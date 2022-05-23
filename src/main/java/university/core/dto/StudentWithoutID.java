package university.core.dto;

public class StudentWithoutID {

    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public StudentWithoutID(String name, int age, double score, boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private int age;
        private double score;
        private boolean olympicGamer;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setScore(double score) {
            this.score = score;
            return this;
        }

        public Builder setOlympicGamer(boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
            return this;
        }

        public StudentWithoutID build() {
            return new StudentWithoutID(name, age, score, olympicGamer);
        }
    }

}
