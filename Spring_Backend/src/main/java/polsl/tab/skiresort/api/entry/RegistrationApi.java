package polsl.tab.skiresort.api.entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.entry.service.RegisterService;

@RestController
@RequestMapping("/api/register")
public class RegistrationApi {

    private final RegisterService registerService;

    public RegistrationApi(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUserAndCreateToken(
            @RequestBody UserRequest body
    ) {
        return new ResponseEntity<>(registerService.registerUser(body, "ROLE_USER"), HttpStatus.OK);
    }
}
