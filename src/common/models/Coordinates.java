package common.models;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double x;
    private Integer y;

    public Coordinates(Double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public boolean isValid() {
        return x != null && y != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
} 