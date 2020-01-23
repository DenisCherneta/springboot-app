package spring.pro.springboot.jdbc.service.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import spring.pro.springboot.jdbc.dao.CustomerDao;

import java.util.Optional;

//import org.springframework.context.annotation.Scope;
//import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;


@Slf4j
@Component
//@Scope(scopeName = SCOPE_SESSION) //TODO: добавить сессионность
public class AuditorAwareBean implements AuditorAware<String> {

    CustomerDao customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDaoImpl) {
        this.customerDao = customerDaoImpl;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) { // если авторизованы!
            String currentUserName = authentication.getName();
            return Optional.of(currentUserName);
        }
        return Optional.empty();
    }
}
