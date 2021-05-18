package polsl.tab.skiresort.api.entry.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.UserRepository;

import javax.persistence.EntityExistsException;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isEmpty()) {
            return new UserResponse(
                    userRepository.save(
                            new User(
                                    userRequest.getFirstName(),
                                    userRequest.getLastName(),
                                    userRequest.getAddress(),
                                    userRequest.getCity(),
                                    userRequest.getVoivodeship(),
                                    userRequest.getCountry(),
                                    userRequest.getPostalCode(),
                                    userRequest.getPhone(),
                                    userRequest.getEmail(),
                                    passwordEncoder.encode(userRequest.getPassword())
                            )
                    )
            );
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + userRequest.getEmail() + " exists!");
    }
}
