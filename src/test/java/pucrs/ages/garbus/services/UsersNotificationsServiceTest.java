package pucrs.ages.garbus.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.NotificationsDisabledUntilWhenDTO;
import pucrs.ages.garbus.entities.NotificationTokens;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.repositories.NotificationTokensRepository;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UsersNotificationsServiceTest {
    @InjectMocks
    private UsersNotificationsService usersNotificationsService;

    @Mock
    private UsersNotificationsRepository usersNotificationsRepository;

    @Mock
    private NotificationTokensRepository notificationTokensRepository;

    @Mock
    private UsersService usersService;

    @BeforeEach
    void setup() {
        usersNotificationsService = new UsersNotificationsService(usersNotificationsRepository, notificationTokensRepository, usersService);
    }

    @Test
    @DisplayName("Saving already existing token")
    void saveTokenAlreadyExisting() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        String token = "asdfhjklasdfhjklasdfhjkl";

        // When
        given(notificationTokensRepository.findByToken(token)).willReturn(NotificationTokens.builder().token(token).build());
        usersNotificationsService.saveToken(login, token);

        // Then
        then(notificationTokensRepository.findByToken(token).getToken().equals(token));
    }

    @Test
    @DisplayName("Notifications disabled until when")
    void isDisabledUntilWhen() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        LocalDateTime disabledUntil = LocalDateTime.now().plusSeconds(10);

        // When
        given(usersNotificationsRepository.findByLogin(login)).willReturn(
                Optional.ofNullable(
                        UsersNotifications.builder().users(users.get(0)).disabledUntil(disabledUntil).build()
                )
        );
        NotificationsDisabledUntilWhenDTO disabledUntilWhenDto = usersNotificationsService.isDisabledUntilWhen(login);

        // Then
        then(disabledUntilWhenDto.getDisabledUntil().equals(disabledUntil));
    }

    @Test
    @DisplayName("Notifications disabled until when - No registered devices")
    void isDisabledUntilWhenNotFound() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();

        //Then
        assertThatThrownBy(() -> usersNotificationsService.isDisabledUntilWhen(login)).isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Disable notifications")
    void disableNotifications() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        int seconds = 3600;

        // When
        given(usersNotificationsRepository.findByLogin(login)).willReturn(
                Optional.ofNullable(
                        UsersNotifications.builder().users(users.get(0)).disabledUntil(null).build()
                )
        );
        usersNotificationsService.disable(login, seconds);

        // Then
        Optional<UsersNotifications> nr = usersNotificationsRepository.findByLogin(login);
        if (nr.isPresent()) {
            then(nr.get().getDisabledUntil()).isNotNull();
        } else {
            fail("UsersNotifications not found");
        }
    }

    @Test
    @DisplayName("Disable notifications - No registered devices")
    void disableNotificationsNotFound() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        int seconds = 3600;

        // Then
        assertThatThrownBy(() -> usersNotificationsService.disable(login, seconds)).isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("Reactivate notifications")
    void reactivateNotifications() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        LocalDateTime disabledUntil = LocalDateTime.now();

        // When
        given(usersNotificationsRepository.findByLogin(login)).willReturn(
                Optional.ofNullable(
                        UsersNotifications.builder().users(users.get(0)).disabledUntil(disabledUntil).build()
                )
        );
        usersNotificationsService.reactivate(login);

        // Then
        Optional<UsersNotifications> nr = usersNotificationsRepository.findByLogin(login);
        if (nr.isPresent()) {
            then(nr.get().getDisabledUntil()).isNull();
        } else {
            fail("UsersNotifications not found");
        }
    }

    @Test
    @DisplayName("Reactivate notifications - No registered devices")
    void reactivateNotificationsNotFound() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();

        // Then
        assertThatThrownBy(() -> usersNotificationsService.reactivate(login)).isInstanceOf(BadRequestException.class);
    }

    private List<Users> generateUsers() {
        List<Users> users = new ArrayList<>();
        users.add(Users.builder().id(1).name("Test User").login("test").build());
        return users;
    }
}
