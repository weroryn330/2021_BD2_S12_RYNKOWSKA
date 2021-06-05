package polsl.tab.skiresort.api.entry.service;

import io.jsonwebtoken.ExpiredJwtException;
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

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtility jwtTokenUtility;

    private final PasswordEncoder passwordEncoder;

    private static final String TOKEN_START = "Bearer ";

    public UserService(UserRepository userRepository, JwtTokenUtility jwtTokenUtility, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUserDetails(String requestTokenHeader){

        String jwtToken;
        String username;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_START)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);

                Optional<User> user = userRepository.findByEmail(username);

                if(user.isPresent()){
                    return new UserResponse(user.get());
                }else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
                }

            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token has expired");
            }
        }

        return null;
    }

    public UserResponse saveUser(UserRequest body) {
        if (userRepository.findByEmail(body.getEmail()).isEmpty()) {
            return
                new UserResponse(
                    userRepository.save(
                        new User(body.getFirstName(),
                        body.getLastName(),
                        body.getAddress(),
                        body.getCity(),
                        body.getVoivodeship(),
                        body.getCountry(),
                        body.getPostalCode(),
                        body.getPhone(),
                        body.getEmail(),
                        passwordEncoder.encode(body.getPassword())))
                );
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + body.getEmail() + " exists!");
    }

    public UserResponse updateUser(UserRequest body, String requestTokenHeader) {
        String jwtToken;
        String username;
        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_START)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token has expired");
            }
            Optional<User> user = userRepository.findByEmail(username);
            if(user.isPresent()){
                if (userRepository.findByEmail(body.getEmail()).isEmpty()) {
                    var gotUser = user.get();
                    gotUser.setFirstName(body.getFirstName());
                    gotUser.setLastName(body.getLastName());
                    gotUser.setAddress(body.getAddress());
                    gotUser.setCity(body.getCity());
                    gotUser.setVoivodeship(body.getVoivodeship());
                    gotUser.setCountry(body.getCountry());
                    gotUser.setPostalCode(body.getPostalCode());
                    gotUser.setPhone(body.getPhone());
                    gotUser.setEmail(body.getEmail());
                    try {
                        user.get().setPassword(passwordEncoder.encode(body.getPassword()));
                    }catch(IllegalArgumentException e){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty" );
                    }
                    return new UserResponse(userRepository.save(user.get()));
                }else{
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "User with provided email already exists" );
                }
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found" );
            }
        }
        return null;
    }

    public void deleteUser(String requestTokenHeader){
        String jwtToken;
        String username;
        if (requestTokenHeader != null && requestTokenHeader.startsWith(TOKEN_START)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);
                userRepository.deleteByEmail(username);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token has expired");
            }
        }
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
                                    newUser.getPassword()
                            )
                    )
            );
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
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
                                    newUser.getPassword()
                            )
                    )
            );
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
    }
}
