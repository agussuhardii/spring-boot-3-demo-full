package com.agussuhardi.springdemofull;

import com.agussuhardi.springdemofull.entity.User;
import com.agussuhardi.springdemofull.entity.UserRole;
import com.agussuhardi.springdemofull.repository.UserRepository;
import com.agussuhardi.springdemofull.service.CategoryService;
import com.agussuhardi.springdemofull.service.ProductService;
import com.agussuhardi.springdemofull.vo.CategoryVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :14.40
 * @project spring-demo-full
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
public class Init implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (!userRepository.findAll().isEmpty()) return;

        var user = User.builder()
                .fullName("admin test")
                .email("admin.test@gmail.com")
                .username("admin.test")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles(Collections.singletonList(UserRole.ROLE_ADMIN))
                .build();
        userRepository.save(user);

        user = User.builder()
                .fullName("customer test")
                .email("customer.test@gmail.com")
                .username("customer.test")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles(Collections.singletonList(UserRole.ROLE_ADMIN))
                .build();
        userRepository.save(user);


//        sample category
        var category = categoryService.add(new CategoryVO("Gadget", null, null));
//        sample product
        productService.add(new ProductVO("Nokia", "", "", 30L, category.id()));


    }
}
