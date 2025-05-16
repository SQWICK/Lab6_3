package server.collection;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import common.file_handlers.XMLReader;
import common.models.Worker;

public class CollectionManager {
    private LinkedHashSet<Worker> workers;
    private final LocalDate initializationDate;
    private final XMLReader xmlReader;
    private Long nextId;
    private final String filePath;

    public CollectionManager(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
        this.filePath = xmlReader.getFilePath();
        this.workers = new LinkedHashSet<>();
        this.initializationDate = LocalDate.now();
        this.nextId = 1L;
        loadCollection(); // Загружаем коллекцию при создании
    }

    private void loadCollection() {
        try {
            LinkedHashSet<Worker> loadedWorkers = xmlReader.readFile();
            if (loadedWorkers != null && !loadedWorkers.isEmpty()) {
                this.workers = loadedWorkers;
                // Обновляем nextId на максимальный ID + 1
                this.nextId = loadedWorkers.stream()
                    .mapToLong(Worker::getId)
                    .max()
                    .orElse(0) + 1;
                System.out.println("Загружено " + loadedWorkers.size() + " работников из XML файла");
            } else {
                System.out.println("XML файл пуст или не содержит работников");
            }
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Long getNextId() {
        return nextId++;
    }

    public void setCollection(LinkedHashSet<Worker> workers) {
        this.workers = workers;
    }

    public String getInfo() {
        return String.format("Type: %s\nРазмер: %d\nДата инициализации: %s",
                workers.getClass().getSimpleName(),
                workers.size(),
                initializationDate);
    }

    public void add(Worker worker) {
        if (worker.getId() == null) {
            worker.setId(getNextId());
        }
        workers.add(worker);
    }

    public void update(Long id, Worker worker) {
        workers.removeIf(w -> w.getId().equals(id));
        workers.add(worker);
    }

    public void remove(Long id) {
        workers.removeIf(w -> w.getId().equals(id));
    }

    public void clear() {
        workers.clear();
    }

    public Worker getById(Long id) {
        return workers.stream()
                .filter(w -> w.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Set<Worker> getCollection() {
        return workers;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFormattedCollection() {
        if (workers.isEmpty()) {
            return "Collection is empty!";
        }

        StringBuilder result = new StringBuilder();
        int width = 77; // Общая ширина рамки
        String horizontalLine = "+" + "-".repeat(width - 2);
        
        result.append(horizontalLine).append("\n");
        result.append("| Collection Elements").append(" ".repeat(width - 21)).append("\n");
        result.append(horizontalLine).append("\n");

        for (Worker worker : workers) {
            result.append("| Worker #").append(worker.getId()).append(" ".repeat(width - 11)).append("\n");
            result.append(horizontalLine).append("\n");
            result.append(String.format("| Name: %-" + (width - 8) + "s\n", worker.getName()));
            result.append("| Coordinates:\n");
            result.append(String.format("|   X: %-" + (width - 7) + ".2f\n", worker.getCoordinates().getX()));
            result.append(String.format("|   Y: %-" + (width - 7) + "d\n", worker.getCoordinates().getY()));
            result.append(String.format("| Creation Date: %-" + (width - 15) + "s\n", worker.getCreationDate()));
            result.append(String.format("| Salary: %-" + (width - 10) + ".2f\n", worker.getSalary()));
            result.append(String.format("| Start Date: %-" + (width - 13) + "s\n", worker.getStartDate()));
            if (worker.getEndDate() != null) {
                result.append(String.format("| End Date: %-" + (width - 12) + "s\n", worker.getEndDate()));
            }
            result.append(String.format("| Position: %-" + (width - 12) + "s\n", worker.getPosition()));
            
            if (worker.getOrganization() != null) {
                result.append("| Organization:\n");
                result.append(String.format("|   Annual Turnover: %-" + (width - 21) + ".2f\n", 
                    worker.getOrganization().getAnnualTurnover()));
                result.append(String.format("|   Type: %-" + (width - 9) + "s\n", 
                    worker.getOrganization().getType()));
                if (worker.getOrganization().getAddress() != null) {
                    result.append(String.format("|   Address: %-" + (width - 13) + "s\n", 
                        worker.getOrganization().getAddress().getStreet()));
                    result.append(String.format("|   ZIP Code: %-" + (width - 13) + "s\n", 
                        worker.getOrganization().getAddress().getZipCode()));
                }
            }
            result.append(horizontalLine).append("\n");
        }
        return result.toString();
    }

    public void removeGreater(Worker worker) {
        workers.removeIf(w -> w.compareTo(worker) > 0);
    }

    public void removeLower(Worker worker) {
        workers.removeIf(w -> w.compareTo(worker) < 0);
    }

    public Worker getMaxByName() {
        return workers.stream()
                .max(Comparator.comparing(Worker::getName))
                .orElse(null);
    }

    public List<Worker> filterBySalary(Double salary) {
        return workers.stream()
                .filter(worker -> worker.getSalary() != null && worker.getSalary() > salary)
                .toList();
    }

    public List<LocalDate> getDescendingStartDates() {
        return workers.stream()
                .map(Worker::getStartDate)
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public void removeLower(Long id) {
        Worker worker = getById(id);
        if (worker != null) {
            workers.removeIf(w -> w.getId() < id);
        }
    }

    public void removeGreater(Long id) {
        Worker worker = getById(id);
        if (worker != null) {
            workers.removeIf(w -> w.getId() > id);
        }
    }
} 