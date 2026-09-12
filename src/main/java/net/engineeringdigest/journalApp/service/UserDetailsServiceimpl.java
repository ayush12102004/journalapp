package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repositery.UserEntryRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private UserEntryRepositery userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        net.engineeringdigest.journalApp.entity.User user = userRepository.findByUserName(username);
        if(user != null)
        {
            UserDetails userDetails = User.builder().username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with Username:" + username);

    }
}
