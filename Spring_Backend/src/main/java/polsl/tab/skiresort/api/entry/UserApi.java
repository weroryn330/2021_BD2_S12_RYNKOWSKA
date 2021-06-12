package polsl.tab.skiresort.api.entry;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.entry.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String requestTokenHeader){
        return ResponseEntity.ok(userService.getUserDetails(requestTokenHeader));
    }

    @PutMapping("/password")
    public ResponseEntity<UserResponse> updateUserPassword(@RequestHeader("Authorization") String token,
                                                           @RequestBody UserRequest body
    ) {
        return ResponseEntity.ok(userService.updateUserPassword(token, body));
    }

    @PutMapping("/email")
    public ResponseEntity<UserResponse> updateUserEmail(@RequestHeader("Authorization") String token,
                                                        @RequestBody UserRequest body
    ) {
        return ResponseEntity.ok(userService.updateUserEmail(token, body));
    }

    @PutMapping("/details")
    public ResponseEntity<UserResponse> updateUserDetails(@RequestHeader("Authorization") String token,
                                                          @RequestBody UserRequest body
    ) {
        return ResponseEntity.ok(userService.updateUserDetails(token, body));
    }
}
