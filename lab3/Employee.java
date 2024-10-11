package lab3;

public class Employee {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String showAll() {
        return "Employee{name='" + name + "', position='" + position + "', salary=" + salary + "}";
    }
}