import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 3. Create a principal class named EmployeesManager.
public class EmployeesManager {
    public List<Employee> employees;

    public EmployeesManager(List<Employee> employeesToRegister) {
        this.employees = employeesToRegister;
    }

    public void removeEmployee(String name) {
        this.employees.removeIf(employee -> employee.name.equals(name));
    }

    public void increaseSalary(double percentage) {
        employees.forEach(
                employee -> employee.salary = employee.salary.multiply(BigDecimal.valueOf(1 + (percentage / 100))));
    }

    public void showEmployees() {
        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Data de Nascimento", "Salário", "Cargo");

        List<List<String>> data = employees.stream()
                .map(employee -> List.of(
                        employee.name,
                        employee.dateOfBirthFormatted("dd/MM/yyyy"),
                        employee.salaryFormatted("#,##0.00", "R$"),
                        employee.position))
                .collect(Collectors.toList());

        table.printTable("Funcionários", headers, data);
    }

    public void showEmployeesByPosition() {
        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Data de Nascimento", "Salário", "Cargo");

        Map<String, List<Employee>> employeesByPosition = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.position));

        System.out.println("\u001B[34m\nFuncionários agrupados por cargo e com acréscimo de 10% no salário :");
        employeesByPosition.forEach((position, employeeList) -> {
            table.printTable(String.format("%s", position), headers, employeeList.stream()
                    .map(employee -> List.of(
                            employee.name,
                            employee.dateOfBirthFormatted("dd/MM/yyyy"),
                            employee.salaryFormatted("#,##0.00", "R$"),
                            employee.position))
                    .collect(Collectors.toList()));

            System.out.println("\n");
        });
    }

    public void showEmployeesMonthOfBirth(List<String> months) {
        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Data de Nascimento", "Salário", "Cargo");

        List<List<String>> data = employees.stream()
                .filter(employee -> months.contains(employee.dateOfBirthFormatted("MM")))
                .map(employee -> List.of(
                        employee.name,
                        employee.dateOfBirthFormatted("dd/MM/yyyy"),
                        employee.salaryFormatted("#,##0.00", "R$"),
                        employee.position))
                .collect(Collectors.toList());

        table.printTable(String.format("Funcionários aniversariantes - Mês %s", months), headers, data);
    }

    public void showEmployeeOlder() {
        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Idade");

        List<List<String>> data = employees.stream()
                .sorted((e1, e2) -> e1.dateOfBirth.compareTo(e2.dateOfBirth))
                .limit(1)
                .map(employee -> List.of(
                        employee.name,
                        String.valueOf(employee.dateOfBirth.until(LocalDate.now()).getYears())))
                .collect(Collectors.toList());

        System.out.println("\n");
        table.printTable("Funcionário mais velho", headers, data);
    }

    public void showEmployeesAlphabeticOrder() {
        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Data de Nascimento", "Salário", "Cargo");

        List<List<String>> data = employees.stream()
                .sorted((e1, e2) -> e1.name.compareTo(e2.name))
                .map(employee -> List.of(
                        employee.name,
                        employee.dateOfBirthFormatted("dd/MM/yyyy"),
                        employee.salaryFormatted("#,##0.00", "R$"),
                        employee.position))
                .collect(Collectors.toList());

        System.out.println("\n");
        table.printTable("Funcionários em ordem alfabética", headers, data);
    }

    public void showSalaryTotal() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        BigDecimal total = employees.stream()
                .map(employee -> employee.salary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n");
        System.out.println(
                "\u001B[34mTotal da folha de pagamento: " + new DecimalFormat("R$ #,##0.00", symbols).format(total));
    }

    public void showEmployeesQtyMinSalary(double minSalary) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        BeautifulTable table = new BeautifulTable();

        List<String> headers = List.of("Nome", "Salário", "Salários mínimos", "Cargo");

        List<List<String>> data = employees.stream()
                .map(employee -> List.of(
                        employee.name,
                        employee.salaryFormatted("R$ #,##0.00", "R$"),
                        new DecimalFormat("#,##0.00", symbols).format(
                                employee.salary.divide(new BigDecimal(minSalary), 2, RoundingMode.HALF_UP)),
                        employee.position))
                .collect(Collectors.toList());

        System.out.println("\n");
        table.printTable("Quantidade de salários mínimos", headers, data);
    }
}
