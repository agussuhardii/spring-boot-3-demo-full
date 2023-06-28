package com.agussuhardi.springdemofull.controller.customer;

import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.vo.LoginVO;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.sql.DataSource;
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
                scripts = "classpath:/sql/create_user_CUSTOMER.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:/sql/remove_user_CUSTOMER.sql")
})
@Slf4j
public class BaseCustomerControllerTest {
    protected final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    protected Integer port;
    @Autowired
    protected TestRestTemplate restTemplate;
    protected String HOST = "http://localhost:";
    protected JdbcTemplate jdbcTemplate;
    Faker faker = new Faker(new Locale("id", "ID"));
    @Autowired
    private AuthService authService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @BeforeEach
    public void setUp() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HOST = HOST + port;
        var token = authService.login(new LoginVO("customer", "password"));
        headers.setBearerAuth(token);
    }

}
