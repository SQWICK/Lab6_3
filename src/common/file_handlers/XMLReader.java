package common.file_handlers;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.models.Coordinates;
import common.models.Position;
import common.models.Worker;

public class XMLReader {
    private final String filePath;
    private final DateTimeFormatter formatter;

    public XMLReader(String filePath) {
        this.filePath = filePath;
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public String getFilePath() {
        return filePath;
    }

    public LinkedHashSet<Worker> readFile() {
        LinkedHashSet<Worker> workers = new LinkedHashSet<>();
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                System.out.println("XML файл пуст или не существует");
                return workers;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList workerList = document.getElementsByTagName("worker");
            System.out.println("Начало парсинга работников");
            System.out.println("Всего найдено работников: " + workerList.getLength());

            for (int i = 0; i < workerList.getLength(); i++) {
                Element workerElement = (Element) workerList.item(i);
                System.out.println("\nНайден блок работника #" + (i + 1) + ":\n");
                try {
                    Worker worker = parseWorker(workerElement);
                    if (worker != null) {
                        workers.add(worker);
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка при парсинге работника: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            System.out.println("Успешно добавлено работников: " + workers.size());
            if (workers.isEmpty()) {
                System.out.println("XML файл пуст или не содержит работников");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при чтении XML файла: " + e.getMessage());
            e.printStackTrace();
        }
        return workers;
    }

    private Worker parseWorker(Element workerElement) {
        try {
            Worker worker = new Worker();
            
            // Парсинг ID
            long id = Long.parseLong(getElementText(workerElement, "id"));
            System.out.println("    <id>" + id + "</id>");
            worker.setId(id);

            // Парсинг имени
            String name = getElementText(workerElement, "name");
            System.out.println("    <name>" + name + "</name>");
            worker.setName(name);

            // Парсинг координат
            Element coordinatesElement = (Element) workerElement.getElementsByTagName("coordinates").item(0);
            double x = Double.parseDouble(getElementText(coordinatesElement, "x"));
            int y = Integer.parseInt(getElementText(coordinatesElement, "y"));
            System.out.println("    <coordinates>");
            System.out.println("      <x>" + x + "</x>");
            System.out.println("      <y>" + y + "</y>");
            System.out.println("    </coordinates>");
            Coordinates coordinates = new Coordinates(x, y);
            worker.setCoordinates(coordinates);

            // Парсинг даты создания
            String creationDateStr = getElementText(workerElement, "creationDate");
            System.out.println("Парсинг даты создания: " + creationDateStr);
            LocalDate creationDate = LocalDate.parse(creationDateStr, formatter);
            worker.setCreationDate(creationDate);

            // Парсинг зарплаты
            double salary = Double.parseDouble(getElementText(workerElement, "salary"));
            System.out.println("    <salary>" + salary + "</salary>");
            worker.setSalary(salary);

            // Парсинг даты начала работы
            String startDateStr = getElementText(workerElement, "startDate");
            System.out.println("    <startDate>" + startDateStr + "</startDate>");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            worker.setStartDate(startDate);

            // Парсинг позиции
            String positionStr = getElementText(workerElement, "position");
            System.out.println("    <position>" + positionStr + "</position>");
            Position position = Position.valueOf(positionStr);
            worker.setPosition(position);

            return worker;
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге работника: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getElementText(Element parent, String tagName) {
        Element element = (Element) parent.getElementsByTagName(tagName).item(0);
        return element != null ? element.getTextContent() : "";
    }
} 