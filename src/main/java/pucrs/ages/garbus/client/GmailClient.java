package pucrs.ages.garbus.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class GmailClient {

    private Session session;

    @Value("${mail.sender.username}")
    private String username;

    @Value("${mail.sender.password}")
    private String password;

    @Value("${mail.server.host}")
    private String serverHost;

    @Value("${mail.server.port}")
    private String serverPort;

    @Async
    public void sendTo(String recipient, String subject, String text) {
        try {
            if (isNull(this.session)) this.initializeSession();
            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail para " + recipient + ". Assunto do email: " + subject, e);
        }
    }

    private void initializeSession() {
        this.session = Session.getInstance(this.initializeProps(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private Properties initializeProps() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", this.serverHost);
        props.put("mail.smtp.port", this.serverPort);
        return props;
    }
}
