package polsl.tab.skiresort.api.entry.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.repository.RoleRepository;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class RoleService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public RoleService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String getUserRoleName(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        var roleList = roleRepository.findByUserId(user.getIdUser());
        if (roleList.size() == 2) {
            return "ROLE_OWNER";
        } else if (roleList.size() == 1){
            return roleList
                   .stream()
                   .findFirst()
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid roles for user"))
                   .getRoleName();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid roles for user");
        }
    }
}
