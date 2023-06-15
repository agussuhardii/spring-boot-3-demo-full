package com.agussuhardi.springdemofull.repository;

import com.agussuhardi.springdemofull.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}