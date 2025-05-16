package common.models;

public enum OrganizationType {
    PUBLIC,
    TRUST,
    PRIVATE_LIMITED_COMPANY;

    public static OrganizationType fromString(String value) {
        if (value == null) return null;
        try {
            return OrganizationType.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String names() {
        StringBuilder organizationList = new StringBuilder();
        for (OrganizationType org : OrganizationType.values()) {
            organizationList.append(org.name()).append(", ");
        }
        return organizationList.substring(0, organizationList.length() - 2);
    }
} 