public class Rates {
    private int id;
    private String position;
    private int salary;

    public Rates(int id, String position, int salary) {
        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
