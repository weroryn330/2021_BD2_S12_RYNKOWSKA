package polsl.tab.skiresort.api.entry.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtility jwtTokenUtility;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private static final String USER_EXISTENCE_ERROR = "User does not exist";

    public UserService(UserRepository userRepository,
                       JwtTokenUtility jwtTokenUtility,
                       PasswordEncoder passwordEncoder,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public UserResponse getUserDetails(String requestTokenHeader){
        return new UserResponse(userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(requestTokenHeader))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR)));
    }

    public UserResponse updateUserPassword(String token, UserRequest body) {
        var currentUser = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (currentUser.isPresent()) {
            var newPassword = passwordEncoder.encode(body.getPassword());
            currentUser.get().setPassword(newPassword);
            var newUser = userRepository.save(currentUser.get());
            return new UserResponse(
                    newUser,
                    jwtTokenUtility.generateToken(
                            new UserLoginRequest(
                                    newUser.getEmail(),
                                    newUser.getPassword(),
                                    roleService.getUserRoleName(newUser.getEmail())
                            )
                    )
            );
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_EXISTENCE_ERROR);
    }

    public UserResponse updateUserEmail(String token, UserRequest body) {
        var currentUser = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
        if (currentUser.isPresent()) {
            var newEmail = body.getEmail();
            if (userRepository.findByEmail(newEmail).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email exist");
            }
            currentUser.get().setEmail(newEmail);
            var newUser = userRepository.save(currentUser.get());
            return new UserResponse(
                    newUser,
                    jwtTokenUtility.generateToken(
                            new UserLoginRequest(
                                    newUser.getEmail(),
                                    newUser.getPassword(),
                                    roleService.getUserRoleName(newUser.getEmail())
                            )
                    )
            );
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_EXISTENCE_ERROR);
    }

    public UserResponse updateUserDetails(String token, UserRequest body) {
        var currentUser = userRepository.findByEmail(jwtTokenUtility.getUsernameFromToken(token))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_EXISTENCE_ERROR));
        return new UserResponse(userRepository.save(User.editMapping(currentUser, body, false)));
    }
}
