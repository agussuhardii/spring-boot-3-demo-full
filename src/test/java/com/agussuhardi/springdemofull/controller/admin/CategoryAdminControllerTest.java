package com.agussuhardi.springdemofull.controller.admin;

import com.agussuhardi.springdemofull.vo.CategoryVO;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :13.44
 * @project spring-demo-full
 */
class CategoryAdminControllerTest extends BaseAdminControllerTest {

    private final String BASE_URL = "/admin/api/v1/products/categories";

    @Test
    void add() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new CategoryVO(faker.animal().name(), null, null);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + BASE_URL));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());


        var id = new ObjectMapper().readTree(response.getBody()).path("data").path("id").asText();
        System.out.println(id);
        jdbcTemplate.update("delete from category where id=?", id);
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
    }

    @Test
    void query() {
    }


    @AfterEach
    public void after() {
//        jdbcTemplate.update("delete from public.pilgrim where user_id=?", userId);
    }

}