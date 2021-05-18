package polsl.tab.skiresort.api.entry;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.api.entry.service.LoginService;
import polsl.tab.skiresort.api.entry.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginApi {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtility jwtTokenUtility;

    private final UserDetailsServiceImpl userDetailsService;

    private final LoginService loginService;

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
    }

    public LoginApi(AuthenticationManager authenticationManager, JwtTokenUtility jwtTokenUtility, UserDetailsServiceImpl userDetailsService, LoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtility = jwtTokenUtility;
        this.userDetailsService = userDetailsService;
        this.loginService = loginService;
    }

    @GetMapping("/owner")
    String ownerEndpoint() {
        return "Owner";
    }

    @PostMapping
    ResponseEntity<String> login(@RequestBody UserLoginRequest body) {
        authenticate(body.getUsername(), body.getPassword());
        var userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        var token = jwtTokenUtility.generateToken(body);
        return ResponseEntity.ok(
                token
        );
    }
}
