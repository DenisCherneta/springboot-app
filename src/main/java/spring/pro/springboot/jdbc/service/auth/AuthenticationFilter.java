package spring.pro.springboot.jdbc.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected AuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication reqAuthentication;
        Optional tokenParam = Optional.ofNullable(request.getHeader(AUTHORIZATION));
        if (tokenParam.isPresent()){
            String token = request.getHeader(AUTHORIZATION);
            token = StringUtils.removeStart(token, "Bearer").trim();
            reqAuthentication = new UsernamePasswordAuthenticationToken(token, token);
        }else{
            reqAuthentication = new UsernamePasswordAuthenticationToken(null, null);
        }
        return getAuthenticationManager().authenticate(reqAuthentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
