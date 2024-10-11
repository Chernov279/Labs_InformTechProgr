package lab3;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Employee employee1 = new Employee("Ivanov Ivan", "Developer", 60000);
        Employee employee2 = new Employee("Petrov Petr", "Manager", 80000);
        Employee employee3 = new Employee("Aleksandrova Aleksandra", "Designer", 50000);

        manager.addEmployee(10, employee1);
        manager.addEmployee(15, employee2);
        manager.addEmployee(23, employee3);

        System.out.println("ID 10: " + manager.findEmployee(10).showAll());
        System.out.println("ID 15: " + manager.findEmployee(15).showAll());

        System.out.println("All employees:");
        manager.showAllEmployees();

        manager.removeEmployee(23);
        System.out.println("removed: " + manager.findEmployee(23));
    }
}