package by.it_academy.jd2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailForSendingDTO {
    private String from;
    private String to;
    private String text;
}
