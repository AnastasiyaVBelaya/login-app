package by.it_academy.jd2.model;

public enum ERole {
    ADMIN("Администратор"),
    USER("Пользователь");

    private final String displayName;

    ERole(String role) {
        this.displayName = role;
    }

    public String getRole() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
