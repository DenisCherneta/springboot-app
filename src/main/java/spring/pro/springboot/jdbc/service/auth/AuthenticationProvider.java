package spring.pro.springboot.jdbc.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spring.pro.springboot.jdbc.dao.CustomerDao;

import java.util.Optional;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    CustomerDao customerDaoImpl;

    @Autowired
    public void setCustomerDaoImpl(CustomerDao customerDaoImpl) {
        this.customerDaoImpl = customerDaoImpl;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object token = authentication.getCredentials();

        if (token == null){
            throw new BadCredentialsException("Cannot found '"+org.springframework.http.HttpHeaders.AUTHORIZATION+"' header with token or value is null!");
        }

        return Optional.
                ofNullable(token).
                map(String::valueOf).
                flatMap(customerDaoImpl::findByToken).
                orElseThrow(()->new UsernameNotFoundException("Cannot find User with token [" + token +"]"));
    }
}
