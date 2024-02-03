import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela.
        EmployeesManager employeesManager = new EmployeesManager(
                new ArrayList<>(
                        List.of(
                                new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"),
                                new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"),
                                new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"),
                                new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"),
                                new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68),
                                        "Recepcionista"),
                                new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"),
                                new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"),
                                new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"),
                                new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85),
                                        "Eletricista"),
                                new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2799.93), "Gerente"))));

        // 3.2 – Remover o funcionário “João” da lista.
        employeesManager.removeEmployee("João");

        // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        employeesManager.showEmployees();

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista
        // de funcionários com novo valor.
        employeesManager.increaseSalary(10);

        // 3.5 e 3.6 – Agrupar os funcionários por função em um MAP, sendo a chave a
        // “função” e o valor a “lista de funcionários”. Imprimir o MAP.
        employeesManager.showEmployeesByPosition();

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        employeesManager.showEmployeesMonthOfBirth(List.of("10", "12"));

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e
        // idade.
        employeesManager.showEmployeeOlder();

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        employeesManager.showEmployeesAlphabeticOrder();

        // 3.11 – Imprimir o total dos salários dos funcionários.
        employeesManager.showSalaryTotal();

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando
        // que o salário mínimo é R$1212.00.
        employeesManager.showEmployeesQtyMinSalary(1212.00);

    }

}