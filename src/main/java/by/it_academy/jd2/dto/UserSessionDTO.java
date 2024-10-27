package by.it_academy.jd2.dto;

import by.it_academy.jd2.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSessionDTO {
    private String login;
    private String fio;
    private LocalDate dateOfBirth;
    private ERole role;
}
