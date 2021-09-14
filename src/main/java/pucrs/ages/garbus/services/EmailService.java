package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.client.GmailClient;
import pucrs.ages.garbus.client.SendGridClient;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final SendGridClient sendGridClient;

    public void sendTo(String recipient, String subject, String text) throws IOException {
        sendGridClient.sendTo(recipient, subject, text);
    }
}
