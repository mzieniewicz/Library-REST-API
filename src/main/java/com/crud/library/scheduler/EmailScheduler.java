package com.crud.library.scheduler;

import com.crud.library.config.AdminConfig;
import com.crud.library.domain.Mail;
import com.crud.library.service.DbService;
import com.crud.library.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Your library: New Year's mail";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Scheduled(cron = "0 0 0 15 1 ?")
    public void sendInformationEmail() {

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "*New Year's wishes*\n" +
                        "Thank you for joining our library.\n" +
                        "We would like to inform you that the fee for the annual maintenance of a library account is PLN 10.\n" +
                        "Please pay it at your next visit to the library. This fee is entirely dedicated to the purchase of new books.\n" +
                        "Best Regards.\n" + "The Library"));
    }
}
