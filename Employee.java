import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;

// 2 – Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
public class Employee extends Person {
    protected BigDecimal salary;
    protected String position;

    public Employee(String name, LocalDate dateOfBirth, BigDecimal salary, String position) {
        super(name, dateOfBirth);

        this.salary = salary;
        this.position = position;
    }

    protected String salaryFormatted(String pattern, String prefix) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        return String.format("%s %s", prefix, decimalFormat.format(salary));
    }
}
