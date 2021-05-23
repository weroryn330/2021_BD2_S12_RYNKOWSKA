package polsl.tab.skiresort.api.entry.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.model.Role;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.RoleRepository;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public RegisterService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isEmpty()) {
            User user = new User(
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
            );
            Role role = this.roleRepository.findByRoleName("ROLE_USER").get();
            user.addRole(role);
            return new UserResponse(userRepository.save(user));
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + userRequest.getEmail() + " exists!");
    }
}
