package com.employees.application;

import com.employees.domain.Employee;
import com.employees.domain.EmployeeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void removeEmployee(String name) {
        repository.getEmployees().removeIf(e -> e.getName().equals(name));
    }

    public void increaseSalary(double percentage) {
        repository.getEmployees().forEach(e ->
                e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1 + (percentage / 100)))));
    }

    public List<Employee> listEmployees() {
        return repository.getEmployees();
    }

    public Map<String, List<Employee>> groupByPosition() {
        return repository.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getPosition));
    }

    public List<Employee> employeesByBirthMonths(List<String> months) {
        return repository.getEmployees().stream()
                .filter(e -> months.contains(e.formatDateOfBirth("MM")))
                .collect(Collectors.toList());
    }

    public Employee getOldestEmployee() {
        return repository.getEmployees().stream()
                .min(Comparator.comparing(Employee::getDateOfBirth))
                .orElse(null);
    }

    public List<Employee> listAlphabetically() {
        return repository.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
    }

    public BigDecimal totalSalary() {
        return repository.getEmployees().stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<List<String>> employeesQtyMinSalary(double minSalary) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);

        return repository.getEmployees().stream()
                .map(e -> List.of(
                        e.getName(),
                        e.formatSalary("#,##0.00", "R$"),
                        format.format(e.getSalary().divide(new BigDecimal(minSalary), 2, RoundingMode.HALF_UP)),
                        e.getPosition()))
                .collect(Collectors.toList());
    }
}
