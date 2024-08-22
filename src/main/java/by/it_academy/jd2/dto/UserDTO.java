package by.it_academy.jd2.dto;

import by.it_academy.jd2.model.ERole;

import java.time.LocalDate;

public class UserDTO {
    private String login;
    private String password;
    private String fio;
    private LocalDate dateOfBirth;
    private LocalDate dateOfRegistration;
    private ERole role;

    public UserDTO() {
    }

    public UserDTO(String login, String password, String fio, LocalDate dateOfBirth,
                   LocalDate dateOfRegistration, ERole role) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfRegistration=" + dateOfRegistration +
                ", role='" + role + '\'' +
                '}';
    }
}
