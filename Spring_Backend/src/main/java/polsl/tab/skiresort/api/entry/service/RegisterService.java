package polsl.tab.skiresort.api.entry.service;

import org.springframework.stereotype.Service;
import polsl.tab.skiresort.api.entry.request.UserRequest;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class RegisterService {

    private final UserRepository userRepository;


    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUserAndCreateToken(UserRequest userRequest) {
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
                            userRequest.getPassword()
                        )
                ),
                "Access Token"
        );
    }
}
