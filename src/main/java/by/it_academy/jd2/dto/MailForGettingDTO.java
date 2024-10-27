package by.it_academy.jd2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MailForGettingDTO {
    private LocalDateTime createAt;
    private String from;
    private String text;
}

