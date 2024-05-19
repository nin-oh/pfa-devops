package org.xproce.revormclass.service.inf;



import org.xproce.revormclass.user.entities.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xproce.revormclass.user.repositories.Userrepos;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel byUsername = userService.findByUsername(username);
        if (byUsername == null) {
            return null;
        }

        return User.builder()
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .roles(byUsername.getRole())
                .build();
    }
}
