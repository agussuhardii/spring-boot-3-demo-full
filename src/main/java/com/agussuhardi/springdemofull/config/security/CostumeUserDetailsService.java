package com.agussuhardi.springdemofull.config.security;

import com.agussuhardi.springdemofull.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CostumeUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.login(username);

        log.info("request user auth => {}", username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return optional.get();
    }
}
