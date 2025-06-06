package com.employees;

import com.employees.application.EmployeeService;
import com.employees.domain.Employee;
import com.employees.infrastructure.BeautifulTable;
import com.employees.infrastructure.InMemoryEmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> initialEmployees = new ArrayList<>(List.of(
                new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        EmployeeService service = new EmployeeService(new InMemoryEmployeeRepository(initialEmployees));
        BeautifulTable table = new BeautifulTable();

        service.removeEmployee("João");

        service.increaseSalary(10);

        printEmployees(table, "Funcionários", service.listEmployees());

        System.out.println("\u001B[34m\nFuncionários agrupados por cargo e com acréscimo de 10% no salário :");
        service.groupByPosition().forEach((position, list) -> {
            printEmployees(table, position, list);
            System.out.println();
        });

        printEmployees(table, "Funcionários aniversariantes - Mês [10, 12]", service.employeesByBirthMonths(List.of("10", "12")));

        Employee oldest = service.getOldestEmployee();
        List<List<String>> oldestData = List.of(List.of(oldest.getName(), String.valueOf(oldest.getDateOfBirth().until(LocalDate.now()).getYears())));
        table.printTable("Funcionário mais velho", List.of("Nome", "Idade"), oldestData);

        printEmployees(table, "Funcionários em ordem alfabética", service.listAlphabetically());

        System.out.println("\n\u001B[34mTotal da folha de pagamento: " + service.totalSalary());

        table.printTable("Quantidade de salários mínimos", List.of("Nome", "Salário", "Salários mínimos", "Cargo"), service.employeesQtyMinSalary(1212.00));
    }

    private static void printEmployees(BeautifulTable table, String title, List<Employee> employees) {
        List<String> headers = List.of("Nome", "Data de Nascimento", "Salário", "Cargo");
        List<List<String>> data = employees.stream()
                .map(e -> List.of(
                        e.getName(),
                        e.formatDateOfBirth("dd/MM/yyyy"),
                        e.formatSalary("#,##0.00", "R$"),
                        e.getPosition()))
                .collect(Collectors.toList());
        table.printTable(title, headers, data);
    }
}
