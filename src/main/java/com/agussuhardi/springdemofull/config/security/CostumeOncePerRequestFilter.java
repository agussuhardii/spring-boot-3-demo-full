package com.agussuhardi.springdemofull.config.security;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@Component
public class CostumeOncePerRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CostumeUserDetailsService costumeUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        var responseWrapper = new ContentCachingResponseWrapper(response);

        String bearerToken = request.getHeader("Authorization");
        try {
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                var jwt = bearerToken.substring(7);
                var tokenClaims = jwtService.parseJwt(jwt);
                var userDetails = costumeUserDetailsService.loadUserByUsername(tokenClaims.getBody().getId());

                var autContext = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autContext);
                responseWrapper.copyBodyToResponse();
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            var status = UNAUTHORIZED;
            response.setStatus(status.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            response.getWriter().write(new GlobalApiResponse<>(status).toJson());
            return;
        }
        filterChain.doFilter(request, response);
    }


}
