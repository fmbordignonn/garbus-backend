package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.NotificationsDisabledUntilWhenDTO;
import pucrs.ages.garbus.dtos.ErrorResponse;
import pucrs.ages.garbus.entities.NotificationTokens;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.repositories.NotificationTokensRepository;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;
import pucrs.ages.garbus.entities.UsersNotifications;

import java.time.LocalDateTime;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersNotificationsService {

    private final UsersNotificationsRepository usersNotificationsRepository;
    private final NotificationTokensRepository notificationTokensRepository;
    private final UsersService usersService;


    public Optional<UsersNotifications> findById(Long id) {
        return usersNotificationsRepository.findById(id);
    }

    public Optional<UsersNotifications> findByLogin(String login) {
        return usersNotificationsRepository.findByLogin(login);
    }

    public void updateUsersNotifications(UsersNotifications usersNotifications) {
        usersNotificationsRepository.saveAndFlush(usersNotifications);
    }

    public void saveToken(String login, String notificationToken) {
        Optional<NotificationTokens> notificationTokens = Optional.ofNullable(notificationTokensRepository.findByToken(notificationToken));
        if (notificationTokens.isPresent()) {
            return;
        }

        Users user = usersService.findByLogin(login);

        Optional<UsersNotifications> usersNotifications = this.findByLogin(login);
        if (usersNotifications.isEmpty()) {
            UsersNotifications un = UsersNotifications.builder().users(user).build();
            usersNotificationsRepository.save(un);
        }

        NotificationTokens nt = NotificationTokens.builder().users(user).token(notificationToken).build();
        notificationTokensRepository.save(nt);
    }

    public void disable(String login, int seconds) {
        UsersNotifications usersNotifications = this.findByLogin(login).orElseThrow(() ->
                new BadRequestException(
                        new ErrorResponse("Não há dispositivos registrados para notificações")
                )
        );
        LocalDateTime disabledUntil = LocalDateTime.now().plusSeconds(seconds);
        usersNotifications.setDisabledUntil(disabledUntil);
        usersNotificationsRepository.save(usersNotifications);
    }

    public void reactivate(String login) throws BadRequestException {
        UsersNotifications usersNotifications = this.findByLogin(login)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorResponse("Não há dispositivos registrados para notificações")
                ));
        usersNotifications.setDisabledUntil(null);
        this.updateUsersNotifications(usersNotifications);
    }

    public NotificationsDisabledUntilWhenDTO isDisabledUntilWhen(String login) {
        UsersNotifications usersNotifications = this.findByLogin(login)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorResponse("Não há dispositivos registrados para notificações")
                ));

        LocalDateTime disabledUntil = usersNotifications.getDisabledUntil();

        return NotificationsDisabledUntilWhenDTO.builder().disabledUntil(disabledUntil).build();
    }
}
