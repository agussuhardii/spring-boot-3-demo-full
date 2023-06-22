package com.agussuhardi.springdemofull.config.security;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CostumeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public @Override void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {

        var status = UNAUTHORIZED;

        response.setStatus(status.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response
                .getWriter()
                .write(new GlobalApiResponse<>(status.getReasonPhrase(), status).toJson());
    }
}
