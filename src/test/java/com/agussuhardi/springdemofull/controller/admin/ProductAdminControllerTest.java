package com.agussuhardi.springdemofull.controller.admin;

import com.agussuhardi.springdemofull.vo.ProductUpdateQtyVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
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
                        "classpath:/sql/ProductAdminControllerTest/ProductAdminControllerTest_BEFORE.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:/sql/remove_user_ADMIN.sql",
                        "classpath:/sql/ProductAdminControllerTest/ProductAdminControllerTest_AFTER.sql"})
})
class ProductAdminControllerTest extends BaseAdminControllerTest {

    private final String BASE_URL = "/admin/api/v1/products";
    private final String categoryId = "7f000101-8900-11d3-8189-00d1db930000";
    private final String productId = "7f000101-8901-1d1a-8189-013d23250000";

    @Test
    void add() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new ProductVO(faker.animal().name(), null, null, 0L, categoryId);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + BASE_URL));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        var id = new ObjectMapper().readTree(response.getBody()).path("data").path("id").asText();
        jdbcTemplate.update("delete from product where id=?", id);
    }

    @Test
    void delete() {
        var request = new RequestEntity<>(null, headers, HttpMethod.DELETE, URI.create(HOST + BASE_URL + "/" + productId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void update() {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new ProductUpdateVO(faker.animal().name(), null, null, categoryId);

        var request = new RequestEntity<>(form, headers, HttpMethod.PUT, URI.create(HOST + BASE_URL + "/" + productId));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateQty() {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new ProductUpdateQtyVO(300L);

        var request = new RequestEntity<>(form, headers, HttpMethod.PUT, URI.create(HOST + BASE_URL + "/" + productId + "/qty"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getById() {
        var request = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create(HOST + BASE_URL + "/" + productId));
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