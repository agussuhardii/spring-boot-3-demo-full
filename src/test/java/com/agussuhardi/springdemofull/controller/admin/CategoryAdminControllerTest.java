package com.agussuhardi.springdemofull.controller.admin;

import com.agussuhardi.springdemofull.vo.CategoryVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.net.URI;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :13.44
 * @project spring-demo-full
 */
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {
                        "classpath:/sql/create_user_ADMIN.sql",
                        "classpath:/sql/CategoryAdminControllerTest/CategoryAdminControllerTest_BEFORE.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:/sql/remove_user_ADMIN.sql",
                        "classpath:/sql/CategoryAdminControllerTest/CategoryAdminControllerTest_AFTER.sql"})
})
class CategoryAdminControllerTest extends BaseAdminControllerTest {

    private final String BASE_URL = "/admin/api/v1/products/categories";
    private final String categoryId = "7f000101-8900-11d3-8189-00d1db930000";

    @Test
    void add() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new CategoryVO(faker.animal().name(), null, null);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + BASE_URL));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());


        var id = new ObjectMapper().readTree(response.getBody()).path("data").path("id").asText();
        jdbcTemplate.update("delete from category where id=?", id);
    }

    @Test
    void delete() {
        var request = new RequestEntity<>(null, headers, HttpMethod.DELETE, URI.create(HOST + BASE_URL + "/" + categoryId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void update() {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new CategoryVO(faker.animal().name(), null, null);

        var request = new RequestEntity<>(form, headers, HttpMethod.PUT, URI.create(HOST + BASE_URL + "/" + categoryId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getById() {
        var request = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create(HOST + BASE_URL + "/" + categoryId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void query() {
        var request = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create(HOST + BASE_URL));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}