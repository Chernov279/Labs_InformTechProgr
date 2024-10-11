package lab3;

import java.util.HashMap;

public class EmployeeManager {
    private HashMap<Integer, Employee> employeeHashMap;

    public EmployeeManager() {
        employeeHashMap = new HashMap<>();
    }

    public void addEmployee(int id, Employee employee) {
        employeeHashMap.put(id, employee);
    }

    public Employee findEmployee(int id) {
        return employeeHashMap.get(id);
    }

    public void removeEmployee(int id) {
        employeeHashMap.remove(id);
    }

    public void showAllEmployees() {
        for (Integer id : employeeHashMap.keySet()) {
            System.out.println("ID: " + id + ", " + employeeHashMap.get(id).showAll());
        }
    }
}