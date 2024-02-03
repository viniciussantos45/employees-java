import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 1 – Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
public class Person {
    protected String name;
    protected LocalDate dateOfBirth;

    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    protected String dateOfBirthFormatted(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return dateOfBirth.format(formatter);
    }
}
