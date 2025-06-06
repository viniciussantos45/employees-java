# Gestão de Funcionários

Este projeto é um sistema de gerenciamento de funcionários escrito em Java. Ele permite que você gerencie uma lista de funcionários, incluindo suas informações pessoais e salariais.

## Arquivos

O projeto agora segue uma organização em camadas. Os principais pacotes são:

- `com.employees.domain` – entidades `Person` e `Employee` e a interface `EmployeeRepository`.
- `com.employees.application` – contém a classe `EmployeeService` responsável pelas regras de negócio.
- `com.employees.infrastructure` – implementações de infraestrutura, como `InMemoryEmployeeRepository` e `BeautifulTable` para impressão no console.
- `com.employees.Main` – ponto de entrada do aplicativo que demonstra o uso das camadas.

## Uso

Para usar este projeto, crie alguns `Employee` e utilize o `EmployeeService` com um repositório de sua escolha. Um exemplo usando o repositório em memória:

```java
List<Employee> employees = new ArrayList<>();
employees.add(new Employee("João", LocalDate.of(1990, 1, 1), new BigDecimal("3000.00"), "Gerente"));
employees.add(new Employee("Maria", LocalDate.of(1995, 6, 15), new BigDecimal("2000.00"), "Desenvolvedor"));

EmployeeService service = new EmployeeService(new InMemoryEmployeeRepository(employees));
service.listEmployees();
```

> Versão do Java utilizada
>
>```bash
>java 21.0.2 2024-01-16 LTS
>Java(TM) SE Runtime Environment (build 21.0.2+13-LTS-58)
>Java HotSpot(TM) 64-Bit Server VM (build 21.0.2+13-LTS-58, mixed mode, sharing)
>```
