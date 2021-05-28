package polsl.tab.skiresort.api.entry;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.api.entry.service.LoginService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginApi {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtility jwtTokenUtility;

    private final LoginService loginService;

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
    }

    public LoginApi(AuthenticationManager authenticationManager,
                    JwtTokenUtility jwtTokenUtility,
                    LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtility = jwtTokenUtility;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest body) {
        authenticate(body.getUsername(), body.getPassword());
        var token = jwtTokenUtility.generateToken(body);
        return ResponseEntity.ok(
                loginService.mapResponse(body.getUsername(), token)
        );
    }
}
