package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.MailForGettingDTO;
import by.it_academy.jd2.dto.MailForSendingDTO;

import java.util.List;


public interface IMailService {
    void create(MailForSendingDTO mail);

    List<MailForGettingDTO> get();

    List<MailForGettingDTO> getByLogin(String login);
}
