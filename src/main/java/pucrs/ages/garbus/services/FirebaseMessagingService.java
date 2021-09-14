package pucrs.ages.garbus.services;

import com.google.firebase.messaging.*;
import pucrs.ages.garbus.Utils.FirebaseMessage;
import org.springframework.stereotype.Service;
import lombok.Builder;

import java.util.List;

@Builder
@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public void sendNotification(FirebaseMessage note, List<String> tokens) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        MulticastMessage message = MulticastMessage
                .builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .build();

        firebaseMessaging.sendMulticast(message);
    }

}