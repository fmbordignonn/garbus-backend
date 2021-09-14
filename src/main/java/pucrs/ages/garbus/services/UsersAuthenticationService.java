package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.Utils.JWTUtility;
import pucrs.ages.garbus.Utils.PasswordUtil;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersAuthenticationService {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordUtil passwordUtil;

    private final UsersRepository usersRepository;

    public LoginResponse authenticateUser(JwtRequest jwtRequest) throws Exception {

        Users user = usersService.findByLogin(jwtRequest.getLogin());
        if (user == null || !user.getPassword().equals(jwtRequest.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getLogin(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }

        final UserDetails userDetails = new User(jwtRequest.getLogin(), jwtRequest.getPassword(), new ArrayList<>());
        final String token = jwtUtility.generateToken(userDetails, user);

        return new LoginResponse(token, user.isMustChangePwd());
    }

    public PasswordRecoveryResponse recoveryPassword(String login) throws IOException {
        Users user = Optional.ofNullable(usersService.findByLoginEquals(login))
                .orElseThrow(() -> new NotFoundException(new ErrorResponse(String.format("Usuário com Login %s não encontrado.", login))));
        PasswordRecoveryResponse passwordRecoveryResponse = new PasswordRecoveryResponse();
        if (!Objects.isNull(user.getEmail()) && !user.getEmail().isBlank()) {
            passwordRecoveryResponse.setHasEmail(true);
            String newPassword = redefinePassword(user);
            sendPasswordRecoveryMail(user, newPassword);
            passwordRecoveryResponse.setEmailSent(true);
            return passwordRecoveryResponse;
        }
        passwordRecoveryResponse.setHasEmail(false);
        passwordRecoveryResponse.setEmailSent(false);
        return passwordRecoveryResponse;
    }


    private void sendPasswordRecoveryMail(Users user, String newPassword) throws IOException {
        emailService.sendTo(user.getEmail(),"Recuperação Senha", "Sua nova senha temporária é: " + newPassword);
    }

    public TempPasswordGenerationResponse generateTempPassword(long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Usuário não encontrado para o id " + userId)
                ));
        return new TempPasswordGenerationResponse(redefinePassword(user));
    }

    public String redefinePassword(Users user) {
        String newPassword = passwordUtil.generatePassayPassword();
        user.setPassword(newPassword);
        user.setMustChangePwd(true);
        usersService.save(user);

        return newPassword;
    }

    public String changePassword(String login, String password) {
        String success = "Senha alterada";

        Users user = Optional.ofNullable(usersRepository.findByLogin(login))
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Usuário não encontrado para o id " + login)
                ));

        user.setPassword(password);
        user.setMustChangePwd(false);

        usersRepository.save(user);

        return success;
    }
}
