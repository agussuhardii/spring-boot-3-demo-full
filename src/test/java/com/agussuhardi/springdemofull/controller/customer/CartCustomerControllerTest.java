package com.agussuhardi.springdemofull.controller.customer;

import com.agussuhardi.springdemofull.vo.CartItemVO;
import com.agussuhardi.springdemofull.vo.CartVO;
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
import java.util.Collections;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :19.47
 * @project spring-demo-full
 */

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {
                        "classpath:/sql/create_user_CUSTOMER.sql",
                        "classpath:/sql/CartCustomerControllerTest/CartCustomerControllerTest_BEFORE.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:/sql/remove_user_CUSTOMER.sql",
                        "classpath:/sql/CartCustomerControllerTest/CartCustomerControllerTest_AFTER.sql"})
})
class CartCustomerControllerTest extends BaseCustomerControllerTest {

    private final String BASE_URL = "/customer/api/v1/carts";
    private final String productId = "7f000101-8901-1d1a-8189-013d23250000";


    @Test
    void addItems() throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);

        var item = new CartItemVO(productId, 10L);

        var form = new CartVO(Collections.singletonList(item));

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + BASE_URL));
        var response = restTemplate.exchange(request, String.class);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());


        var cartId = new ObjectMapper().readTree(response.getBody()).path("data").path("id").asText();
        jdbcTemplate.update("delete from cart_item where cart_id=?", cartId);
        jdbcTemplate.update("delete from cart where id=?", cartId);
    }

    @Test
    void getByPrincipal() {
    }
}