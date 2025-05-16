package common.models;

import java.io.Serializable;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double annualTurnover;
    private OrganizationType type;
    private Address address;

    public Organization(Double annualTurnover, OrganizationType type, Address address) {
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.address = address;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isValid() {
        return annualTurnover != null && annualTurnover > 0 &&
               type != null &&
               address != null && address.isValid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(annualTurnover, that.annualTurnover) &&
               type == that.type &&
               Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annualTurnover, type, address);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "annualTurnover=" + annualTurnover +
                ", type=" + type +
                ", address=" + address +
                '}';
    }
} 