package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.vo.LoginVO;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.sql.DataSource;
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
 * @Param statements : sql query
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:/sql/AuthControllerTest_BEFORE.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:/sql/AuthControllerTest_AFTER.sql")
})
@Slf4j
class AuthControllerTest {

    private final String BASE_URL = "/api/v1/auth";

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;
    private String HOST = "http://localhost:";
    private Faker faker = new Faker(new Locale("id", "ID"));
    private String password = "Pa$$W0rD";
    private String email = "agus.suhardii@gmail.com";
    private ObjectMapper objectMapper = new ObjectMapper();
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * register test method with TestRestTemplate http client
     */
    @Test
    void registerSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new RegisterVO(faker.name().fullName(), password, null, null, null, null, email, null);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + port + BASE_URL + "/register"));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        var id = jdbcTemplate.queryForObject("select id from users where email=?", String.class, email);
        jdbcTemplate.update("delete from user_role where user_id=?", id);
        jdbcTemplate.update("delete from users where id=?", id);
    }


    @Test
    void loginSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        var form = new LoginVO("random@email.com", password);

        var request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(HOST + port + BASE_URL));
        var response = restTemplate.exchange(request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }


}