public class Employee {
    private int id;
    private String name;
    private String position;
    private int salary;
    private int officeId;

    public Employee(String name, String position, int salary, int officeId) {
    this.name = name;
    this.position = position;
    this.salary = salary;
    this.officeId = officeId;

    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", office_id=" + officeId +
                '}';
    }

    public Employee() {
    }

    public Employee(int id, String name, String position, int salary, int officeId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.officeId = officeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
