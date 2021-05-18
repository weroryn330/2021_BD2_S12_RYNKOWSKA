package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import polsl.tab.skiresort.model.User;
import polsl.tab.skiresort.repository.RoleRepository;
import polsl.tab.skiresort.repository.UserRepository;

import java.util.stream.Collectors;

@Configuration
class OwnerCredentialsConfig {

    private final UserRepository userRepository;

    // Before changing owner details delete him/her in database
    private void deleteOwnerAccount(String ownerEmail) {
        userRepository.deleteByEmail(ownerEmail);
    }

    OwnerCredentialsConfig(final PasswordEncoder passwordEncoder,
                           final UserRepository userRepository,
                           final RoleRepository roleRepository,
                           @Value("${resort.owner.email}") String email,
                           @Value("${resort.owner.password}") String password,
                           @Value("${resort.owner.firstName}") String firstName,
                           @Value("${resort.owner.lastName}") String lastName,
                           @Value("${resort.owner.address}") String address,
                           @Value("${resort.owner.city}") String city,
                           @Value("${resort.owner.voivodeship}") String voivodeship,
                           @Value("${resort.owner.country}") String country,
                           @Value("${resort.owner.postalCode}") String postalCode,
                           @Value("${resort.owner.phone}") String phone,
                           @Value("${resort.owner.recreate}") Boolean recreate
    ) {
        this.userRepository = userRepository;

        if (recreate) {
            deleteOwnerAccount(email);
        }

        Logger logger = LoggerFactory.getLogger(OwnerCredentialsConfig.class);
        if (userRepository.findByEmail(email).isPresent()) {
            logger.info("Owner exists in database");
        } else {
            userRepository.save(
                    new User(
                            firstName,
                            lastName,
                            address,
                            city,
                            voivodeship,
                            country,
                            postalCode,
                            phone,
                            email,
                            passwordEncoder.encode(password),
                            roleRepository.findAll()
                                    .stream()
                                    .filter(role -> !role.getRoleName().equals("ROLE_USER"))
                                    .collect(Collectors.toList()))
                    );
            logger.info("Ski Resort owner added to database with credentials from properties.");
        }

    }
}
