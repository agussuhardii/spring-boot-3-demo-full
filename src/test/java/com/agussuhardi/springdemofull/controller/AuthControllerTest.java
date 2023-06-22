package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.util.List;
import java.util.Locale;

/**
 * @author agussuhardi
 * @created 17/06/23/06/2023 :06.28
 * @project spring-demo-full
 * <p>
 * check api which automatically runs on build
 * @Param webEnvironment : set port in test
 * @Param executionPhase : run query every before or after test method
 * @Param  statements : sql query
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
//        statements = {
//                "delete from user_role where user_role.user_id !=null;",
//                "delete from users where id is not null ;"
//        })
class AuthControllerTest {

    private final String BASE_URL = "/api/v1/auth";

    @LocalServerPort
    protected Integer port;

    @Autowired
    protected TestRestTemplate restTemplate;
    protected String HOST = "http://localhost:";
    protected Faker faker = new Faker(new Locale("id", "ID"));
    protected String password = "Pa$$W0rD";

    /**
     * register test method with TestRestTemplate http client
     */
    @Test
    void register() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new RegisterVO(faker.name().fullName(), password, null, null, null, null, null, null);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + port + BASE_URL + "/register"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}