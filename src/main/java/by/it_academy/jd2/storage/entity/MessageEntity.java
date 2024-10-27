package by.it_academy.jd2.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "message", schema = "app")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageEntity {

    @Id
    private UUID id;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "mail_from")
    private String from;
    @Column(name = "mail_to")
    private String to;
    private String text;
}
