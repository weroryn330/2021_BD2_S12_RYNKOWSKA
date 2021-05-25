package polsl.tab.skiresort.api.entry;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest body){

        return new ResponseEntity<>(
                userService.saveUser(body)
                , HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestHeader("Authorization") String requestTokenHeader,
                                                   @RequestBody UserRequest body){

        return new ResponseEntity<>(
                userService.updateUser(body, requestTokenHeader)
                , HttpStatus.OK
        );
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String requestTokenHeader){
        userService.deleteUser(requestTokenHeader);
    }

}
