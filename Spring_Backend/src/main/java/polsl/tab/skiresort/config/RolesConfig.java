package polsl.tab.skiresort.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import polsl.tab.skiresort.model.Role;
import polsl.tab.skiresort.repository.RoleRepository;

@Configuration
class RolesConfig {

    private static final String ROLE_USER = "ROLE_USER";

    private static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    private static final String ROLE_TECHNICIAN = "ROLE_TECHNICIAN";

    private static final String ROLE_OWNER = "ROLE_OWNER";

    private static final Logger logger = LoggerFactory.getLogger(RolesConfig.class);

    private final RoleRepository roleRepository;

    private void deleteRoles() {
        roleRepository.deleteAll();
    }

    RolesConfig(RoleRepository repository,
                @Value("${resort.roles.recreate}") Boolean recreate

    ) {
        this.roleRepository = repository;
        if (Boolean.TRUE.equals(recreate)) {
            deleteRoles();
        }
        if (repository.findAll().size() == 3) {
            roleRepository.save(new Role(ROLE_TECHNICIAN));
        }
        if (repository.findAll().isEmpty()) {
            var roleUser = new Role(ROLE_USER);
            var roleEmployee = new Role(ROLE_EMPLOYEE);
            var roleTechnician = new Role(ROLE_TECHNICIAN);
            var roleOwner = new Role(ROLE_OWNER);

            roleRepository.save(roleUser);
            roleRepository.save(roleEmployee);
            roleRepository.save(roleTechnician);
            roleRepository.save(roleOwner);
            logger.info("Roles added to database");
        } else {
            logger.info("Roles exist in database");
        }
    }
}
