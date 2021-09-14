package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.DisableNotificationsRequest;
import pucrs.ages.garbus.dtos.SaveNotificationTokenRequest;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/notifications")
public interface UsersNotificationsController {
    @PostMapping("/disable")
    @ApiOperation("Disable notifications temporarily for the logged user")
    ResponseEntity<Object> disable(
            @RequestBody DisableNotificationsRequest disableNotificationsRequest,
            @ApiIgnore Authentication authentication
    );

    @PutMapping("/save-token")
    @ApiOperation("Save notification token for the logged user")
    ResponseEntity<Object> saveToken(
            @RequestBody SaveNotificationTokenRequest tokenRequest,
            @ApiIgnore Authentication authentication
    );

    @PostMapping("/reactivate")
    @ApiOperation("Reactive notifications for the logged user")
    ResponseEntity<Object> reactivate(@ApiIgnore Authentication authentication);

    @GetMapping("/is-disabled")
    @ApiOperation("Check if and until when notifications are disabled for the logged user")
    ResponseEntity<Object> isDisabledUntilWhen(@ApiIgnore Authentication authentication);
}
