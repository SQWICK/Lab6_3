package common.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Worker implements Comparable<Worker>, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private Double salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private Position position;
    private Organization organization;

    public Worker(Long id, String name, Coordinates coordinates, LocalDate creationDate, Double salary, 
                 LocalDate startDate, LocalDate endDate, Position position, Organization organization) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
        this.organization = organization;
    }

    public Worker() {
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public boolean isValid() {
        return name != null && !name.isEmpty() &&
               coordinates != null && coordinates.isValid() &&
               creationDate != null &&
               salary != null && salary > 0 &&
               startDate != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) &&
                Objects.equals(name, worker.name) &&
                Objects.equals(coordinates, worker.coordinates) &&
                Objects.equals(creationDate, worker.creationDate) &&
                Objects.equals(salary, worker.salary) &&
                Objects.equals(startDate, worker.startDate) &&
                position == worker.position &&
                Objects.equals(organization, worker.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, salary, startDate, position, organization);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", position=" + position +
                ", organization=" + organization +
                '}';
    }

    @Override
    public int compareTo(Worker other) {
        if (this.salary == null && other.salary == null) {
            return 0;
        }
        if (this.salary == null) {
            return -1;
        }
        if (other.salary == null) {
            return 1;
        }
        return this.salary.compareTo(other.salary);
    }
} 