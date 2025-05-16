package common.file_handlers;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import common.models.Coordinates;
import common.models.Worker;

public class XMLWriter {
    private final DateTimeFormatter formatter;

    public XMLWriter() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public void write(Set<Worker> workers, String filePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Создаем корневой элемент
        Element rootElement = document.createElement("workers");
        document.appendChild(rootElement);

        // Добавляем каждого работника
        for (Worker worker : workers) {
            Element workerElement = createWorkerElement(document, worker);
            rootElement.appendChild(workerElement);
        }

        // Записываем в файл
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    private Element createWorkerElement(Document document, Worker worker) {
        Element workerElement = document.createElement("worker");

        // ID
        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(String.valueOf(worker.getId())));
        workerElement.appendChild(idElement);

        // Имя
        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(worker.getName()));
        workerElement.appendChild(nameElement);

        // Координаты
        Element coordinatesElement = document.createElement("coordinates");
        Coordinates coordinates = worker.getCoordinates();
        
        Element xElement = document.createElement("x");
        xElement.appendChild(document.createTextNode(String.valueOf(coordinates.getX())));
        coordinatesElement.appendChild(xElement);
        
        Element yElement = document.createElement("y");
        yElement.appendChild(document.createTextNode(String.valueOf(coordinates.getY())));
        coordinatesElement.appendChild(yElement);
        
        workerElement.appendChild(coordinatesElement);

        // Дата создания
        Element creationDateElement = document.createElement("creationDate");
        creationDateElement.appendChild(document.createTextNode(worker.getCreationDate().format(formatter)));
        workerElement.appendChild(creationDateElement);

        // Зарплата
        Element salaryElement = document.createElement("salary");
        salaryElement.appendChild(document.createTextNode(String.valueOf(worker.getSalary())));
        workerElement.appendChild(salaryElement);

        // Дата начала работы
        Element startDateElement = document.createElement("startDate");
        startDateElement.appendChild(document.createTextNode(worker.getStartDate().format(formatter)));
        workerElement.appendChild(startDateElement);

        // Позиция
        Element positionElement = document.createElement("position");
        positionElement.appendChild(document.createTextNode(worker.getPosition().toString()));
        workerElement.appendChild(positionElement);

        return workerElement;
    }
}