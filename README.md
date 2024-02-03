# Gestão de Funcionários

Este projeto é um sistema de gerenciamento de funcionários escrito em Java. Ele permite que você gerencie uma lista de funcionários, incluindo suas informações pessoais e salariais.

## Arquivos

O projeto consiste nos seguintes arquivos:

- `Main.java`: Arquivo principal de entrada.

- `EmployeesManager.java`: Esta é a classe principal que gerencia a lista de funcionários. Ela fornece métodos para adicionar e remover funcionários, aumentar salários, e exibir informações sobre os funcionários em vários formatos.

- `Employee.java`: Esta classe representa um funcionário individual. Ela armazena informações como nome, data de nascimento, salário e cargo.

- `Person.java`: Esta é uma classe abstrata que representa uma pessoa. A classe `Employee` herda desta classe.

- `BeautifulTable.java`: Esta classe auxiliar é usada para imprimir tabelas formatadas no console.

## Uso

Para usar este projeto, você precisa criar uma instância da classe `EmployeesManager` e adicionar funcionários a ela. Aqui está um exemplo de como você pode fazer isso:

```java
List<Employee> employees = new ArrayList<>();
employees.add(new Employee("João", LocalDate.of(1990, 1, 1), new BigDecimal("3000.00"), "Gerente"));
employees.add(new Employee("Maria", LocalDate.of(1995, 6, 15), new BigDecimal("2000.00"), "Desenvolvedor"));

EmployeesManager manager = new EmployeesManager(employees);
manager.showEmployees();
```

> Versão do Java utilizada
>
>```bash
>java 21.0.2 2024-01-16 LTS
>Java(TM) SE Runtime Environment (build 21.0.2+13-LTS-58)
>Java HotSpot(TM) 64-Bit Server VM (build 21.0.2+13-LTS-58, mixed mode, sharing)
>```
