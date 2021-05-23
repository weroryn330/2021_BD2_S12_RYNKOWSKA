package polsl.tab.skiresort.api.entry;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.UserRepository;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public abstract class IntegrationTestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    private void setup() {
        userRepository.save(
                new User(
                       "Test",
                       "Test",
                       "Test",
                       "Test",
                       "Test",
                       "Test",
                       "Test",
                       "Test",
                        "test@test.test",
                        passwordEncoder.encode("testPassword")
                )
        );
    }
}
