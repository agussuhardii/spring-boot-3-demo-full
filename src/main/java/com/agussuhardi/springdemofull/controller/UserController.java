package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.dto.UsersDTO;

import com.agussuhardi.springdemofull.service.impl.UserService;
import com.agussuhardi.springdemofull.vo.UserQueryVO;
import com.agussuhardi.springdemofull.vo.UserUpdateVO;
import com.agussuhardi.springdemofull.vo.UserVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String save(@Valid @RequestBody UserVO vO) {
        return userService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody UserUpdateVO vO) {
        userService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UsersDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<UsersDTO> query(@Valid UserQueryVO vO) {
        return userService.query(vO);
    }
}
