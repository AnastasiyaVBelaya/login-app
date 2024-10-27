package by.it_academy.jd2.storage.entity;

import by.it_academy.jd2.model.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "login", schema = "app")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    @Id
    private String login;
    private String password;
    private String fio;
    @Column(name = "birthday")
    private LocalDate dateOfBirth;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Enumerated(EnumType.STRING)
    private ERole role;
}
