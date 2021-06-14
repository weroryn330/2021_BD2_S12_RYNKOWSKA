package polsl.tab.skiresort.api.entry.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import polsl.tab.skiresort.api.entry.request.UserLoginRequest;
import polsl.tab.skiresort.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserLoginRequest(
                    user.get().getEmail(),
                    user.get().getPassword(),
                    roleService.getUserRoleName(user.get().getEmail())
            );
        }
        throw new UsernameNotFoundException("User not found!");
    }
}
