package jdbc_text;

public class Student {
    private int id;
    private int age;
    private String name;
    private double score;
    private String birthday;
    private String insert_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getInsert_time() {
        return insert_time;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", birthday='" + birthday + '\'' +
                ", insert_time='" + insert_time + '\'' +
                '}'+'\n';
    }
}
