package by.it_academy.jd2.service;

import by.it_academy.jd2.controller.listener.StatisticsListener;
import by.it_academy.jd2.dto.MailForGettingDTO;
import by.it_academy.jd2.exceptions.MessageSendException;
import by.it_academy.jd2.exceptions.UserNotFoundException;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.dto.MailForSendingDTO;
import by.it_academy.jd2.storage.api.IMailStorage;
import by.it_academy.jd2.storage.entity.MessageEntity;
import by.it_academy.jd2.service.factory.ServiceFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MailService implements IMailService {

    private final IMailStorage mailStorage;

    public MailService(IMailStorage mailStorage) {
        this.mailStorage = mailStorage;
    }

    @Override
    public void create(MailForSendingDTO mail) {
        UserValidator userValidator = ServiceFactory.getUserValidator();

        if (!userValidator.userExists(mail.getTo())) {
            throw new UserNotFoundException("Получатель с таким логином не найден.");
        }

        try {
            MessageEntity entity = new MessageEntity();
            entity.setId(UUID.randomUUID());
            entity.setCreateAt(LocalDateTime.now());
            entity.setFrom(mail.getFrom());
            entity.setTo(mail.getTo());
            entity.setText(mail.getText());
            this.mailStorage.create(entity);

            StatisticsListener.incrementMessageCount();
        } catch (Exception e) {
            throw new MessageSendException("Не удалось отправить сообщение: " + e.getMessage());
        }
    }

    @Override
    public List<MailForGettingDTO> get() {
        List<MessageEntity> messages = this.mailStorage.get();
        return messages.stream()
                .map(this::convertToMailForGettingDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MailForGettingDTO> getByLogin(String login) {
        List<MessageEntity> messages = this.mailStorage.getByLogin(login);
        return messages.stream()
                .map(this::convertToMailForGettingDTO)
                .collect(Collectors.toList());
    }

    private MailForGettingDTO convertToMailForGettingDTO(MessageEntity message) {
        return MailForGettingDTO.builder()
                .createAt(message.getCreateAt())
                .from(message.getFrom())
                .text(message.getText())
                .build();
    }
}
