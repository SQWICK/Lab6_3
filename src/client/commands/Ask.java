package client.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import common.models.Address;
import common.models.Coordinates;
import common.models.Organization;
import common.models.OrganizationType;
import common.models.Position;
import common.models.Worker;

public class Ask {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public static class AskBreak extends Exception {
        public AskBreak(String message) {
            super(message);
        }
    }

    public static Worker askWorker(Long id) throws AskBreak {
        Worker worker = new Worker();
        if (id != null) {
            worker.setId(id);
        }

        System.out.println("$ New Worker $");

        // Имя
        String name;
        while (true) {
        System.out.print("name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            }
            System.err.println("\nИмя не может быть пустым. Пожалуйста, попробуйте снова.");
        }
        worker.setName(name);

        // Координаты
        System.out.println("> Coordinates Details: <");
        double x;
        while (true) {
            System.out.print("coordinates.x: ");
        try {
            x = Double.parseDouble(scanner.nextLine().trim());
                break;
        } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат coordinates.x. Пожалуйста, попробуйте снова.");
            }
        }

        int y;
        while (true) {
            System.out.print("coordinates.y: ");
        try {
            y = Integer.parseInt(scanner.nextLine().trim());
                break;
        } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат coordinates.y. Пожалуйста, попробуйте снова.");
            }
        }
        worker.setCoordinates(new Coordinates(x, y));

        // Зарплата
        double salary;
        while (true) {
            System.out.print("salary: ");
        try {
            salary = Double.parseDouble(scanner.nextLine().trim());
                if (salary >= 0) {
                    break;
                }
                System.err.println("\nЗарплата не может быть отрицательной! Пожалуйста, попробуйте снова.");
            } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат salary. Пожалуйста, попробуйте снова.");
            }
        }
        worker.setSalary(salary);

        // Дата начала работы
        LocalDate startDate;
        while (true) {
        System.out.print("startDate (Пример: 2025-01-12): ");
        String startDateStr = scanner.nextLine().trim();
        try {
                startDate = LocalDate.parse(startDateStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.err.println("\nНеверный формат даты. Пожалуйста, попробуйте снова.");
            }
        }
            worker.setStartDate(startDate);

        // Дата окончания работы
        System.out.print("Хотите добавить дату окончания работы? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            LocalDate endDate;
            while (true) {
                System.out.print("endDate (Пример: 2025-01-12): ");
                String endDateStr = scanner.nextLine().trim();
                try {
                    endDate = LocalDate.parse(endDateStr, formatter);
                    if (endDate.isAfter(startDate)) {
                        break;
                    }
                    System.err.println("\nДата окончания работы должна быть после даты начала работы. Пожалуйста, попробуйте снова!");
        } catch (DateTimeParseException e) {
                    System.err.println("\nНеверный формат даты. Пожалуйста, попробуйте снова.");
                }
            }
            worker.setEndDate(endDate);
        } else {
            worker.setEndDate(null);
        }

        // Позиция
        System.out.println("Available positions:");
        for (Position pos : Position.values()) {
            System.out.println(pos.ordinal() + 1 + ". " + pos);
        }
        Position position;
        while (true) {
        System.out.print("Введите номер должности: ");
        try {
                int positionNum = Integer.parseInt(scanner.nextLine().trim());
                if (positionNum >= 1 && positionNum <= Position.values().length) {
                    position = Position.values()[positionNum - 1];
                    break;
            }
                System.err.println("\nНеверная должность. Пожалуйста, попробуйте снова.");
        } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат номера должности. Пожалуйста, попробуйте снова.");
            }
        }
        worker.setPosition(position);

        // Организация
        System.out.print("Хотите добавить организацию? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            Organization organization = askOrganization();
            worker.setOrganization(organization);
        }

        return worker;
    }

    private static Organization askOrganization() throws AskBreak {
        System.out.println("> Organization Details: <");

        // Годовой оборот
        double turnover;
        while (true) {
            System.out.print("Annual turnover: ");
        try {
            turnover = Double.parseDouble(scanner.nextLine().trim());
                if (turnover > 0) {
                    break;
                }
                System.err.println("\nДолжно быть положительным числом и не равным нулю. Пожалуйста, попробуйте снова.");
        } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат годового оборота. Пожалуйста, попробуйте снова.");
            }
        }

        // Тип организации
        System.out.println("Available organization types:");
        for (OrganizationType type : OrganizationType.values()) {
            System.out.println(type.ordinal() + 1 + ". " + type);
        }
        OrganizationType type;
        while (true) {
        System.out.print("Введите номер типа организации: ");
        try {
                int typeNum = Integer.parseInt(scanner.nextLine().trim());
                if (typeNum >= 1 && typeNum <= OrganizationType.values().length) {
                    type = OrganizationType.values()[typeNum - 1];
                    break;
                }
                System.err.println("\nНеверный тип организации. Пожалуйста, попробуйте снова.");
        } catch (NumberFormatException e) {
                System.err.println("\nНеверный формат номера типа организации. Пожалуйста, попробуйте снова.");
            }
        }

        // Адрес
        System.out.print("Хотите добавить адрес? (y/n): ");
        Address address = null;
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("> Address Information <");

            String zipCode;
            while (true) {
            System.out.print("Zip code: ");
                zipCode = scanner.nextLine().trim();
                if (!zipCode.isEmpty()) {
                    break;
                }
                System.err.println("\nКод почтового индекса не может быть пустым. Пожалуйста, попробуйте снова.");
            }

            String street;
            while (true) {
            System.out.print("Street: ");
                street = scanner.nextLine().trim();
                if (!street.isEmpty()) {
                    break;
                }
                System.err.println("\nУлица не может быть пустой. Пожалуйста, попробуйте снова.");
            }

            address = new Address(street, zipCode);
        }

        return new Organization(turnover, type, address);
    }
}
