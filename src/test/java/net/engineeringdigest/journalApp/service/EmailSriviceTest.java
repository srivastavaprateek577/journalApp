package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSriviceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
       emailService.sendEmail("Srivastavaprateek577@gmail.com","testing Java mail sender" ,"Hii , Your system and Id has been initialized for testing java protocols of smtp ");
    }
}
