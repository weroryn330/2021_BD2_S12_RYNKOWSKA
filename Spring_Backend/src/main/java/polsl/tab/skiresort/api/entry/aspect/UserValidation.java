package polsl.tab.skiresort.api.entry.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.repository.UserRepository;

@Aspect
@Component
class UserValidation {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserValidation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Pointcut("execution(* polsl.tab.skiresort.api.entry.LoginApi.login(..))")
    static void validateUserLogin() { /* Used for pointcut header */ }

    @Around("validateUserLogin()")
    Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        var userRequest = (UserLoginRequest)  joinPoint.getArgs()[0];
        var dbUser = userRepository.findByEmail(userRequest.getUsername());
        if (dbUser.isPresent() &&
                passwordEncoder.matches(
                        userRequest.getPassword(),
                        dbUser.get().getPassword()
                )
        ) {
            return joinPoint.proceed();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }
}
