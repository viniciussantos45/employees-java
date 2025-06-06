package com.employees.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;

public class Employee extends Person {
    private BigDecimal salary;
    private String position;

    public Employee(String name, LocalDate dateOfBirth, BigDecimal salary, String position) {
        super(name, dateOfBirth);
        this.salary = salary;
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public String formatDateOfBirth(String pattern) {
        return super.formatDateOfBirth(pattern);
    }

    public String formatSalary(String pattern, String prefix) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        return String.format("%s %s", prefix, decimalFormat.format(salary));
    }
}
