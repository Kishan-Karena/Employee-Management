import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name;
    int age;
    String department;
    double salary;

    public Employee(int id, String name, int age, String department, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

class Ceo {

    private ArrayList<Employee> employeeList=new ArrayList<>();
    private String fileName; 

    public Ceo() {
        employeeList = new ArrayList<>();
        fileName = "employees.txt"; 
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee details added successfully");
    }

    public void removeEmployee(int id) {
        if (employeeList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        int index = -1;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                index = employeeList.indexOf(employee);
                break;
            }
        }

        if (index == -1) {
            System.out.println("Employee ID " + id + " not found");
        } else {
            employeeList.remove(index);
            System.out.println("Employee removed successfully");
        }
    }

    public void showDetailsById(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Age:  " + employee.getAge());
                System.out.println("Department: "  + employee.getDepartment());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println();
                return;
            }
        }
        System.out.println("Employee ID " + id + " not found!");
    }

    public void sortBySalary() {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        });
        System.out.println("Employees sorted by salary");
    }

    public void showAllDetails() {
        if (employeeList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        for (Employee employee : employeeList) {
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Age:  " + employee.getAge());
            System.out.println("Department: "  + employee.getDepartment());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println();
        }
    }

    public void clearList() {
        employeeList.clear();
        System.out.println("Employee list cleared");
    }

    public void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Employee employee : employeeList) {
                fileWriter.write(employee.getId() + "," + employee.getName() + "," + employee.getAge() + "," + employee.getDepartment() + "," + employee.getSalary() + "\n");
            }
            System.out.println("Employees saved to file successfully");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void displayFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine())!= null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String department = parts[3];
                double salary = Double.parseDouble(parts[4]);
                employeeList.add(new Employee(id, name, age, department, salary));
            }
            System.out.println("Employees displayed from file successfully");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}

public class P3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ceo ceo = new Ceo();
        int choice;

        do {
            System.out.println("** Office Management System **");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee (by ID)");
            System.out.println("3. Show Details by ID");
            System.out.println("4. Sort Employees by Salary");
            System.out.println("5. Show All Employee Details");
            System.out.println("6. Save Employees to File");
            System.out.println("7. Display Employees from File");
            System.out.println("8. Clear Employee List");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ceo.addEmployee(getEmployeeDetails());
                    break;
                case 2:
                    System.out.print("Enter Employee ID to remove: ");
                    int id = scanner.nextInt();
                    ceo.removeEmployee(id);
                    break;
                case 3:
                    System.out.print("Enter Employee ID to showdetails: ");
                    int idToShow = scanner.nextInt();
                    ceo.showDetailsById(idToShow);
                    break;
                case 4:
                    ceo.sortBySalary();
                    break;
                case 5:
                    ceo.showAllDetails();
                    break;
                case 6:
                    ceo.saveToFile();
                    break;
                case 7:
                    ceo.displayFromFile();
                    break;
                case 8:
                    ceo.clearList();
                    break;
                case 9:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

        } while (choice != 9);

        scanner.close();

    }

    private static Employee getEmployeeDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Employee Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); 

        return new Employee(id, name, age, department, salary);
    }
}