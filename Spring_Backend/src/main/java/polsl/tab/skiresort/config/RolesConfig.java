package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.Role;
import polsl.tab.skiresort.repository.RoleRepository;

@Configuration
class RolesConfig {

    // Id = 1
    private static final String userRole = "ROLE_USER";

    // Id = 2
    private static final String employeeRole = "ROLE_EMPLOYEE";

    // Id = 3
    private static final String ownerRole = "ROLE_OWNER";

    private static final Logger logger = LoggerFactory.getLogger(RolesConfig.class);

    private final RoleRepository roleRepository;

    // Before adding roles delete them in database
    private void deleteRoles() {
        roleRepository.deleteAll();
    }

    RolesConfig(RoleRepository repository,
                @Value("${resort.roles.recreate}") Boolean recreate

    ) {
        this.roleRepository = repository;

        if (recreate) {
            deleteRoles();
        }

        if (repository.findAll().isEmpty()) {
            Role roleUser = new Role(userRole);
            Role roleEmployee = new Role(employeeRole);
            Role roleOwner = new Role(ownerRole);

            roleRepository.save(roleUser);
            roleRepository.save(roleEmployee);
            roleRepository.save(roleOwner);
            logger.info("Roles added to database");
        } else {
            logger.info("Roles exist in database");
        }
    }
}
