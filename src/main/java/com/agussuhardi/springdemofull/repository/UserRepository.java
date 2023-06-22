package com.agussuhardi.springdemofull.repository;

import com.agussuhardi.springdemofull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User  u where u.username=?1 or u.email=?1 or u.id=?1")
    Optional<User> login(String username);
}