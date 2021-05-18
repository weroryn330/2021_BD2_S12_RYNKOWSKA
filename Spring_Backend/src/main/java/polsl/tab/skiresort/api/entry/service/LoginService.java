package polsl.tab.skiresort.api.entry.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import polsl.tab.skiresort.api.entry.response.UserResponse;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse mapResponse(String email, String token) {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserResponse(
                    user.get(),
                    token
            );
        }
        throw new UsernameNotFoundException("User not found");
    }
}
