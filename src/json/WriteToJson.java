package json;

import data.Department;
import data.Employee;

import java.util.Collection;

public class WriteToJson {
    public static String writePretty(Collection<Employee> employees) {
        StringBuilder stringBuilder = new StringBuilder("{").append("\n");
        if(employees.isEmpty()) return "{}";
        for (Employee employee : employees) {
            stringBuilder.append("  ").append("{").append("\n");
            stringBuilder.append("    ").append("\"id\": ").append(employee.getId()).append(",\n");
            stringBuilder.append("    ").append("\"firstName\": \"").append(employee.getFirstName()).append("\",\n");
            stringBuilder.append("    ").append("\"secondName\": \"").append(employee.getSecondName()).append("\",\n");
            stringBuilder.append("    ").append("\"birthDate\": \"").append(employee.getBirthDate().toString()).append("\",\n");
            stringBuilder.append("    ").append("\"hireDate\": \"").append(employee.getHireDate().toString()).append("\",\n");
            stringBuilder.append("    ").append("\"jobTitle\": \"").append(employee.getJobtitle()).append("\",\n");
            stringBuilder.append("    ").append("\"salary\": ").append(employee.getSalary()).append(",\n");
            Department dep = employee.getDepartment();
            stringBuilder.append("    ").append("\"department\": [\n");
            stringBuilder.append("      ").append("\"id\": ").append(dep.getId()).append(",\n");
            stringBuilder.append("      ").append("\"name\": \"").append(dep.getName()).append("\",\n");
            stringBuilder.append("      ").append("\"description\": \"").append(dep.getDescription()).append("\"\n");
            stringBuilder.append("    ").append("]\n");
            stringBuilder.append("  ").append("},\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String write(Collection<Employee> employees) {
        StringBuilder stringBuilder = new StringBuilder("{");
        if(employees.isEmpty()) return "{}";
        for (Employee employee : employees) {
            stringBuilder.append("{");
            stringBuilder.append("\"id\":").append(employee.getId()).append(",");
            stringBuilder.append("\"firstName\":\"").append(employee.getFirstName()).append("\",");
            stringBuilder.append("\"secondName\":\"").append(employee.getSecondName()).append("\",");
            stringBuilder.append("\"birthDate\":\"").append(employee.getBirthDate().toString()).append("\",");
            stringBuilder.append("\"hireDate\":\"").append(employee.getHireDate().toString()).append("\",");
            stringBuilder.append("\"jobTitle\":\"").append(employee.getJobtitle()).append("\",");
            stringBuilder.append("\"salary\":").append(employee.getSalary()).append(",");
            Department dep = employee.getDepartment();
            stringBuilder.append("\"department\":[");
            stringBuilder.append("\"id\":").append(dep.getId()).append(",");
            stringBuilder.append("\"name\":\"").append(dep.getName()).append("\",");
            stringBuilder.append("\"description\":\"").append(dep.getDescription()).append("\"");
            stringBuilder.append("]");
            stringBuilder.append("},");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
