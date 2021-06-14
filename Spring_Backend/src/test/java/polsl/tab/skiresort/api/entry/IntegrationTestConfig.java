package polsl.tab.skiresort.api.entry;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.model.Role;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.RoleRepository;
import polsl.tab.skiresort.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@SpringBootTest
@Transactional
public abstract class IntegrationTestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        var roles = new ArrayList<Role>();
        roles.add(roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role does not exist")));
        var user = new User(
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
        );
        user.setRoleList(roles);
        userRepository.save(user);
    }
}