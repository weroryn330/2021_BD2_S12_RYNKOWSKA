package polsl.tab.skiresort.api.entry.service;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.jwt.JwtTokenUtility;
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


    public UserService(UserRepository userRepository, JwtTokenUtility jwtTokenUtility, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUserDetails(String requestTokenHeader){

        String jwtToken;
        String username;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
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
                System.out.println("Unable to get JWT Token");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
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

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token has expired");
            }

            Optional<User> user = userRepository.findByEmail(username);;

            if(user.isPresent()){
                if (userRepository.findByEmail(body.getEmail()).isEmpty()) {


                    user.get().setFirstName(body.getFirstName());
                    user.get().setLastName(body.getLastName());
                    user.get().setAddress(body.getAddress());
                    user.get().setCity(body.getCity());
                    user.get().setVoivodeship(body.getVoivodeship());
                    user.get().setCountry(body.getCountry());
                    user.get().setPostalCode(body.getPostalCode());
                    user.get().setPhone(body.getPhone());
                    user.get().setEmail(body.getEmail());

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

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);

                userRepository.deleteByEmail(username);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token has expired");
            }
        }
    }
}
