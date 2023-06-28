package com.agussuhardi.springdemofull;

import com.agussuhardi.springdemofull.entity.User;
import com.agussuhardi.springdemofull.entity.UserRole;
import com.agussuhardi.springdemofull.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :14.40
 * @project spring-demo-full
 */
//@Component
@RequiredArgsConstructor
public class Init implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (!userRepository.findAll().isEmpty()) return;

        var user = User.builder()
                .fullName("admin")
                .email("admin@gmail.com")
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles(Collections.singletonList(UserRole.ROLE_ADMIN))
                .build();
        userRepository.save(user);

        user = User.builder()
                .fullName("customer")
                .email("customer@gmail.com")
                .username("customer")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles(Collections.singletonList(UserRole.ROLE_ADMIN))
                .build();
        userRepository.save(user);

    }
}
